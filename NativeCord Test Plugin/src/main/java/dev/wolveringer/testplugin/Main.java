package dev.wolveringer.testplugin;

import dev.wolveringer.nativecord.api.EventHandler;
import dev.wolveringer.nativecord.api.Listener;
import dev.wolveringer.nativecord.api.event.PlayerHandschakeEvent;
import dev.wolveringer.nativecord.api.event.PlayerServerConnectEvent;
import dev.wolveringer.nativecord.api.player.PlayerConnection;
import dev.wolveringer.nativecord.api.server.ServerInfo;
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
            public void a(PlayerServerConnectEvent e){
                System.out.println("Servers: "+ ServerInfo.getServers());
                System.out.println("Player connect to server event: "+e.getPlayerConnection()+" - "+e.getServer());
            }

            @EventHandler
            public void a(PlayerHandschakeEvent e){
                System.out.println("Having handshake event. "+e);
                if(e != null){
                    System.out.println("Player connection name: "+e.getPlayerConnection().getPlayerName());
                    System.out.println("Player connection: "+e.getPlayerConnection());
                }
            }
        };
        new Thread(new Runnable() {
            public void run() {
                while (true){
                    try{
                        Thread.sleep(1000);
                    }catch (Exception e){}
                    System.out.println("Players: "+PlayerConnection.getAllConnectedConnections());
                }
            }
        }).start();
        getPluginManager().registerListener(this, listener);
        getPluginManager().registerListener(this, listener);
    }
}
