package com.headinajar.glebtik;
import com.headinajar.glebtik.util.Reference;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;


@Mod(modid = Reference.MOD_ID, version = Reference.VERSION, name = Reference.NAME)
public class HeadInAJar {

    @Mod.Instance
    public static HeadInAJar Instance;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        StartupCommon.preInitCommon();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        StartupCommon.postInitCommon();
    }
    @Mod.EventHandler
    public void Init(FMLInitializationEvent event) {
        StartupCommon.initCommon();
    }
    @Mod.EventHandler
    public void ServerInit(FMLServerStartingEvent event) {
        
    }
}
