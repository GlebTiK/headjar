package com.glebtik.headjar;

import com.glebtik.headjar.capabilities.IJarCapability;
import com.glebtik.headjar.capabilities.JarCapability;
import com.glebtik.headjar.jars.JarRegistry;
import com.glebtik.headjar.capabilities.Storage;
import com.glebtik.headjar.register.ItemInit;
import com.glebtik.headjar.jars.behavoir.IronGolemJarBehavoir;
import com.glebtik.headjar.util.Events;
import com.glebtik.headjar.network.PacketHandler;
import com.glebtik.headjar.util.Reference;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


@Mod(modid = Reference.MOD_ID, version = Reference.VERSION, name = Reference.NAME)
public class HeadInAJar {

    @Mod.Instance
    public static HeadInAJar Instance;

    @SideOnly(Side.CLIENT)
    @Mod.EventHandler
    public void preInitSideOnly(FMLPreInitializationEvent event) {

    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        PacketHandler.registerPacket();
        CapabilityManager.INSTANCE.register(IJarCapability.class, new Storage(), JarCapability::new);
        MinecraftForge.EVENT_BUS.register(new Events());
        MinecraftForge.EVENT_BUS.register(IronGolemJarBehavoir.class);
        MinecraftForge.EVENT_BUS.register(ItemInit.class);
        Loader.instance().getActiveModList().get(0).getMod();
        JarRegistry.registerOwn();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        JarRegistry.lock();
    }
    @Mod.EventHandler
    public void Init(FMLInitializationEvent event) {

    }
    @Mod.EventHandler
    public void ServerInit(FMLServerStartingEvent event) {
        
    }
}
