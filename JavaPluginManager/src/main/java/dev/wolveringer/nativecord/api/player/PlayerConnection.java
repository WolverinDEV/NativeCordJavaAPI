package dev.wolveringer.nativecord.api.player;

import dev.wolveringer.nativecord.Native;
import dev.wolveringer.nativecord.impl.DataStorage;
import dev.wolveringer.nativecord.packet.DataBuffer;
import io.netty.buffer.Unpooled;
import lombok.Getter;
import lombok.NonNull;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.chat.ComponentSerializer;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * Created by wolverindev on 10.10.16.
 */
public class PlayerConnection {
    @Native
    private static final ArrayList<PlayerConnection> connections = new ArrayList<>();

    public static List<PlayerConnection> getAllConnectedConnections(){
        return Collections.unmodifiableList(connections);
    }

    @Deprecated
    public static PlayerConnection getPlayerConnection(long nativeAdress){
        for(PlayerConnection connection : connections)
            if(nativeAdress == connection.getNativeAdress())
                return connection;
        return null;
    }

    public static PlayerConnection getPlayerConnection(@NonNull String name){
        for(PlayerConnection connection : connections)
            if(name.equalsIgnoreCase(connection.getPlayerName()))
                return connection;
        return null;
    }

    public static PlayerConnection getPlayerConnection(@NonNull UUID player){
        for(PlayerConnection connection : connections)
            if(player.equals(connection.getUuid()))
                return connection;
        return null;
    }

    public static enum State {
        HANDSHAKING,
        ENCRIPTING,
        PLAYING,
        STATUS;
    }

    @Getter
    @Native
    private long nativeAdress;

    @Getter
    @Native
    private State connectionState;
    @Getter
    @Native
    private int connectionVersion;
    @Getter
    @Native
    private String playerName;
    @Getter
    @Native
    private UUID uuid;
    @Getter
    @Native
    private InetAddress adress;
    private String currentServer; //TODO server connection...
    private List<String> pendingConnections; //TODO server pending connections....


    public void sendMessage(String message){
        sendMessage(TextComponent.fromLegacyText(message));
    }

    public void sendMessage(BaseComponent... message){
        DataBuffer buffer = new DataBuffer(Unpooled.buffer());
        buffer.write(message);
        buffer.writeByte(0);
        sendPacket(connectionVersion > 46 ? 0x0F : 0x02, buffer);
    }


    private boolean sendPacket(int packetId, DataBuffer buffer){
        DataStorage storage = new DataStorage();
        storage.setInt(0, packetId);

        byte[] bbuffer = new byte[buffer.readableBytes()];
        buffer.readBytes(bbuffer);
        storage.setBytes(bbuffer);

        return sendPacket0(storage);
    }

    public void disconnect(BaseComponent... message){
        disconnect0(ComponentSerializer.toString(message));
    }

    private native void disconnect0(String message);
    private native boolean sendPacket0(DataStorage buffer); //int[0] = packetId | byte[n] = packetData
}
