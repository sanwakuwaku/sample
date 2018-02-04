package com.sanwaku2.memoryreleasesample;

/**
 * Created by nabe on 2018/02/03.
 */

public class StringStruct {
    public static int sCount = 0;
    private String val1 = "123456789012345678901234567890";
    private String val2 = "123456789012345678901234567890";
    private String val3 = "123456789012345678901234567890";
    private StringStruct object;

    public StringStruct() {
        sCount++;
    }

    public String getVal1() {
        return val1;
    }

    public void setVal1(String val1) {
        this.val1 = val1;
    }

    public String getVal2() {
        return val2;
    }

    public void setVal2(String val2) {
        this.val2 = val2;
    }

    public String getVal3() {
        return val3;
    }

    public void setVal3(String val3) {
        this.val3 = val3;
    }

    public StringStruct getObject() {
        return object;
    }

    public void setObject(StringStruct object) {
        this.object = object;
    }
}
