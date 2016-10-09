package dev.wolveringer.nativecord.plugin;

import dev.wolveringer.nativecord.Native;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.Validate;

public class Plugin {
    private boolean enabled = false;

    @Native
    private long nativePluginAddress;
    @Getter
    @Setter(AccessLevel.PACKAGE)
    private PluginManager pluginManager;
    @Getter
    @Setter(AccessLevel.PACKAGE)
    private PluginDescription description;

    public final long getNativePluginAddress() {
        return nativePluginAddress;
    }

    @Native
    private final void load(){
        onLoad();
    }

    @Native
    private final void enable(){
        Validate.isTrue(!enabled,"Plugin already enabled.");
        onEnable();
    }

    @Native
    private final void disable(){
        Validate.isTrue(enabled,"Plugin already disabled.");
        onDisable();
    }

    public void onLoad(){}
    public void onEnable(){}
    public void onDisable(){}

}
