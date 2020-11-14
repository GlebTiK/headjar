package com.glebtik.headjar.capabilities;

public interface IJar {
    void setJar(boolean b);
    boolean isJar();
    void copyInto(IJar jar);
}
