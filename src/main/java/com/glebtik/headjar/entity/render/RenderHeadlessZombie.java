package com.glebtik.headjar.entity.render;

import com.glebtik.headjar.entity.HeadlessZombie;
import com.glebtik.headjar.entity.model.HeadlessZombieModel;
import com.glebtik.headjar.util.Reference;

import net.minecraft.client.renderer.entity.RenderZombie;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderHeadlessZombie extends RenderLivingBase<HeadlessZombie> {

    public static final ResourceLocation TEXTURES = new ResourceLocation(
            "minecraft:textures/entity/zombie/zombie.png");

    public RenderHeadlessZombie(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new HeadlessZombieModel(), 0.5F);
    }

    @Override
    protected ResourceLocation getEntityTexture(HeadlessZombie entity) {
        return TEXTURES;
    }
    
}