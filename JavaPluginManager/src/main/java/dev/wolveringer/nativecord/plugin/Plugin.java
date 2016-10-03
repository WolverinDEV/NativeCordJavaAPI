package dev.wolveringer.nativecord.plugin;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.Validate;

/**
 * Created by wolverindev on 03.10.16.
 */
public class Plugin {
    private boolean enabled = false;
    @Getter
    @Setter(AccessLevel.PACKAGE)
    private long nativePluginAddress;
    public String getName(){
        return "undefined";
    }

    public void getVersion(){

    }

    public final void enable(){
        Validate.isTrue(!enabled,"Plugin alredy enabled.");
    }

    public final void disable(){
        Validate.isTrue(enabled,"Plugin alredy disabled.");
    }

    public void onLoad(){}
    public void onEnable(){}
    public void onDisable(){}

}
