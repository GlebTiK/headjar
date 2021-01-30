package com.glebtik.headjar.jars;

import java.util.Map;
import java.util.UUID;

import com.glebtik.headjar.util.Reference;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

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
        return new ResourceLocation(Reference.MOD_ID, "no_jar");
    }

    @Override
    public float getWidth() {
        return 0.6f;
    }

    @Override
    public float getHeight() {
        return 1.8f;
    }

    @Override
    public float getEyeHeight() {
        return 1.62f;
    }

    @Override
    public boolean getAbility(String a) {
        return false;
    }

    @Override
    public void setAbility(String a, boolean b) {

    }

    @Override
    public Map<String, Boolean> getAbils() {
        return null;
    }

    @Override
    public String transform() {
        return null;
    }

    @Override
    public void setTransfrom(String a) {

    }

    @Override
    public UUID getPlayerBodyUuid() {
        return null;
    }

    @Override
    public void setPlayerBodyUuid(UUID a) {

    }
}
