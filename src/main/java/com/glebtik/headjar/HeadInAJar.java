package com.glebtik.headjar;

import com.glebtik.headjar.capabilities.IJar;
import com.glebtik.headjar.capabilities.Jar;
import com.glebtik.headjar.capabilities.Storage;
import com.glebtik.headjar.util.Events;
import com.glebtik.headjar.items.JarItem;
import com.glebtik.headjar.network.PacketHandler;
import com.glebtik.headjar.util.Reference;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
// import com.glebtik.headjar.items.BodyItem;


@Mod(modid = Reference.MOD_ID, version = Reference.VERSION, name = Reference.NAME)
public class HeadInAJar {
    public static JarItem JarItem;
//    public static BodyItem BodyItem;

    @Mod.Instance
    public static HeadInAJar Instance;

    @SideOnly(Side.CLIENT)
    @Mod.EventHandler
    public void preInitSideOnly(FMLPreInitializationEvent event) {
    //    ModelResourceLocation itemModelResourceLocation = new ModelResourceLocation("headjar", "textures/items/jar");
    //    final int DEFAULT_ITEM_SUBTYPE = 0;
    //    ModelLoader.setCustomModelResourceLocation(JarItem, DEFAULT_ITEM_SUBTYPE, itemModelResourceLocation);
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        PacketHandler.registerPacket();
        JarItem = (JarItem) (new JarItem().setUnlocalizedName("jar"));
        JarItem.setRegistryName("jar");
        ModelResourceLocation location = new ModelResourceLocation(Reference.MOD_ID + ":jar", "inventory");
        ModelLoader.setCustomModelResourceLocation(JarItem, JarItem.getItem().getMetadata(), location);
//        BodyItem = (BodyItem) (new BodyItem().setUnlocalizedName("body"));
//        BodyItem.setRegistryName("body");
        ForgeRegistries.ITEMS.register(JarItem);
//        ForgeRegistries.ITEMS.register(BodyItem);
        // System.out.println("DEBUG 1 2");
        CapabilityManager.INSTANCE.register(IJar.class, new Storage(), Jar.class);
        MinecraftForge.EVENT_BUS.register(new Events());
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    }
    @Mod.EventHandler
    public void Init(FMLInitializationEvent event) {

    }
    @Mod.EventHandler
    public void ServerInit(FMLServerStartingEvent event) {
        
    }
}
