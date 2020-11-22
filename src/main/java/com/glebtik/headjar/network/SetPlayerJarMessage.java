package com.glebtik.headjar.network;

import com.glebtik.headjar.jars.IJar;
import com.glebtik.headjar.jars.HeadJar;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.util.UUID;

import static com.glebtik.headjar.capabilities.JarProvider.JAR;

public class SetPlayerJarMessage extends AbstractMessage<SetPlayerJarMessage> {

    private IJar jar;
    private UUID uuid;
    public SetPlayerJarMessage() {

    }
    private SetPlayerJarMessage(IJar jar, UUID uuid) {
        this.jar = jar;
        this.uuid = uuid;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.jar = MessageBufferUtils.readJar(buf);
        this.uuid = MessageBufferUtils.readUUID(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        MessageBufferUtils.writeJar(buf, jar);
        MessageBufferUtils.writeUUID(buf, uuid);
    }

    @Override
    protected boolean validate() {
        return true;
    }

    @Override
    protected IMessage handle(MessageContext ctx) {
        Minecraft.getMinecraft().addScheduledTask(() -> {
            try {
                EntityPlayer player = Minecraft.getMinecraft().world.getPlayerEntityByUUID(uuid);
                player.getCapability(JAR,null).setJar(jar);
            } catch (IllegalStateException e) {
                System.out.println("ERROR: Capability was not attached to client player");
                e.printStackTrace();
            }
        });
        return null;
    }
    public static SetPlayerJarMessage create(EntityPlayerMP player) {
        return new SetPlayerJarMessage(player.getCapability(JAR, null).getJar(), player.getUniqueID());
    }
}
