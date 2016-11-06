package dev.wolveringer.nativecord.impl;

import dev.wolveringer.nativecord.Native;
import dev.wolveringer.nativecord.plugin.Plugin;
import dev.wolveringer.nativecord.plugin.PluginManager;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.awt.*;
import java.lang.reflect.Method;

/**
 * Created by wolverindev on 03.10.16.
 */
public class PluginManagerImpl {
    private final PluginManager handle = null;

    @Native
    private PluginManagerImpl(){ }

    public void registerPlugin(@NonNull Plugin plugin){
        long npluginAddress = registerPlugin0(plugin);
        if(npluginAddress == -1)
            throw  new RuntimeException("Cant register plugin "+plugin.getDescription().getName());
        if(npluginAddress != plugin.getNativePluginAddress())
            throw  new RuntimeException("Native didnt register plugin "+plugin.getDescription().getName()+" (Invalid plugin id: ["+npluginAddress+"/"+plugin.getNativePluginAddress()+"])");
    }

    private native long registerPlugin0(Plugin plugin);
    public native void enablePlugin(long plugin);
    public native void disablePlugin(long plugin);

    public native void registerListener(long plugin,Object object, int eventId, Method methode);
    public native void unregisterListener(long plugin);
    public native void unregisterListener(long plugin,Object object);
}
