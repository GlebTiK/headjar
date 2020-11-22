package com.glebtik.headjar.jars;

import com.glebtik.headjar.client.render.head.RenderHead;
import com.glebtik.headjar.client.render.head.RenderJar;
import com.glebtik.headjar.network.MessageBufferUtils;
import com.glebtik.headjar.util.Color;
import com.glebtik.headjar.util.Reference;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;

public class HeadJar implements IJar {

    protected final RenderJar rendererJar = new RenderJar(Minecraft.getMinecraft().getRenderManager());
    protected RenderHead rendererHead = new RenderHead(Minecraft.getMinecraft().getRenderManager());
    private Color color = Color.BLANK;

    protected float headXOff;
    protected float headYOff;
    protected float headZOff;

    protected double renderXOff;
    protected double renderYOff;
    protected double renderZOff;

    public void setColor(Color color) {
        this.color = color;
    }
    public Color getColor() {
        return color;
    }
    @Override
    public boolean shouldRenderPlayer() {
        return false;
    }

    @Override
    public void doRender(EntityPlayer player, float partialRenderTick, RenderPlayer playerRenderer) {
        updateRenderOffset(player, partialRenderTick);
        rendererHead.setEntityTexture(playerRenderer.getEntityTexture((AbstractClientPlayer) player));

        if (!player.isRiding()) {
            rendererHead.doRender(player, headXOff + renderXOff, headYOff + renderYOff, headZOff + renderZOff, player.getRotationYawHead(), partialRenderTick);
            rendererJar.doRender(player, headXOff + renderXOff, headYOff + renderYOff, headZOff + renderZOff, player.getRotationYawHead(), partialRenderTick, color);
        } else {
            rendererJar.doRender(player, headXOff + renderXOff, headYOff + renderYOff + 0.5, headZOff + renderZOff, 0, partialRenderTick, color);
            rendererHead.doRender(player, headXOff + renderXOff, headYOff + renderYOff + 0.5, headZOff + renderZOff, player.getRotationYawHead(), partialRenderTick);
        }
    }
    private void updateRenderOffset(EntityPlayer player, float partialRenderTick) {
        renderXOff = getPartialValue(partialRenderTick, player.posX, player.prevPosX) - getPartialValue(partialRenderTick, Minecraft.getMinecraft().player.posX, Minecraft.getMinecraft().player.prevPosX);
        renderYOff = getPartialValue(partialRenderTick, player.posY, player.prevPosY) - getPartialValue(partialRenderTick, Minecraft.getMinecraft().player.posY, Minecraft.getMinecraft().player.prevPosY);
        renderZOff = getPartialValue(partialRenderTick, player.posZ, player.prevPosZ) - getPartialValue(partialRenderTick, Minecraft.getMinecraft().player.posZ, Minecraft.getMinecraft().player.prevPosZ);

    }
    private double getPartialValue(float partialRenderTick, double newValue, double oldValue) {
        return newValue*partialRenderTick + oldValue*(1-partialRenderTick);
    }
    @Override
    public void writeNBT(NBTTagCompound nbt) {
        nbt.setInteger("jarcolor", color.colorValue);
    }

    @Override
    public void readNBT(NBTTagCompound nbt) {
        int colorValue = nbt.getInteger("jarcolor");
        for(Color c: Color.values()) {
            if(c.colorValue == colorValue) {
                setColor(c);
                return;
            }
        }
        setColor(Color.BLANK);
    }

    @Override
    public void writeByteBuf(ByteBuf buf) {
        MessageBufferUtils.writeColor(buf, color);
    }

    @Override
    public void readByteBuf(ByteBuf buf) {
        color = MessageBufferUtils.readColor(buf);
    }

    @Override
    public ResourceLocation getRegistryName() {
        return new ResourceLocation(Reference.MOD_ID, "head_jar");
    }

    @Override
    public float getHeight() {
        return 0.75f;
    }

    @Override
    public float getWidth() {
        return 0.7f;
    }

    @Override
    public float getEyeHeight() {
        return 0.5f;
    }
    public boolean canModify() {
        return true;
    }
}
