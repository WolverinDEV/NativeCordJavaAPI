package dev.wolveringer.nativecord.api.event;

import dev.wolveringer.nativecord.impl.DataStorage;

/**
 * Created by wolverindev on 03.10.16.
 */
public class Event {
    protected DataStorage storage;

    public Event(DataStorage storage){
        this.storage = storage;
    }
}
