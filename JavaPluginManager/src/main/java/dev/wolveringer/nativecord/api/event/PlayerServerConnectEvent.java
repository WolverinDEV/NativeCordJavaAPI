package dev.wolveringer.nativecord.api.event;

import dev.wolveringer.nativecord.api.server.ServerInfo;

/**
 * Created by wolverindev on 05.11.16.
 */
public class PlayerServerConnectEvent extends PlayerServerEvent {
    @Override
    public void setServer(ServerInfo server) {
        super.setServer(server);
    }
}
