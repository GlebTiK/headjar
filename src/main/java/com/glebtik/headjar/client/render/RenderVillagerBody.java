package com.glebtik.headjar.client.render;

import com.glebtik.headjar.entity.HeadlessVillager;
import com.glebtik.headjar.entity.model.HeadlessVillagerModel;

import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderVillagerBody extends RenderLivingBase<EntityLivingBase> {
    
    private ResourceLocation textures = new ResourceLocation("minecraft:textures/entity/villager/villager.png");

    public RenderVillagerBody(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new HeadlessVillagerModel(0.0F), 0.5F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityLivingBase entity) {
        return textures;
    }

    public void updateEntityTexture(ResourceLocation r) {
        textures = r;
    }

}
