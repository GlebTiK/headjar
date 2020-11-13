package com.headinajar.glebtik;

import com.headinajar.glebtik.capabilities.IJar;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTPrimitive;
import net.minecraft.nbt.NBTTagByte;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;


public class Storage implements Capability.IStorage<IJar> {

    @Override
    public NBTBase writeNBT(Capability<IJar> capability, IJar instance, EnumFacing side) {
        return new NBTTagByte(instance.isJar());
    }

    @Override
    public void readNBT(Capability<IJar> capability, IJar instance, EnumFacing side, NBTBase nbt) {
        instance.setJar(((NBTPrimitive) nbt).getByte());
    }
    // interesting, how will i add multiple nbts?
}
