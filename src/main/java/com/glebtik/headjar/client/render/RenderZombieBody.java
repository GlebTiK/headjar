package com.glebtik.headjar.client.render;

import com.glebtik.headjar.entity.model.HeadlessZombieModel;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderZombieBody extends RenderLivingBase<EntityLivingBase> {
    public static final ResourceLocation TEXTURES = new ResourceLocation(
            "minecraft:textures/entity/zombie/zombie.png");

    public RenderZombieBody(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new HeadlessZombieModel(), 0.5F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityLivingBase entity) {
        return TEXTURES;
    }
}
