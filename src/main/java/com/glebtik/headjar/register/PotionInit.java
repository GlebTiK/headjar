package com.glebtik.headjar.register;

import com.glebtik.headjar.potions.Transformer;
import com.glebtik.headjar.util.Reference;

import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionHelper;
import net.minecraft.potion.PotionType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class PotionInit {
    public static final Potion TRANSFORM = new Transformer(false, 15741599).setPotionName("effect.transform").setRegistryName(new ResourceLocation(Reference.MOD_ID+":transform"));
    public static final PotionType TRANSFORM_POTION = new PotionType("transform_potion", new PotionEffect[] {new PotionEffect(TRANSFORM, 1200)}).setRegistryName(new ResourceLocation(Reference.MOD_ID+":transform_potion"));
    public static final PotionType TRANSFORM_LONG_POTION = new PotionType("transformer_potion_long", new PotionEffect[] {new PotionEffect(TRANSFORM, 2400)}).setRegistryName(new ResourceLocation(Reference.MOD_ID+":transform_potion_long"));

    public static void regsiterPotions() {
        registerPotion(TRANSFORM_POTION, TRANSFORM_LONG_POTION, TRANSFORM);

        registerPotionMixes();
    }

    private static void registerPotion(PotionType normalPotion, PotionType longPotion, Potion effect) {
        ForgeRegistries.POTIONS.register(effect);
        ForgeRegistries.POTION_TYPES.register(normalPotion);
        ForgeRegistries.POTION_TYPES.register(longPotion);
    }

    private static void registerPotionMixes() {
        PotionHelper.addMix(TRANSFORM_POTION, Items.REDSTONE, TRANSFORM_LONG_POTION);
        PotionHelper.addMix(PotionTypes.AWKWARD, Items.SHULKER_SHELL, TRANSFORM_POTION);
    }
}