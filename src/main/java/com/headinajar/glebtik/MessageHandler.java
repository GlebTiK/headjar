package com.headinajar.glebtik;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageHandler implements IMessageHandler<Message, IMessage> {
    // Do note that the default constructor is required, but implicitly defined in this case

    @Override public IMessage onMessage(Message message, MessageContext ctx) {
        // This is the player the packet was sent to the server from
        EntityPlayerMP serverPlayer = ctx.getServerHandler().player;
        // The value that was sent
        byte amount = message.toSend;
        // Execute the action on the main server thread by adding it as a scheduled task
        serverPlayer.getServerWorld().addScheduledTask(() -> {
//            serverPlayer.inventory.addItemStackToInventory(new ItemStack(Items.DIAMOND, amount));
        });
        // No response packet
        return null;
    }
}