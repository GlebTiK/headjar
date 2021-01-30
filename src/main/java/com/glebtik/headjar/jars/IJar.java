package com.glebtik.headjar.jars;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import static com.glebtik.headjar.capabilities.JarProvider.JAR;

import java.util.Map;
import java.util.UUID;

public interface IJar {
    boolean shouldRenderPlayer();
    void doRender(EntityPlayer player, float partialRenderTick, RenderPlayer playerRenderer);
    void writeNBT(NBTTagCompound nbt);
    void readNBT(NBTTagCompound nbt);
    void writeByteBuf(ByteBuf buf);
    void readByteBuf(ByteBuf buf);
    ResourceLocation getRegistryName();
    float getHeight();
    float getWidth();
    float getEyeHeight();
    default void clientTick(TickEvent.PlayerTickEvent event) {}
    default void serverTick(TickEvent.PlayerTickEvent event) {
        EntityPlayer player = event.player;
        if (player.getCapability(JAR, null).getJar().getAbility("fire") && (player.getActivePotionEffect(MobEffects.FIRE_RESISTANCE) == null ? true : player.getActivePotionEffect(MobEffects.FIRE_RESISTANCE).getDuration() <= 2)) {
            player.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 5, 0));
        }
        if (player.getCapability(JAR, null).getJar().getAbility("prot") && (player.getActivePotionEffect(MobEffects.RESISTANCE) == null ? true : player.getActivePotionEffect(MobEffects.RESISTANCE).getDuration() <= 2)) {
            player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 5, 0));
        }
        if (player.getCapability(JAR, null).getJar().getAbility("water") && (player.getActivePotionEffect(MobEffects.WATER_BREATHING) == null ? true : player.getActivePotionEffect(MobEffects.WATER_BREATHING).getDuration() <= 2)) {
            player.addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 5, 0));
        }
        //player.setPosition(player.prevPosX, player.prevPosY, player.prevPosZ);
    }
    default void updateHitBox(EntityPlayer player) {
        player.setEntityBoundingBox( new AxisAlignedBB(player.posX-getWidth()/2, player.posY, player.posZ-getWidth()/2, player.posX+getWidth()/2, player.posY+getHeight(), player.posZ+getWidth()/2));
        player.height = getHeight();
        player.width = getWidth();
        player.eyeHeight = getEyeHeight();
        if(player.isRiding()) {
            player.height = player.height + 0.48f;
            player.eyeHeight = player.eyeHeight + 0.25f;
        }
    }
    default void onRemove() {
    }
    boolean getAbility(String a);
    void setAbility(String a, boolean b);
    Map<String, Boolean> getAbils();
    String transform();
    void setTransfrom(String a);
    UUID getPlayerBodyUuid();
    void setPlayerBodyUuid(UUID a);
}
