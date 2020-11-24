package com.glebtik.headjar.capabilities;

import com.glebtik.headjar.jars.IJar;
import com.glebtik.headjar.jars.NoJar;

public class JarCapability implements IJarCapability{
    private IJar jar = new NoJar();

    @Override
    public IJar getJar() {
        return jar;
    }

    public void setJar(IJar jar) {
        if(this.jar != null) {
            this.jar.onRemove();
        }
        this.jar = jar;
    }
}
