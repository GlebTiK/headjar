package com.glebtik.headjar.util;

import com.glebtik.headjar.capabilities.IJarCapability;
import com.glebtik.headjar.network.SetPlayerJarMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraft.entity.Entity;
import com.glebtik.headjar.capabilities.JarProvider;
import com.glebtik.headjar.client.RenderHead;
import com.glebtik.headjar.client.RenderJar;
import com.glebtik.headjar.network.PacketHandler;

import static com.glebtik.headjar.capabilities.JarProvider.JAR;

import com.glebtik.headjar.jars.IJar;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class Events {
    private RenderJar renderer = null;
    private RenderHead rendererHead = null;

    @SubscribeEvent
    public void onPlayerRenderEvent(RenderPlayerEvent.Pre event) {
        if (renderer == null) {
            renderer = new RenderJar(event.getRenderer().getRenderManager());
        }
        if (rendererHead == null) {
            rendererHead = new RenderHead(event.getRenderer().getRenderManager());
        }
        EntityPlayer player = event.getEntityPlayer();
        IJarCapability jar = player.getCapability(JAR, null);
        event.setCanceled(!jar.getJar().shouldRenderPlayer());
        jar.getJar().doRender(player, event.getPartialRenderTick(), event.getRenderer());

    }

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event) {
        EntityPlayer player = event.player;
        IJar jar = player.getCapability(JAR, null).getJar();

        jar.updateHitbox(player);
    }

    public static final ResourceLocation JAR_LOC = new ResourceLocation(Reference.MOD_ID, "jar");

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
        //only fire on Server
        if(event.player instanceof EntityPlayerMP) {
            EntityPlayerMP serverPlayer = (EntityPlayerMP) event.player;
            PacketHandler.INSTANCE.sendToAll(SetPlayerJarMessage.create(serverPlayer));

            for(EntityPlayerMP player: serverPlayer.getServer().getPlayerList().getPlayers()) {
                PacketHandler.INSTANCE.sendTo(SetPlayerJarMessage.create(player), serverPlayer);
            }
        }
    }
}
