package com.glebtik.headjar.entity.render;

import com.glebtik.headjar.entity.HeadlessVillager;
import com.glebtik.headjar.entity.model.HeadlessVillagerModel;

import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderHeadlessVillager extends RenderLivingBase<HeadlessVillager> {

    public RenderHeadlessVillager(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new HeadlessVillagerModel(0.0F), 0.5F);
    }

    @Override
    protected ResourceLocation getEntityTexture(HeadlessVillager entity) {
        return entity.getProfessionForge().getSkin();
    }
    
}
