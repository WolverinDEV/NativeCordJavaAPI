package dev.wolveringer;

import dev.wolveringer.nativecord.impl.DataStorage;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by wolverindev on 03.10.16.
 */

public class DataStorageTest {
    @Test
    public void testPutAndGet(){
        //boolean fixedLength, int stringLength, int longLenth, int intLength, int byteLength, int doubleLength, int floatLength
        DataStorage storage = new DataStorage(true,1,1,1,1,1,1);

        storage.setLong(0, 11);
        Assert.assertTrue(storage.getLong(0) == 11);

        storage.setInt(0, 7);
        Assert.assertTrue(storage.getInt(0) == 7);

        storage.setByte(0, (byte) 5);
        Assert.assertTrue(storage.getByte(0) == 5);

        storage.setDouble(0, 3.123456);
        Assert.assertTrue(storage.getDouble(0) == 3.123456);

        storage.setFloat(0, 12.34567F);
        Assert.assertTrue(storage.getFloat(0) == 12.34567F);

        storage.setString(0, "WolverinDEV");
        Assert.assertTrue("WolverinDEV".equals(storage.getString(0)));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testOutOfBounds1() throws ArrayIndexOutOfBoundsException{
        DataStorage storage = new DataStorage(true,1,0,0,0,0,0);
        storage.getString(1);
    }

    @Test()
    public void testOutOfBounds2(){
        DataStorage storage = new DataStorage(false,1,0,0,0,0,0);
        Assert.assertTrue(storage.getString(1) == null);
    }
}
