package com.headinajar.glebtik;

import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class StartupCommon {
    public static JarItem JarItem;

    public static void preInitCommon() {
        JarItem = (JarItem) (new JarItem().setUnlocalizedName("jar"));
        JarItem.setRegistryName("jar");
        ForgeRegistries.ITEMS.register(JarItem);
    }

    public static void initCommon() {
    }

    public static void postInitCommon() {
    }
}
