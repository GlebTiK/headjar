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

    // private static ResourceLocation textures = new ResourceLocation("headjar",
    // "player/jar.png");
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
        // renderer.bindTexture(textures);
        IJar jar = player.getCapability(JAR, null);
        // player.getServer().getEntityFromUuid(player.getUniqueID()).getCapability(JAR,
        // null);
        // System.out.println("jar.isJar() PlayerModler: " + jar.isJar());
        if (jar == null) {
            event.setCanceled(false);
            return;
        }
        if (jar.isJar()) {
            event.setCanceled(true);
            double X = player.posX - Minecraft.getMinecraft().player.posX;
            double Y = player.posY - Minecraft.getMinecraft().player.posY;
            double Z = player.posZ - Minecraft.getMinecraft().player.posZ;
            // public void render(Entity entity, float limbSwing, float limbSwingAmount,
            // float ageInTicks, float netHeadYaw, float headPitch, float scale)
            model.setRotationAngle(0, -(player.getRotationYawHead() / 60), 0);
            headModel.setRotationAngle(0, -(player.getRotationYawHead() / 60), 0);
            // model.render(event.getEntity(), event.getEntityLiving().limbSwing,
            // event.getEntityLiving().limbSwingAmount,
            // event.getEntityLiving().ticksExisted, event.getEntityLiving().rotationYaw,
            // event.getEntityLiving().rotationPitch, 0.1f);
            // renderer.renderName(player, 0D, 0D, 0D);
            // Render.renderOffsetAABB(new AxisAlignedBB(X1-0.5, Y1, Z1-0.5, X1+0.5, Y1+1,
            // Z1+0.5), player.posX, player.posY, player.posZ);
            if (!player.isRiding()) {
                renderer.doRender(player, X, Y, Z, player.getRotationYawHead(), event.getPartialRenderTick());
                rendererHead.doRender(player, X, Y, Z, player.getRotationYawHead(), event.getPartialRenderTick());
            } else {
                renderer.doRender(player, X, Y + 0.5, Z, 0, event.getPartialRenderTick());
                rendererHead.doRender(player, X, Y + 0.5, Z, player.getRotationYawHead(), event.getPartialRenderTick());
            }
            // bb_main.rotateAngleY = player.rotationYawHead;
        } else {
            event.setCanceled(false);
        }
        //
        //// NBTTagCompound tag = (!player.isServerWorld()) ?
        // player.getServer().getEntityFromUuid(player.getUniqueID()).getEntityData() :
        // player.getEntityData();
        //
        // if (!tag.hasKey(event.getEntity().getName()+"isJar")) {
        // tag.setBoolean(event.getEntity().getName()+"isJar", false);
        // System.out.println(tag.getBoolean(event.getEntity().getName()+"isJar") + "
        // just got it in playermodel after setting it to false cuz it does not exist");
        // }
        // System.out.println(tag.getBoolean(event.getEntity().getName()+"isJar") + " at
        // start of playermodel");
        // if (tag.getBoolean(event.getEntity().getName()+"isJar")) {
        // System.out.println(tag.getBoolean(event.getEntity().getName()+"isJar") + "
        // just got it in playermodel if is");
        //
        // } else {
        // event.setCanceled(false);
        // System.out.println(tag.getBoolean(event.getEntity().getName()+"isJar") + "
        // just got it in playermodel if is not");
        // }
    }

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event) {
        // if (event.phase == Phase.START) return;
        EntityPlayer player = event.player;
        IJar jar = player.getCapability(JAR, null);
        double X = player.lastTickPosX + (player.posX - player.lastTickPosX);
        double Y = player.lastTickPosY + (player.posY - player.lastTickPosY);
        double Z = player.lastTickPosZ + (player.posZ - player.lastTickPosZ);
        if (jar == null)
            return;
        // System.out.println("h"+player.height);
        // System.out.println("w"+player.width);
        // System.out.println("eh"+player.eyeHeight);
        // player.setSize(1.0f, 1.0f);
        if (jar.isJar()) {
            // if (player.isRiding()) {
            // player.setEntityBoundingBox(new AxisAlignedBB(X - 0.35, Y + 1, Z - 0.35, X +
            // 0.35, Y + 2, Z + 0.35));
            // System.out.println(player.getEntityBoundingBox().minY + " -> " +
            // player.getEntityBoundingBox().maxY);
            // } else {
            player.setEntityBoundingBox(new AxisAlignedBB(X - 0.35, Y, Z - 0.35, X + 0.35, Y + 0.75, Z + 0.35));
            // }
            player.height = 0.75f;
            player.width = 0.7f;
            player.eyeHeight = 0.5f;
            if(player.isRiding()) player.eyeHeight = 0.75f;
        } else {
            player.height = 1.8f;
            player.width = 0.6f;
            player.eyeHeight = 1.62f;
            player.setEntityBoundingBox(new AxisAlignedBB(X - 0.3, Y, Z - 0.3, X + 0.3, Y + 1.8, Z + 0.3));
            // System.out.println((player.posX - player.getEntityBoundingBox().minX) + " "
            // + (player.posY - player.getEntityBoundingBox().minY) + " "
            // + (player.posZ - player.getEntityBoundingBox().minZ) + " "
            // + (player.getEntityBoundingBox().maxX - player.posX) + " "
            // + (player.getEntityBoundingBox().maxY - player.posY) + " "
            // + (player.getEntityBoundingBox().maxZ - player.posZ));
        }
        // System.out.println(player.getEntityBoundingBox());
        // System.out.println("Does this at least work?");
        // if (!tag.hasKey(event.player.getName()+"isJar")) {
        // tag.setBoolean(event.player.getName()+"isJar", false);
        // System.out.println(tag.getBoolean(event.player.getName()+"isJar") + " just
        // got it in onplayertick after setting it to false cuz it does not exist");
        // }
        // System.out.println(tag.getBoolean(event.player.getName()+"isJar") + " -!-!-!-
        // onPlayerTick");
        // if (tag.getBoolean(event.player.getName()+"isJar")) {
        //
        // }
        // what is this code please help
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
        // PacketHandler.sendCapabilityPacket(event.player.getCapability(JAR,
        // null).isJar(), event.player.getUniqueID());
    }
}
