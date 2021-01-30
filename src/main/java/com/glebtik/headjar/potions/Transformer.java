package com.glebtik.headjar.potions;

import com.glebtik.headjar.util.Reference;

import net.minecraft.client.Minecraft;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

public class Transformer extends Potion {

    public Transformer(boolean isBadEffectIn, int liquidColorIn) {
        super(isBadEffectIn, liquidColorIn);
        this.setIconIndex(0,0);
    }

    @Override
    public boolean hasStatusIcon() {
        Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(Reference.MOD_ID + ":textures/gui/potion_effect.png"));
        return true;
    }
}