package com.glebtik.headjar.client;

import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import javax.annotation.Nullable;

public class RenderHead extends RenderLivingBase<EntityLivingBase> {
    private static ResourceLocation textures = null;

    public RenderHead(RenderManager render, ResourceLocation texture) {
        super(render, new HeadModel(), 0.0F);
        textures = texture;
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityLivingBase EntityLivingBase) {
        return textures;
    }
    
}
