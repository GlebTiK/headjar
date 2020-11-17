package com.glebtik.headjar.register;

import com.glebtik.headjar.items.JarItem;
import com.glebtik.headjar.util.Color;
import com.glebtik.headjar.util.Reference;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
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

    @SubscribeEvent
    public static void onItemRegister(RegistryEvent.Register<Item> event) {
        registerItem(JAR, event);
        registerItem(WHITE_JAR, event);
        registerItem(ORANGE_JAR, event);
        registerItem(MAGENTA_JAR, event);
        registerItem(LIGHT_BLUE_JAR, event);
        registerItem(YELLOW_JAR, event);
        registerItem(LIME_JAR, event);
        registerItem(PINK_JAR, event);
        registerItem(GRAY_JAR, event);
        registerItem(LIGHT_GRAY_JAR, event);
        registerItem(CYAN_JAR, event);
        registerItem(PURPLE_JAR, event);
        registerItem(BLUE_JAR, event);
        registerItem(BROWN_JAR, event);
        registerItem(GREEN_JAR, event);
        registerItem(RED_JAR, event);
        registerItem(BLACK_JAR, event);
    }

    private static void registerItem(JarItem item, RegistryEvent.Register<Item> event) {
        ModelResourceLocation location = new ModelResourceLocation(Reference.MOD_ID + ":" + item.color.prefix + "jar", "inventory");
        ModelLoader.setCustomModelResourceLocation(item, item.getItem().getMetadata(), location);
        event.getRegistry().register(item);
    }
}
