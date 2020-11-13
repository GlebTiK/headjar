package com.glebtik.headjar.client;

import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import javax.annotation.Nullable;

public class RenderJar extends RenderLivingBase<EntityLivingBase> {
    private static ResourceLocation textures = new ResourceLocation("headjar:textures/player/jar.png");

    public RenderJar(RenderManager render) {
        super(render, new JarModel(), 0.0F);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityLivingBase EntityLivingBase) {
        return textures;
    }

}
