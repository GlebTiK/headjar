package com.glebtik.headjar.network;

import com.glebtik.headjar.capabilities.Jar;
import io.netty.buffer.ByteBuf;
import java.util.UUID;

public class MessageBufferUtils {

    private MessageBufferUtils() {}

    public static UUID readUUID(ByteBuf buf) {
        return new UUID(buf.readLong(), buf.readLong());
    }
    public static void writeUUID(ByteBuf buf, UUID uuid) {
        buf.writeLong(uuid.getMostSignificantBits());
        buf.writeLong(uuid.getLeastSignificantBits());
    }

    public static Jar readJar(ByteBuf buf) {
        Jar jar = new Jar();
        jar.setJar(buf.readBoolean());
        return jar;
    }
    public static void writeJar(ByteBuf buf, Jar jar) {
        buf.writeBoolean(jar.isJar());
    }
}
