package com.glebtik.headjar.jars;

import com.glebtik.headjar.util.Reference;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;

public class NoJar implements IJar {

    @Override
    public boolean shouldRenderPlayer() {
        return true;
    }

    @Override
    public void doRender(EntityPlayer player, float partialRenderTick, RenderPlayer playerRenderer) {

    }

    @Override
    public void writeNBT(NBTTagCompound nbt) {

    }

    @Override
    public void readNBT(NBTTagCompound nbt) {

    }

    @Override
    public void writeByteBuf(ByteBuf buf) {

    }

    @Override
    public void readByteBuf(ByteBuf buf) {

    }

    @Override
    public ResourceLocation getRegistryName() {
        return new ResourceLocation(Reference.MOD_ID,"no_jar");
    }

    @Override
    public void updateHitbox(EntityPlayer player) {
        player.height = 1.8f;
        player.width = 0.6f;
        player.eyeHeight = 1.62f;
        player.setEntityBoundingBox(new AxisAlignedBB(player.posX - 0.3, player.posY, player.posZ - 0.3, player.posX + 0.3, player.posY + 1.8, player.posZ + 0.3));
    }
}
