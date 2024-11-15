package com.glebtik.headjar.client.render;

import net.minecraft.util.ResourceLocation;
import com.glebtik.headjar.entity.model.HeadlessCreeperModel;

import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;

public class RenderCreeperBody extends RenderLivingBase<EntityLivingBase> {
    public static final ResourceLocation TEXTURES = new ResourceLocation(
        "minecraft:textures/entity/creeper/creeper.png");

public RenderCreeperBody(RenderManager rendermanagerIn) {
    super(rendermanagerIn, new HeadlessCreeperModel(), 0.5F);
}

@Override
protected ResourceLocation getEntityTexture(EntityLivingBase entity) {
    return TEXTURES;
}

}
