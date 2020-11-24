package com.glebtik.headjar.jars;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public interface IJar {
    boolean shouldRenderPlayer();
    void doRender(EntityPlayer player, float partialRenderTick, RenderPlayer playerRenderer);
    void writeNBT(NBTTagCompound nbt);
    void readNBT(NBTTagCompound nbt);
    void writeByteBuf(ByteBuf buf);
    void readByteBuf(ByteBuf buf);
    ResourceLocation getRegistryName();
    float getHeight();
    float getWidth();
    float getEyeHeight();
    default void clientTick(TickEvent.PlayerTickEvent event) {}
    default void serverTick(TickEvent.PlayerTickEvent event) {}
    default void updateHitBox(EntityPlayer player) {
        player.setEntityBoundingBox( new AxisAlignedBB(player.posX-getWidth()/2, player.posY, player.posZ-getWidth()/2, player.posX+getWidth()/2, player.posY+getHeight(), player.posZ+getWidth()/2));
        player.height = getHeight();
        player.width = getWidth();
        player.eyeHeight = getEyeHeight();
        if(player.isRiding()) {
            player.height = player.height + 0.48f;
            player.eyeHeight = player.eyeHeight + 0.25f;
        }
    }
    default void onRemove() {
    }
}
