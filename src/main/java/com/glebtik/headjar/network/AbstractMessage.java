package com.glebtik.headjar.network;

import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public abstract class AbstractMessage<THIS extends AbstractMessage> implements IMessage, IMessageHandler<THIS, IMessage> {
    abstract protected boolean validate();

    @Override
    public IMessage onMessage(THIS message, MessageContext ctx) {
        if(message.validate()) {
            return message.handle(ctx);
        }else{
            if(ctx.side == Side.CLIENT){
                System.out.println("WARN: Server-Packet invalid: " + message.getClass());
                System.out.println("WARN: " + message.toString());
            }else{
                System.out.println("WARN: Client-Packet invalid: " + message.getClass());
                System.out.println("WARN: " + message.toString());
            }
            return null;
        }
    }
    protected abstract IMessage handle(MessageContext ctx);
}
