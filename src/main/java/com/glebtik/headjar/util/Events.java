package com.glebtik.headjar.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraft.entity.Entity;
import com.glebtik.headjar.capabilities.JarProvider;
import com.glebtik.headjar.client.HeadModel;
import com.glebtik.headjar.client.JarModel;
import com.glebtik.headjar.client.RenderHead;
import com.glebtik.headjar.client.RenderJar;
import com.glebtik.headjar.network.Message;
import com.glebtik.headjar.network.PacketHandler;

import static com.glebtik.headjar.capabilities.JarProvider.JAR;

import com.glebtik.headjar.capabilities.IJar;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class Events {
    private RenderJar renderer = null;
    private RenderHead rendererHead = null;
    private JarModel model = new JarModel();
    private HeadModel headModel = new HeadModel();

    @SubscribeEvent
    public void onPlayerRenderEvent(RenderPlayerEvent.Pre event) {
        if (renderer == null) {
            renderer = new RenderJar(event.getRenderer().getRenderManager());
        }
        if (rendererHead == null) {
            rendererHead = new RenderHead(event.getRenderer().getRenderManager(),
                    event.getRenderer().getEntityTexture((AbstractClientPlayer) event.getEntityPlayer()));
        }
        EntityPlayer player = event.getEntityPlayer();
        IJar jar = player.getCapability(JAR, null);
        if (jar == null) {
            event.setCanceled(false);
            return;
        }
        if (jar.isJar()) {
            event.setCanceled(true);
            double X = player.posX - Minecraft.getMinecraft().player.posX;
            double Y = player.posY - Minecraft.getMinecraft().player.posY;
            double Z = player.posZ - Minecraft.getMinecraft().player.posZ;
            model.setRotationAngle(0, -(player.getRotationYawHead() / 60), 0);
            headModel.setRotationAngle(0, -(player.getRotationYawHead() / 60), 0);
            if (!player.isRiding()) {
                renderer.doRender(player, X, Y, Z, player.getRotationYawHead(), event.getPartialRenderTick());
                rendererHead.doRender(player, X, Y, Z, player.getRotationYawHead(), event.getPartialRenderTick());
            } else {
                renderer.doRender(player, X, Y + 0.5, Z, 0, event.getPartialRenderTick());
                rendererHead.doRender(player, X, Y + 0.5, Z, player.getRotationYawHead(), event.getPartialRenderTick());
            }
        } else {
            event.setCanceled(false);
        }
    }

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event) {
        EntityPlayer player = event.player;
        IJar jar = player.getCapability(JAR, null);
        double x = player.lastTickPosX + (player.posX - player.lastTickPosX);
        double y = player.lastTickPosY + (player.posY - player.lastTickPosY);
        double z = player.lastTickPosZ + (player.posZ - player.lastTickPosZ);
        if (jar == null)
            return;
        if (jar.isJar()) {
            player.setEntityBoundingBox(new AxisAlignedBB(x - 0.35, y, z - 0.35, x + 0.35, y + 0.75, z + 0.35));

            player.height = 0.75f;
            player.width = 0.7f;
            player.eyeHeight = 0.5f;
            if(player.isRiding()) player.eyeHeight = 0.75f;
        } else {
            player.height = 1.8f;
            player.width = 0.6f;
            player.eyeHeight = 1.62f;
            player.setEntityBoundingBox(new AxisAlignedBB(x - 0.3, y, z - 0.3, x + 0.3, y + 1.8, z + 0.3));
        }
    }

    public static final ResourceLocation JAR_LOC = new ResourceLocation(Reference.MOD_ID, "jar");

    @SubscribeEvent
    public void attachCapability(AttachCapabilitiesEvent<Entity> event) {
        if (!(event.getObject() instanceof EntityPlayer))
            return;

        if (!(event.getObject().hasCapability(JAR, null)))
            event.addCapability(JAR_LOC, new JarProvider());
    }

    @SubscribeEvent
    public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        Message msg = new Message(event.player.getUniqueID());
        PacketHandler.INSTANCE.sendToAll(msg);
        System.out.println("sent message 2");
        msg = new Message(event.player.getCapability(JAR, null).isJar(), event.player.getUniqueID());
        PacketHandler.INSTANCE.sendToAll(msg);
        System.out.println("sent message 1");
    }
}
