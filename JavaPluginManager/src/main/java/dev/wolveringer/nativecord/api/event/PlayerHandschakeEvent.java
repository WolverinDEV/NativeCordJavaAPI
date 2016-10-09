package dev.wolveringer.nativecord.api.event;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * Created by wolverindev on 09.10.16.
 */

@NoArgsConstructor
public class PlayerHandschakeEvent extends Event {
    public String getTargetHost(){
        return storage.getString(0);
    }

    public void setTargetHost(String host){
        storage.setString(0, host);
    }

    public short getTargetPort(){
        return (short) storage.getInt(0);
    }

    public void setTargetPort(short port){
        storage.setInt(0, port);
    }

    public int getClientVersion(){
        return storage.getInt(1);
    }

    public void setClientVersion(int version){
        storage.setInt(1, version);
    }

    @Deprecated //TODO
    public Object getConnection(){
        return null;
    }
}
