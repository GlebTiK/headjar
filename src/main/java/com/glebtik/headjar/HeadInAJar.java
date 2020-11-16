package com.glebtik.headjar;

import com.glebtik.headjar.capabilities.IJarCapability;
import com.glebtik.headjar.capabilities.JarCapability;
import com.glebtik.headjar.jars.JarRegistry;
import com.glebtik.headjar.capabilities.Storage;
import com.glebtik.headjar.util.Events;
import com.glebtik.headjar.items.JarItem;
import com.glebtik.headjar.network.PacketHandler;
import com.glebtik.headjar.util.Reference;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


@Mod(modid = Reference.MOD_ID, version = Reference.VERSION, name = Reference.NAME)
public class HeadInAJar {
    public static JarItem JarItem;

    @Mod.Instance
    public static HeadInAJar Instance;

    @SideOnly(Side.CLIENT)
    @Mod.EventHandler
    public void preInitSideOnly(FMLPreInitializationEvent event) {

    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        PacketHandler.registerPacket();
        JarItem = (JarItem) (new JarItem().setUnlocalizedName("jar"));
        JarItem.setRegistryName("jar");
        ModelResourceLocation location = new ModelResourceLocation(Reference.MOD_ID + ":jar", "inventory");
        ModelLoader.setCustomModelResourceLocation(JarItem, JarItem.getItem().getMetadata(), location);

        ForgeRegistries.ITEMS.register(JarItem);
        CapabilityManager.INSTANCE.register(IJarCapability.class, new Storage(), JarCapability::new);
        MinecraftForge.EVENT_BUS.register(new Events());
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
