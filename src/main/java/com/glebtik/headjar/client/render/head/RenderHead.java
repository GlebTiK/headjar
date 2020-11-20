package com.glebtik.headjar.client.render.head;

import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import javax.annotation.Nullable;

public class RenderHead extends RenderLivingBase<EntityLivingBase> {
    private static ResourceLocation textures = null;

    public RenderHead(RenderManager render) {
        super(render, new HeadModel(), 0.0F);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityLivingBase EntityLivingBase) {
        return textures;

    }

    public void setEntityTexture(ResourceLocation texture) {
        textures = texture;
    }
}
