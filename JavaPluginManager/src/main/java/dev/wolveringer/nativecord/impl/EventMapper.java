package dev.wolveringer.nativecord.impl;

import dev.wolveringer.nativecord.api.event.PlayerHandschakeEvent;
import dev.wolveringer.nativecord.api.event.PlayerServerConnectEvent;
import dev.wolveringer.nativecord.api.event.PlayerServerConnectedEvent;

import java.util.HashMap;

/**
 * Created by wolverindev on 05.11.16.
 */
public class EventMapper {
    private static final HashMap<Class, Integer> classIdMapping = new HashMap<>();

    static {
        /*
         PLAYER_HANDSCHAKE_EVENT, - 0
         PLAYER_ENCRIPT_EVENT, - 1
         PLAYER_CONNECTED_EVENT, - 2
         PLAYER_DISCONNECT_EVENT, - 3
         PLAYER_SERVER_CONNECT_EVENT, -4
         PLAYER_SERVER_CONNECTED_EVENT, -5
         PLAYER_CHAT_EVENT - 6
         */
        classIdMapping.put(PlayerHandschakeEvent.class, 0);

        classIdMapping.put(PlayerServerConnectEvent.class, 4);
        classIdMapping.put(PlayerServerConnectedEvent.class, 5);
    }

    public static int getEventId(Class clazz){
        return classIdMapping.containsKey(clazz) ? classIdMapping.get(clazz) : -1;
    }
}
