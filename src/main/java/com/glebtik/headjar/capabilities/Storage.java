package com.glebtik.headjar.capabilities;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTPrimitive;
import net.minecraft.nbt.NBTTagByte;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;


public class Storage implements Capability.IStorage<IJar> {

    @Override
    public NBTBase writeNBT(Capability<IJar> capability, IJar instance, EnumFacing side) {
        return new NBTTagByte(instance.isJar()? (byte) 1 : (byte) 0);
    }

    @Override
    public void readNBT(Capability<IJar> capability, IJar instance, EnumFacing side, NBTBase nbt) {
        instance.setJar(((NBTPrimitive) nbt).getByte() == (byte) 1);
    }
    // interesting, how will i add multiple nbts?
}
