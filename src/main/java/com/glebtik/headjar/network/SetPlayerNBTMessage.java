package com.glebtik.headjar.network;

import com.glebtik.headjar.capabilities.IJar;
import com.glebtik.headjar.capabilities.Jar;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.util.UUID;

import static com.glebtik.headjar.capabilities.JarProvider.JAR;

public class SetPlayerNBTMessage extends AbstractMessage<SetPlayerNBTMessage> {
    //represent MessageType1 in Old System
    private Jar jar;
    private UUID uuid;
    public SetPlayerNBTMessage() {}
    private SetPlayerNBTMessage(Jar jar, UUID uuid) {
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
            IJar cap;
            try {
                cap = Minecraft.getMinecraft().world.getPlayerEntityByUUID(uuid).getCapability(JAR,
                        null);
                cap.copyInto(jar);
            } catch (IllegalStateException e) {
                System.out.println("ERROR: Capability was not attached to client player");
                e.printStackTrace();
            }
        });
        return null;
    }
    public static SetPlayerNBTMessage create(EntityPlayerMP player) {
        return new SetPlayerNBTMessage((Jar)player.getCapability(JAR, null), player.getUniqueID());
    }
}
