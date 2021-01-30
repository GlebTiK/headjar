package com.glebtik.headjar.util;

import com.glebtik.headjar.capabilities.IJarCapability;
// import com.glebtik.headjar.jars.HeadJar;
// import com.glebtik.headjar.jars.IronGolemJar;
import com.glebtik.headjar.network.SetPlayerJarMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
// import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.EntityInteractSpecific;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityVillager;

import com.glebtik.headjar.capabilities.JarProvider;
import com.glebtik.headjar.network.PacketHandler;

import static com.glebtik.headjar.capabilities.JarProvider.JAR;

import com.glebtik.headjar.jars.HeadJar;
// import com.glebtik.headjar.jars.HeadJar;
import com.glebtik.headjar.jars.IJar;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class Events {

    public static final ResourceLocation JAR_LOC = new ResourceLocation(Reference.MOD_ID, "jar");

    @SubscribeEvent
    public void onPlayerDimensionChange(PlayerEvent.PlayerChangedDimensionEvent event) {
        if (event.player instanceof EntityPlayerMP) {
            PacketHandler.INSTANCE.sendToAll(SetPlayerJarMessage.create((EntityPlayerMP) event.player));
        }
    }

    @SubscribeEvent
    public void onPlayerRenderEvent(RenderPlayerEvent.Pre event) {

        EntityPlayer player = event.getEntityPlayer();
        IJarCapability jar = player.getCapability(JAR, null);
        event.setCanceled(!jar.getJar().shouldRenderPlayer());
        jar.getJar().doRender(player, event.getPartialRenderTick(), event.getRenderer());

    }

    @SubscribeEvent
    public void EntityInteract(EntityInteractSpecific event) {
        if(event.getTarget() instanceof EntityVillager) {
            IJar j = event.getEntityPlayer().getCapability(JAR, null).getJar();
            if(j instanceof HeadJar) {
                HeadJar jar = (HeadJar) j;
                if (jar.transform() == "" || jar.transform() == HeadJar.T5) {
                    event.setCanceled(true);
                }
            }
        }
    }

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event) {
        EntityPlayer player = event.player;
        IJar jar = player.getCapability(JAR, null).getJar();

        jar.updateHitBox(player);

        if (player instanceof EntityPlayerMP) {
            jar.serverTick(event);
        } else {
            if (event.player == Minecraft.getMinecraft().player) {
                jar.clientTick(event);
            }
        }
    }

    @SubscribeEvent
    public void attachCapability(AttachCapabilitiesEvent<Entity> event) {
        if (!(event.getObject() instanceof EntityPlayer)) {
            return;
        }
        if (!(event.getObject().hasCapability(JAR, null))) {
            event.addCapability(JAR_LOC, new JarProvider());
        }
    }

    @SubscribeEvent
    public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        // only fire on Server
        if (event.player instanceof EntityPlayerMP) {
            EntityPlayerMP serverPlayer = (EntityPlayerMP) event.player;
            PacketHandler.INSTANCE.sendToAll(SetPlayerJarMessage.create(serverPlayer));
            // System.out.println("fire "+((HeadJar) (serverPlayer.getCapability(JAR,
            // null).getJar())).getAbility("fire"));
            // System.out.println("render_jar "+!serverPlayer.getCapability(JAR,
            // null).getJar().shouldRenderPlayer());
            for (EntityPlayerMP player : serverPlayer.getServer().getPlayerList().getPlayers()) {
                PacketHandler.INSTANCE.sendTo(SetPlayerJarMessage.create(player), serverPlayer);
            }
        }
    }
}
