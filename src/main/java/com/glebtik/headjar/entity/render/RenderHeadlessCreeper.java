package com.glebtik.headjar.entity.render;

import com.glebtik.headjar.entity.HeadlessCreeper;
import com.glebtik.headjar.entity.model.HeadlessCreeperModel;

import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderHeadlessCreeper extends RenderLivingBase<HeadlessCreeper> {

    public static final ResourceLocation TEXTURES = new ResourceLocation(
            "minecraft:textures/entity/creeper/creeper.png");

    public RenderHeadlessCreeper(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new HeadlessCreeperModel(), 0.5F);
    }

    @Override
    protected ResourceLocation getEntityTexture(HeadlessCreeper entity) {
        return TEXTURES;
    }
    
}
