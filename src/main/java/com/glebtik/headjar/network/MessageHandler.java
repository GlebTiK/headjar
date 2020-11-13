package com.glebtik.headjar.network;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.management.PlayerList;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import static com.glebtik.headjar.capabilities.JarProvider.JAR;

import com.glebtik.headjar.capabilities.IJar;

public class MessageHandler implements IMessageHandler<Message, IMessage> {
    // Do note that the default constructor is required, but implicitly defined in
    // this case

    @Override
    public IMessage onMessage(Message message, MessageContext ctx) {
        if (ctx.side == Side.SERVER) {
            if (message.type == 3) {
                PlayerList players = FMLCommonHandler.instance().getMinecraftServerInstance().getPlayerList();
                Message msg = new Message(players.getPlayerByUUID(message.UniqueID).getCapability(JAR, null).isJar(),
                        message.UniqueID);
                System.out.println("3+" + players.getPlayerByUUID(message.UniqueID).getCapability(JAR, null).isJar());
                PacketHandler.INSTANCE.sendTo(msg, players.getPlayerByUUID(message.UniqueID2));
                System.out.println("message=3, recieved, should send 1");
            }
        } else if (ctx.side == Side.CLIENT) {
            if (message.type == 1) {
                Minecraft.getMinecraft().addScheduledTask(() -> {
                    IJar cap;
                    try {
                        cap = Minecraft.getMinecraft().world.getPlayerEntityByUUID(message.UniqueID).getCapability(JAR,
                                null);
                        cap.setJar(message.isJar);
                        System.out.println("1+" + cap.isJar());
                    } catch (IllegalStateException e) {
                        System.out.println("Capability was not attached to client player");
                        System.out.println(e);
                    }
                });
            } else if (message.type == 2) {
                EntityPlayer player = Minecraft.getMinecraft().world
                        .getPlayerEntityByUUID(Minecraft.getMinecraft().player.getUniqueID());
                Message msg = new Message(player.getUniqueID(), message.UniqueID);
                System.out.println("2+" + player.getCapability(JAR, null).isJar());
                PacketHandler.INSTANCE.sendToServer(msg);
                System.out.println("message=2, should send 3");
            }
        }
        return null;
    }
}