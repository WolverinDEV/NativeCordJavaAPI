package dev.wolveringer.nativecord.impl;

import lombok.Getter;
import lombok.NonNull;
import org.apache.commons.lang3.Validate;

import java.lang.reflect.Array;
import java.nio.Buffer;
import java.nio.ByteBuffer;

/**
 * Created by wolverindev on 03.10.16.
 */
//Fastest way to transfare data between native and java (If you use ByteBuffer every operation a native methode will called.... its fucking slow)
@Getter
public class DataStorage {
    private boolean fixedLength = true;

    private int stringLength;
    private String[] strings;

    private int longLenth;
    private long[] longs;

    private int intLength;
    private int[] ints;

    private int byteLengt;
    private byte[] bytes;

    private int doubleLength;
    private double[] doubles;

    private int floatLength;
    private float[] floats;

    public DataStorage() {
        this(false, 0, 0, 0, 0, 0, 0);
    }

    public DataStorage(int stringLength, int longLenth, int intLength, int byteLength, int doubleLength, int floatLength) {
        this(true, stringLength, longLenth, intLength, byteLength, doubleLength, floatLength);
    }

    public DataStorage(boolean fixedLength, int stringLength, int longLenth, int intLength, int byteLength, int doubleLength, int floatLength) {
        this.fixedLength = fixedLength;

        // Dont need to init a array if not needed :)
        this.stringLength = stringLength;
        this.strings = stringLength > 0 || !fixedLength ? new String[stringLength] : null;

        this.longLenth = longLenth;
        this.longs = longLenth > 0 || !fixedLength ? new long[longLenth] : null;

        this.intLength = intLength;
        this.ints = intLength > 0 || !fixedLength ? new int[intLength] : null;

        this.byteLengt = byteLength;
        this.bytes = byteLength > 0 || !fixedLength ? new byte[byteLength] : null;

        this.doubleLength = doubleLength;
        this.doubles = doubleLength > 0 || !fixedLength ? new double[doubleLength] : null;

        this.floatLength = floatLength;
        this.floats = floatLength > 0 || !fixedLength ? new float[floatLength] : null;
    }


    public void setString(int pos, String string) {
        strings = (String[]) set(strings, pos, string);
        stringLength = strings.length;
    }

    public String getString(int pos) {
        return (String) get(strings, pos);
    }


    public long getLong(int pos) {
        return (Long) get(longs, pos);
    }

    public void setLong(int pos, long val) {
        longs = (long[]) set(longs, pos, val);
        longLenth = longs.length;
    }


    public int getInt(int pos) {
        return (Integer) get(ints, pos);
    }

    public void setInt(int pos, int val) {
        ints = (int[]) set(ints, pos, val);
        intLength = ints.length;
    }


    public byte getByte(int pos) {
        return (Byte) get(bytes, pos);
    }

    public void setByte(int pos, byte val) {
        bytes = (byte[]) set(bytes, pos, val);
        byteLengt = bytes.length;
    }


    public double getDouble(int pos) {
        return (Double) get(doubles, pos);
    }

    public void setDouble(int pos, double val) {
        doubles = (double[]) set(doubles, pos, val);
        doubleLength = doubles.length;
    }


    public float getFloat(int pos) {
        return (Float) get(floats, pos);
    }

    public void setFloat(int pos, float val) {
        floats = (float[]) set(floats, pos, val);
        floatLength = floats.length;
    }


    private Object get(Object array, int pos) { //TODO Validate position etc? (ArrayIndeOutOfBoundsException)
        int arrayLength = Array.getLength(array);
        if (pos < arrayLength)
            return Array.get(array, pos);
        if (fixedLength)
            throw new ArrayIndexOutOfBoundsException();
        return null;
    }

    private <T> Object set(Object array, int pos, T val) {
        array = allocatePosition(array, pos);
        Array.set(array, pos, val);
        return array;
    }

    private Object allocatePosition(@NonNull Object array, int pos) {
        int arrayLength = Array.getLength(array);
        if (fixedLength) {
            Validate.isTrue(pos < arrayLength, "Invalid position (" + pos + ") for array (" + array.getClass().getComponentType().getName() + ")[" + arrayLength + "]");
        }
        if (arrayLength >= pos) {
            Object _new = Array.newInstance(array.getClass().getComponentType(), pos + 1);
            System.arraycopy(array, 0, _new, 0, arrayLength);
            array = _new;
        }
        return array;
    }
}
