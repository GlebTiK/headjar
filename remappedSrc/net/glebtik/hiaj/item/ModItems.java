package net.glebtik.hiaj.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.glebtik.hiaj.HeadInAJar;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {
    public static final Item JAR = registerJar("");
    public static final Item WHITE_JAR = registerJar("white");
    public static final Item ORANGE_JAR = registerJar("orange");
    public static final Item MAGENTA_JAR = registerJar("magenta");
    public static final Item LIGHT_BLUE_JAR = registerJar("light_blue");
    public static final Item YELLOW_JAR = registerJar("yellow");
    public static final Item LIME_JAR = registerJar("lime");
    public static final Item PINK_JAR = registerJar("pink");
    public static final Item GRAY_JAR = registerJar("gray");
    public static final Item LIGHT_GRAY_JAR = registerJar("light_gray");
    public static final Item CYAN_JAR = registerJar("cyan");
    public static final Item PURPLE_JAR = registerJar("purple");
    public static final Item BLUE_JAR = registerJar("blue");
    public static final Item BROWN_JAR = registerJar("brown");
    public static final Item GREEN_JAR = registerJar("green");
    public static final Item RED_JAR = registerJar("red");
    public static final Item BLACK_JAR = registerJar("black");
    public static void registerItems() {
        HeadInAJar.LOGGER.debug("Registering items for " + HeadInAJar.MODID);
    }
    private static Item registerJar(String color) {
        return registerItem("".equals(color) ? "jar" : color + "_jar", new Item(new FabricItemSettings().group(ItemGroup.REDSTONE).maxCount(1)));
    }
    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(HeadInAJar.MODID, name), item);
    }
}
