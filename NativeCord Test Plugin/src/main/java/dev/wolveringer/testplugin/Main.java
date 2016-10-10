package dev.wolveringer.testplugin;

import dev.wolveringer.nativecord.api.EventHandler;
import dev.wolveringer.nativecord.api.Listener;
import dev.wolveringer.nativecord.api.event.PlayerHandschakeEvent;
import dev.wolveringer.nativecord.plugin.Plugin;
import lombok.Getter;

/**
 * Created by wolverindev on 09.10.16.
 */
public class Main extends Plugin {
    @Getter
    private static Main instance;

    @Override
    public void onEnable() {
        instance = this;

        Listener listener = new Listener() {
            @EventHandler
            public void a(PlayerHandschakeEvent e){
                System.out.println("Having handshake event. "+e);
            }
        };
        getPluginManager().registerListener(this, listener);
        getPluginManager().registerListener(this, listener);
    }
}
