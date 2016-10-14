package dev.wolveringer.nativecord.api.event;

import dev.wolveringer.nativecord.Native;
import dev.wolveringer.nativecord.impl.DataStorage;
import lombok.Getter;
import lombok.ToString;

/**
 * Created by wolverindev on 03.10.16.
 */
@ToString
public class Event {
    @Getter
    protected DataStorage storage;

    @Native
    protected Event(){}

    public Event(DataStorage storage){
        this.storage = storage;
    }
}
