package com.glebtik.headjar.capabilities;

public class Jar implements IJar {
    private boolean isJar = false;

    @Override
    public void setJar(boolean b) {
        isJar=b;
    }

    @Override
    public boolean isJar() {
        return isJar;
    }

    @Override
    public void copyInto(IJar jar) {
        Jar implementedJar = (Jar) jar;
        isJar = implementedJar.isJar;
    }
}
