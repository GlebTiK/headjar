package com.glebtik.headjar.network;

import com.glebtik.headjar.jars.IJar;
import com.glebtik.headjar.jars.JarRegistry;
import com.glebtik.headjar.util.Color;
import io.netty.buffer.ByteBuf;
import net.minecraft.util.ResourceLocation;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
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

    public static void writeColor(ByteBuf buf, Color color) {
        buf.writeInt(color.colorValue);
    }

    public static Color readColor(ByteBuf buf) {
        int colorValue = buf.readInt();
        for(Color color: Color.values()){
            if(color.colorValue == colorValue) {
                return color;
            }
        }
        return Color.BLANK;
    }

    public static void writeAbility(ByteBuf buf, String a, boolean ab) {
        buf.writeInt(a.length());
        buf.writeCharSequence(a, StandardCharsets.UTF_8);
        buf.writeBoolean(ab);
    }
    
    public static HashMap<String, Boolean> readAbility(ByteBuf buf) {
        int l = buf.readInt();
        String a = buf.readCharSequence(l, StandardCharsets.UTF_8).toString();
        boolean ab = buf.readBoolean();
        Map<String, Boolean> abil = new HashMap<String, Boolean>();
        abil.put(a,ab);
        return (HashMap<String, Boolean>) abil;//
    }
}
