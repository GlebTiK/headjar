package com.glebtik.headjar.jars;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

public interface IJar {
    boolean shouldRenderPlayer();
    void doRender(EntityPlayer player, float partialRenderTick, RenderPlayer playerRenderer);
    void writeNBT(NBTTagCompound nbt);
    void readNBT(NBTTagCompound nbt);
    void writeByteBuf(ByteBuf buf);
    void readByteBuf(ByteBuf buf);
    ResourceLocation getRegistryName();
    void updateHitbox(EntityPlayer player);
}
