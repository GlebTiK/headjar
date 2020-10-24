package com.headinajar.glebtik;

import com.headinajar.glebtik.util.Reference;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class JarProvider implements ICapabilitySerializable<NBTBase>
{
    @CapabilityInject(IJar.class)
    public static final Capability<IJar> JAR = null;

    private IJar instance = JAR.getDefaultInstance();

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing)
    {
        return capability == JAR;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing)
    {
        return capability == JAR ? JAR.cast(this.instance) : null;
    }

    @Override
    public NBTBase serializeNBT()
    {
        return JAR.getStorage().writeNBT(JAR, this.instance, null);
    }

    @Override
    public void deserializeNBT(NBTBase nbt)
    {
        JAR.getStorage().readNBT(JAR, this.instance, null, nbt);
    }
}