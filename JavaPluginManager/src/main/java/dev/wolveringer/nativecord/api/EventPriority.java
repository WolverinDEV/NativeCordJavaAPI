package dev.wolveringer.nativecord.api;

import lombok.Getter;

/**
 * Created by wolverindev on 03.10.16.
 */
public enum EventPriority {
    LOWEST(0),
    LOW(25),
    MEDIUM(50),
    HIGH(75),
    HIGHEST(100);

    @Getter
    int level;

    EventPriority(int level) {
        this.level = level;
    }
}
