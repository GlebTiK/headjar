package com.glebtik.headjar.network;

import java.util.UUID;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class Message implements IMessage {
    public int type;
    public boolean isJar;
    public UUID UniqueID;
    public UUID UniqueID2;

    public Message() {

    }

    public Message(boolean j, UUID u) {
        type=1;
        isJar = j;
        UniqueID = u;
    }

    public Message(UUID u) {
        type=2;
        UniqueID = u;
    }

    public Message(UUID u, UUID u2) {
        type=3;
        UniqueID = u;
        UniqueID2 = u2;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        type = buf.readInt();
        if(type==1||type==3)isJar = buf.readByte() == 1;
        UniqueID = new UUID(buf.readLong(), buf.readLong());
        if(type==3)UniqueID2 = new UUID(buf.readLong(), buf.readLong());
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(type);
        if(type==1||type==3)buf.writeByte(isJar ? 1 : 0);
        buf.writeLong(UniqueID.getMostSignificantBits());
        buf.writeLong(UniqueID.getLeastSignificantBits());
        if(type==3)buf.writeLong(UniqueID2.getMostSignificantBits());
        if(type==3)buf.writeLong(UniqueID2.getLeastSignificantBits());
    }

}