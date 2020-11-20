package com.glebtik.headjar.jars;

import com.glebtik.headjar.client.RenderHead;
import com.glebtik.headjar.client.RenderJar;
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

    private final RenderJar rendererJar = new RenderJar(Minecraft.getMinecraft().getRenderManager());
    private RenderHead rendererHead = new RenderHead(Minecraft.getMinecraft().getRenderManager());
    private Color color = Color.BLANK;

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public boolean shouldRenderPlayer() {
        return false;
    }

    @Override
    public void doRender(EntityPlayer player, float partialRenderTick, RenderPlayer playerRenderer) {
        double x = player.posX - Minecraft.getMinecraft().player.posX;
        double y = player.posY - Minecraft.getMinecraft().player.posY;
        double z = player.posZ - Minecraft.getMinecraft().player.posZ;

        rendererHead.setEntityTexture(playerRenderer.getEntityTexture((AbstractClientPlayer) player));

        if (!player.isRiding()) {
            rendererHead.doRender(player, x, y, z, player.getRotationYawHead(), partialRenderTick);
            rendererJar.doRender(player, x, y, z, player.getRotationYawHead(), partialRenderTick, color);
        } else {
            rendererJar.doRender(player, x, y + 0.5, z, 0, partialRenderTick, color);
            rendererHead.doRender(player, x, y + 0.5, z, player.getRotationYawHead(), partialRenderTick);
        }
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
    public void updateHitbox(EntityPlayer player) {
        player.setEntityBoundingBox(new AxisAlignedBB(player.posX - 0.35, player.posY, player.posZ - 0.35, player.posX + 0.35, player.posY + 0.75, player.posZ + 0.35));

        player.height = 0.75f;
        player.width = 0.7f;
        player.eyeHeight = 0.5f;
        if(player.isRiding()) {
            player.height = 1.23f;
            player.eyeHeight = 0.75f;
        }

    }
}
