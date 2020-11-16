package com.glebtik.headjar.capabilities;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class JarProvider implements ICapabilitySerializable<NBTBase>
{
    @CapabilityInject(IJarCapability.class)
    public static final Capability<IJarCapability> JAR = null;

    private IJarCapability instance = JAR.getDefaultInstance();

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