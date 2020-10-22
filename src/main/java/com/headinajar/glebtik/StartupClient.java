package com.headinajar.glebtik;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

public class StartupClient {
    public static void preInitClientOnly()
    {
        // model to be used for rendering this item
        ModelResourceLocation itemModelResourceLocation = new ModelResourceLocation("headjar:jar", "inventory");
        final int DEFAULT_ITEM_SUBTYPE = 0;
        ModelLoader.setCustomModelResourceLocation(StartupCommon.JarItem, DEFAULT_ITEM_SUBTYPE, itemModelResourceLocation);
    }

    public static void initClientOnly()
    {
    }

    public static void postInitClientOnly()
    {
    }
}
