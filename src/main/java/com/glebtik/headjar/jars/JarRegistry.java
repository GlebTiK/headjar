package com.glebtik.headjar.jars;
import net.minecraft.util.ResourceLocation;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

public class JarRegistry {

    private static final Map<ResourceLocation, Callable<IJar>> JARS = new HashMap<>();
    private static boolean locked = false;

    private JarRegistry() {

    }

    public static void register(ResourceLocation registryName, Callable<IJar> jar) throws IllegalStateException{
        if(locked){
            throw new IllegalStateException("Registry locked. Could not register jar with name: " + registryName.toString());
        }
        if(JARS.containsKey(registryName)) {
            throw new IllegalStateException("RegistryName already used. Could not register jar with name: " + registryName.toString());
        }
        if(JARS.containsValue(jar)) {
            throw new IllegalStateException("Jar already registered with different name: " + registryName.toString());
        }
        JARS.put(registryName, jar);
    }
    public static void lock() throws IllegalStateException{
        if(locked){
            throw new IllegalStateException("Registry already locked");
        }
        locked = true;
    }

    public static void registerOwn() {
        register(new NoJar().getRegistryName(), NoJar::new);
        register(new HeadJar().getRegistryName(), HeadJar::new);
    }

    public static IJar getByRegistryName(ResourceLocation registryName) {
        try {
            return JARS.get(registryName).call();
        }catch (Exception e){
            System.out.println("Could not initiate Jar with registryName: " + registryName.toString());
            return new NoJar();
        }
    }
}
