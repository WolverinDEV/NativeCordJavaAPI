package dev.wolveringer;

/**
 * Created by wolverindev on 06.11.16.
 */
public interface Callback<T> {
    public void done(T obj, Throwable ex);
}
