package dev.wolveringer.nativecord.api.event;

import dev.wolveringer.nativecord.api.server.ServerInfo;

/**
 * Created by wolverindev on 05.11.16.
 */
public class PlayerServerEvent extends PlayerEvent {
    public ServerInfo getServer(){
        return ServerInfo.getServer(storage.getString(0));
    }
    protected void setServer(ServerInfo server){
        storage.setString(0, server.getName());
    }
}
