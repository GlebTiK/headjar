package com.glebtik.headjar.entity.render;

import com.glebtik.headjar.entity.HeadlessSkeleton;
import com.glebtik.headjar.entity.model.HeadlessSkeletonModel;

import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderHeadlessSkeleton extends RenderLivingBase<HeadlessSkeleton> {

    public static final ResourceLocation TEXTURES = new ResourceLocation(
            "minecraft:textures/entity/skeleton/skeleton.png");

    public RenderHeadlessSkeleton(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new HeadlessSkeletonModel(), 0.5F);
    }

    @Override
    protected ResourceLocation getEntityTexture(HeadlessSkeleton entity) {
        return TEXTURES;
    }
    
}
