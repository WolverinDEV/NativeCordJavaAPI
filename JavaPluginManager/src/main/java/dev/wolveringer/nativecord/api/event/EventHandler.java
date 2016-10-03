package dev.wolveringer.nativecord.api.event;

/**
 * Created by wolverindev on 03.10.16.
 */
public @interface EventHandler {
    EventPriority priority() default EventPriority.MEDIUM;
}
