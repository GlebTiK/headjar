package com.glebtik.headjar.client.render.head;

import com.glebtik.headjar.util.Color;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import javax.annotation.Nullable;

public class RenderJar extends RenderLivingBase<EntityLivingBase> {
    private static ResourceLocation textures = new ResourceLocation("headjar:textures/player/jar.png");

    public RenderJar(RenderManager render) {
        super(render, new JarModel(), 0.0F);
    }

    public void doRender(EntityLivingBase entity, double x, double y, double z, float entityYaw, float partialTicks, Color color) {
        updateEntityTexture(color);

        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityLivingBase EntityLivingBase) {
        return textures;
    }

    private void updateEntityTexture(Color color) {
        textures = new ResourceLocation("headjar:textures/items/" + color.prefix + "jar.png");
    }
}
