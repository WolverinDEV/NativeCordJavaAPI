package dev.wolveringer.nativecord.api.server;

import dev.wolveringer.nativecord.Native;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class ServerInfo {
    @Native
    private static List<ServerInfo> registeredServers = new ArrayList<>();

    public static ServerInfo getServer(String name){
        for(ServerInfo server : registeredServers)
            if(server.getName().equalsIgnoreCase(name))
                return server;
        return null;
    }

    public static List<ServerInfo> getServers(){
        return Collections.unmodifiableList(registeredServers);
    }

    @Native
    private static native ServerInfo registerServer0(String name, String host, int port, boolean addToConfig);

    public static ServerInfo registerServer(String name, String host, int port){
        return registerServer(name,host,port,false);
    }

    public static ServerInfo registerServer(String name, String host, int port, boolean addToConfig){
        if(getServer(name) != null)
            throw new IllegalArgumentException("ServerInfo alredy registered!");
        if(port > 0 && port < 65535)
            throw  new IllegalArgumentException("Port isnt valid");
        InetAddress address = null;
        try{
            address = InetAddress.getByName(host);
        }catch (Exception e){ }
        if(address == null)
            throw  new IllegalArgumentException("ServerInfo address isnt valid!");
        return registerServer0(name,host,port,addToConfig);
    }

    @Native
    private final String name;
    @Native
    private final String host;
    @Native
    private final int port;
}
