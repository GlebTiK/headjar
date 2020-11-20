package com.glebtik.headjar.jars;

import com.glebtik.headjar.util.Reference;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

public class IronGolemJar extends HeadJar {
    public(HeadJar oldJar) {
        this.
    }

    @Override
    public void doRender(EntityPlayer player, float partialRenderTick, RenderPlayer playerRenderer) {
        super.doRender(player, partialRenderTick, playerRenderer);


    }

    @Override
    public void writeNBT(NBTTagCompound nbt) {
        super.writeNBT(nbt);
    }

    @Override
    public void readNBT(NBTTagCompound nbt) {
        super.readNBT(nbt);
    }

    @Override
    public void writeByteBuf(ByteBuf buf) {
        super.writeByteBuf(buf);
    }

    @Override
    public void readByteBuf(ByteBuf buf) {
        super.readByteBuf(buf);
    }

    @Override
    public ResourceLocation getRegistryName() {
        return new ResourceLocation(Reference.MOD_ID, "iron_golem_jar");
    }

    @Override
    public void updateHitbox(EntityPlayer player) {
        super.updateHitbox(player);
    }
}
