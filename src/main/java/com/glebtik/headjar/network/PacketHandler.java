package com.glebtik.headjar.network;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class PacketHandler {
    private static int ID = 0;

    private PacketHandler() {

    }

    public static int getID() {
        return ID++;
    }

    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel("headjar");
    public static void registerPacket() {
        INSTANCE.registerMessage(SetPlayerJarMessage.class, SetPlayerJarMessage.class, getID(), Side.CLIENT);
    }
}
//