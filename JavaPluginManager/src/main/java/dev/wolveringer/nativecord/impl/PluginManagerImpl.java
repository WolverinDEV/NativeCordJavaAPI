package dev.wolveringer.nativecord.impl;

import dev.wolveringer.nativecord.plugin.Plugin;
import dev.wolveringer.nativecord.plugin.PluginManager;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.awt.*;
import java.lang.reflect.Method;

/**
 * Created by wolverindev on 03.10.16.
 */
@RequiredArgsConstructor
public class PluginManagerImpl {
    private final PluginManager handle;

    public void registerPlugin(@NonNull Plugin plugin){
        long npluginAddress = registerPlugin0(plugin);
        if(npluginAddress == -1)
            throw  new RuntimeException("Cant register plugin "+plugin.getName());
       // plugin.setNativePluginAddress(npluginAddress);
    }

    private native long registerPlugin0(Plugin plugin);

    private native void registerListener(long plugin,Object object, Method methode);
    private native void unregisterListener(long plugin);
    private native void unregisterListener(long plugin,Object object);
}
