package com.headinajar.glebtik;

import java.io.IOException;

import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class Message implements IMessage {
    private NBTTagCompound data;
    public byte toSend;

    public Message() {

    }

    public Message(NBTTagCompound data) {
        this.data = data;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        PacketBuffer pbuf = new PacketBuffer(buf);
        toSend = buf.readByte();
        try {
            data = pbuf.readCompoundTag();
        } catch (IOException e) {
            System.out.println("well aint this workin");
            e.printStackTrace();
        }
    }

    @Override
    public void toBytes(ByteBuf buf){
        PacketBuffer pbuf = new PacketBuffer(buf);
        buf.writeByte(toSend);
        pbuf.writeCompoundTag(data);
    }

}