package com.headinajar.glebtik;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class PacketHandler {
    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel("headjar");
    public PacketHandler() {
        INSTANCE.registerMessage(MessageHandler.class, Message.class, 0, Side.SERVER);
    }
    public static void sendCapabilityPacket(EntityPlayer player, boolean toClient) {
        NBTTagCompound compound = new NBTTagCompound();

        if(player != null && player.hasCapability(JarProvider.JAR, null)) {
            IJar jar = player.getCapability(JarProvider.JAR, null);
            byte isJar = jar.isJar();
            compound.setInteger("isJar", isJar);

            if(toClient) {
                if(player instanceof EntityPlayerMP) {
                    PacketHandler.INSTANCE.sendTo(new PacketServerToClient(compound, PacketHandler.MANA_CAPABILITY), (EntityPlayerMP)player);
                }
            }
            else {
                PacketHandler.theNetwork.sendToServer(new PacketClientToServer(compound, PacketHandler.MANA_CAPABILITY));
            }
        }
    }
}
