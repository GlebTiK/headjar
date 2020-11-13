package com.glebtik.headjar.capabilities;

public class Jar implements IJar {
    private boolean isJar = false;

    public void setJar(boolean b) {
        isJar=b;
    }


    public boolean isJar() {
        return isJar;
    }
}
