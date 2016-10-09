package dev.wolveringer.nativecord.api.event;

import dev.wolveringer.nativecord.Native;
import dev.wolveringer.nativecord.impl.DataStorage;
import lombok.Getter;

/**
 * Created by wolverindev on 03.10.16.
 */
public class Event {
    @Getter
    protected DataStorage storage;

    @Native
    Event(){}

    public Event(DataStorage storage){
        this.storage = storage;
    }
}
