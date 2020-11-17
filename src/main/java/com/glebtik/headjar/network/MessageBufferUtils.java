package com.glebtik.headjar.network;

import com.glebtik.headjar.jars.IJar;
import com.glebtik.headjar.jars.HeadJar;
import com.glebtik.headjar.jars.JarRegistry;
import io.netty.buffer.ByteBuf;
import net.minecraft.util.ResourceLocation;

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

    public static IJar readJar(ByteBuf buf) {
        IJar jar = JarRegistry.getByRegistryName(readResourceLocation(buf));
        jar.readByteBuf(buf);
        return jar;
    }
    public static void writeJar(ByteBuf buf, IJar jar) {
        writeResourceLocation(buf, jar.getRegistryName());
        jar.writeByteBuf(buf);
    }
    public static void writeResourceLocation(ByteBuf buf, ResourceLocation resourceLocation) {
        buf.writeInt(resourceLocation.getResourceDomain().getBytes().length);
        buf.writeBytes(resourceLocation.getResourceDomain().getBytes());
        buf.writeInt(resourceLocation.getResourcePath().getBytes().length);
        buf.writeBytes(resourceLocation.getResourcePath().getBytes());
    }
    public static ResourceLocation readResourceLocation(ByteBuf buf) {
        byte[] domainBytes = new byte[buf.readInt()];
        buf.readBytes(domainBytes);
        String domain = new String(domainBytes);
        byte[] pathBytes = new byte[buf.readInt()];
        buf.readBytes(pathBytes);
        String path = new String(pathBytes);
        return new ResourceLocation(domain, path);
    }
}
