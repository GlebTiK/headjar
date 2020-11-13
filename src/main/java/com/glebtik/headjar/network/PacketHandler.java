package com.glebtik.headjar.network;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class PacketHandler {
    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel("headjar");
    public static void registerPacket() {
        INSTANCE.registerMessage(MessageHandler.class, Message.class, 0, Side.CLIENT);
        INSTANCE.registerMessage(MessageHandler.class, Message.class, 0, Side.SERVER);
    }
}
//