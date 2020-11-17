package com.glebtik.headjar.capabilities;

import com.glebtik.headjar.jars.IJar;
import com.glebtik.headjar.jars.JarRegistry;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTPrimitive;
import net.minecraft.nbt.NBTTagByte;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;


public class Storage implements Capability.IStorage<IJarCapability> {

    @Override
    public NBTBase writeNBT(Capability<IJarCapability> capability, IJarCapability instance, EnumFacing side) {
        IJar jar = instance.getJar();
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setString("domain", jar.getRegistryName().getResourceDomain());
        nbt.setString("path", jar.getRegistryName().getResourcePath());
        jar.writeNBT(nbt);
        return nbt;
    }

    @Override
    public void readNBT(Capability<IJarCapability> capability, IJarCapability instance, EnumFacing side, NBTBase nbt) {
        if(nbt instanceof NBTTagCompound) {
            NBTTagCompound nbtTagCompound = (NBTTagCompound) nbt;
            IJar jar = JarRegistry.getByRegistryName(new ResourceLocation(nbtTagCompound.getString("domain"), nbtTagCompound.getString("path")));
            jar.readNBT(nbtTagCompound);
            instance.setJar(jar);
            return;
        }
        System.out.println("could not load JarData");
    }
}
