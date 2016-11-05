package dev.wolveringer.nativecord.api;

/**
 * Created by wolverindev on 05.11.16.
 */
public class InvalidEventMethode extends RuntimeException {
    public InvalidEventMethode() {
    }

    public InvalidEventMethode(String message) {
        super(message);
    }
}
