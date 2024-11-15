package com.glebtik.headjar.client.render;

import com.glebtik.headjar.entity.model.HeadlessSkeletonModel;

import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderSkeletonBody extends RenderLivingBase<EntityLivingBase> {
    public static final ResourceLocation TEXTURES = new ResourceLocation(
            "minecraft:textures/entity/skeleton/skeleton.png");

public RenderSkeletonBody(RenderManager rendermanagerIn) {
    super(rendermanagerIn, new HeadlessSkeletonModel(), 0.5F);
}

    @Override
    protected ResourceLocation getEntityTexture(EntityLivingBase entity) {
        return TEXTURES;
    }

}
