package com.headinajar.glebtik;
import com.headinajar.glebtik.util.Reference;
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


@Mod(modid = Reference.MOD_ID, version = Reference.VERSION, name = Reference.NAME)
public class HeadInAJar {
    public static JarItem JarItem;

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
        JarItem = (JarItem) (new JarItem().setUnlocalizedName("jar"));
        JarItem.setRegistryName("jar");
        ForgeRegistries.ITEMS.register(JarItem);
        CapabilityManager.INSTANCE.register(IJar.class, new Storage(), Jar.class);
        MinecraftForge.EVENT_BUS.register(new PlayerModeler());
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
