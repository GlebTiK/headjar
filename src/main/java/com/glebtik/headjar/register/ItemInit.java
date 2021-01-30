package com.glebtik.headjar.register;

import com.glebtik.headjar.items.FireProt;
import com.glebtik.headjar.items.JarItem;
import com.glebtik.headjar.items.Prot;
import com.glebtik.headjar.items.TransfromAbil;
import com.glebtik.headjar.items.WaterProt;
import com.glebtik.headjar.util.Color;
import com.glebtik.headjar.util.Reference;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
// import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class ItemInit {
    public static final JarItem JAR = new JarItem(Color.BLANK);
    public static final JarItem WHITE_JAR = new JarItem(Color.WHITE);
    public static final JarItem ORANGE_JAR = new JarItem(Color.ORANGE);
    public static final JarItem MAGENTA_JAR = new JarItem(Color.MAGENTA);
    public static final JarItem LIGHT_BLUE_JAR = new JarItem(Color.LIGHT_BLUE);
    public static final JarItem YELLOW_JAR = new JarItem(Color.YELLOW);
    public static final JarItem LIME_JAR = new JarItem(Color.LIME);
    public static final JarItem PINK_JAR = new JarItem(Color.PINK);
    public static final JarItem GRAY_JAR = new JarItem(Color.GRAY);
    public static final JarItem LIGHT_GRAY_JAR = new JarItem(Color.LIGHT_GRAY);
    public static final JarItem CYAN_JAR = new JarItem(Color.CYAN);
    public static final JarItem PURPLE_JAR = new JarItem(Color.PURPLE);
    public static final JarItem BLUE_JAR = new JarItem(Color.BLUE);
    public static final JarItem BROWN_JAR = new JarItem(Color.BROWN);
    public static final JarItem GREEN_JAR = new JarItem(Color.GREEN);
    public static final JarItem RED_JAR = new JarItem(Color.RED);
    public static final JarItem BLACK_JAR = new JarItem(Color.BLACK);
    public static final Item PROTECTION_MOD = new Prot();
    public static final Item WATER_PROTECTION_MOD = new WaterProt();
    public static final Item FIRE_PROTECTION_MOD = new FireProt();
    public static final Item TRANSFORM_ABILITY = new TransfromAbil();

    @SubscribeEvent
    public static void onItemRegister(RegistryEvent.Register<Item> event) {
        registerJar(JAR, event);
        registerJar(WHITE_JAR, event);
        registerJar(ORANGE_JAR, event);
        registerJar(MAGENTA_JAR, event);
        registerJar(LIGHT_BLUE_JAR, event);
        registerJar(YELLOW_JAR, event);
        registerJar(LIME_JAR, event);
        registerJar(PINK_JAR, event);
        registerJar(GRAY_JAR, event);
        registerJar(LIGHT_GRAY_JAR, event);
        registerJar(CYAN_JAR, event);
        registerJar(PURPLE_JAR, event);
        registerJar(BLUE_JAR, event);
        registerJar(BROWN_JAR, event);
        registerJar(GREEN_JAR, event);
        registerJar(RED_JAR, event);
        registerJar(BLACK_JAR, event);
        registerItem(PROTECTION_MOD, event);
        registerItem(WATER_PROTECTION_MOD, event);
        registerItem(FIRE_PROTECTION_MOD, event);
        registerItem(TRANSFORM_ABILITY, event);
    }

    private static void registerJar(JarItem item, RegistryEvent.Register<Item> event) {
        ModelResourceLocation location = new ModelResourceLocation(Reference.MOD_ID + ":" + item.color.prefix + "jar", "inventory"); 
        ModelLoader.setCustomModelResourceLocation(item, item.getItem().getMetadata(), location);
        event.getRegistry().register(item);
    }

    public static void registerItem(Item item, RegistryEvent.Register<Item> event) {
        ModelResourceLocation location = new ModelResourceLocation(item.getRegistryName(), "inventory");
        ModelLoader.setCustomModelResourceLocation(item, item.getMetadata(0), location);
        event.getRegistry().register(item);
    }
}
