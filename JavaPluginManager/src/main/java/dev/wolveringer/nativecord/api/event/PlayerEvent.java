package dev.wolveringer.nativecord.api.event;

import dev.wolveringer.nativecord.api.player.PlayerConnection;

/**
 * Created by wolverindev on 14.10.16.
 */
public class PlayerEvent extends Event {
    public PlayerConnection getPlayerConnection(){
        return PlayerConnection.getPlayerConnection(getStorage().getLong(0));
    }
}
