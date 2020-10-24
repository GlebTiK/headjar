package com.headinajar.glebtik;

public class Jar implements IJar {
    private byte isJar = 0;

    public void setJar(byte b) {
        isJar=b;
    }


    public byte isJar() {
        return isJar;
    }
}
