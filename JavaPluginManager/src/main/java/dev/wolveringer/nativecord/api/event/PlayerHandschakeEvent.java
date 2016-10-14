package dev.wolveringer.nativecord.api.event;

import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created by wolverindev on 09.10.16.
 */

@NoArgsConstructor
@ToString(callSuper = true)
public class PlayerHandschakeEvent extends PlayerEvent {
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

    public int getState(){
        return storage.getInt(2);
    }

    public void setState(int state){
        storage.setInt(2, state);
    }

    @Deprecated //TODO
    public Object getConnection(){
        return null;
    }
}
