package com.glebtik.headjar.jars;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.glebtik.headjar.client.render.head.RenderHead;
import com.glebtik.headjar.client.render.head.RenderJar;
import com.glebtik.headjar.entity.render.RenderHeadlessZombie;
import com.glebtik.headjar.network.MessageBufferUtils;
import com.glebtik.headjar.util.Color;
import com.glebtik.headjar.util.Reference;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

public class HeadJar implements IJar {

    protected final RenderJar rendererJar = new RenderJar(Minecraft.getMinecraft().getRenderManager());
    protected RenderHead rendererHead = new RenderHead(Minecraft.getMinecraft().getRenderManager());
    private Color color = Color.BLANK;

    protected float headXOff;
    protected float headYOff;
    protected float headZOff;

    protected double renderXOff;
    protected double renderYOff;
    protected double renderZOff;

    private Map<String, Boolean> abilities = new HashMap<String, Boolean>();

    private String transform = "";

    public final static String T1 = "ZOMBIE";
    public final static String T2 = "SKELETON";
    public final static String T3 = "CREEPER";
    public final static String T4 = "VILLAGER";
    public final static String T5 = "PLAYER_BODY";

    private UUID bodyUuid = null;

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public boolean shouldRenderPlayer() {
        return false;
    }

    @Override
    public void doRender(EntityPlayer player, float partialRenderTick, RenderPlayer playerRenderer) {
        updateRenderOffset(player, partialRenderTick);
        rendererHead.setEntityTexture(playerRenderer.getEntityTexture((AbstractClientPlayer) player));

        if (player.isRiding()) {
            rendererJar.doRender(player, headXOff + renderXOff, headYOff + renderYOff + 0.5, headZOff + renderZOff, 0,
                    partialRenderTick, color);
            rendererHead.doRender(player, headXOff + renderXOff, headYOff + renderYOff + 0.5, headZOff + renderZOff,
                    player.getRotationYawHead(), partialRenderTick);
        } else if (transform == T1) {

        } else if (transform == T2) {

        } else if (transform == T3) {

        } else if (transform == T4) {

        } else {
            rendererHead.doRender(player, headXOff + renderXOff, headYOff + renderYOff, headZOff + renderZOff,
                    player.getRotationYawHead(), partialRenderTick);
            rendererJar.doRender(player, headXOff + renderXOff, headYOff + renderYOff, headZOff + renderZOff,
                    player.getRotationYawHead(), partialRenderTick, color);
            // RenderHeadlessZombie.doRender(player, headXOff + renderXOff, headYOff +
            // renderYOff, headZOff + renderZOff, player.getRotationYawHead());
        }
    }

    private void updateRenderOffset(EntityPlayer player, float partialRenderTick) {
        renderXOff = getPartialValue(partialRenderTick, player.posX, player.prevPosX) - getPartialValue(
                partialRenderTick, Minecraft.getMinecraft().player.posX, Minecraft.getMinecraft().player.prevPosX);
        renderYOff = getPartialValue(partialRenderTick, player.posY, player.prevPosY) - getPartialValue(
                partialRenderTick, Minecraft.getMinecraft().player.posY, Minecraft.getMinecraft().player.prevPosY);
        renderZOff = getPartialValue(partialRenderTick, player.posZ, player.prevPosZ) - getPartialValue(
                partialRenderTick, Minecraft.getMinecraft().player.posZ, Minecraft.getMinecraft().player.prevPosZ);

    }

    private double getPartialValue(float partialRenderTick, double newValue, double oldValue) {
        return newValue * partialRenderTick + oldValue * (1 - partialRenderTick);
    }

    @Override
    public void writeNBT(NBTTagCompound nbt) {
        nbt.setInteger("jarcolor", color.colorValue);
        nbt.setBoolean("fire", getAbility("fire"));
        nbt.setBoolean("prot", getAbility("prot"));
        nbt.setBoolean("water", getAbility("water"));
        nbt.setBoolean("transform", getAbility("transform"));
    }

    @Override
    public void readNBT(NBTTagCompound nbt) {
        int colorValue = nbt.getInteger("jarcolor");
        setAbility("fire", nbt.getBoolean("fire"));
        setAbility("prot", nbt.getBoolean("prot"));
        setAbility("water", nbt.getBoolean("water"));
        setAbility("transform", nbt.getBoolean("transform"));
        for (Color c : Color.values()) {
            if (c.colorValue == colorValue) {
                setColor(c);
                return;
            }
        }
        setColor(Color.BLANK);
    }

    @Override
    public void writeByteBuf(ByteBuf buf) {
        MessageBufferUtils.writeColor(buf, color);
        MessageBufferUtils.writeAbility(buf, "fire", getAbility("fire"));
        MessageBufferUtils.writeAbility(buf, "prot", getAbility("prot"));
        MessageBufferUtils.writeAbility(buf, "water", getAbility("water"));
    }

    @Override
    public void readByteBuf(ByteBuf buf) {
        color = MessageBufferUtils.readColor(buf);
        abilities.putAll(MessageBufferUtils.readAbility(buf));
        abilities.putAll(MessageBufferUtils.readAbility(buf));
        abilities.putAll(MessageBufferUtils.readAbility(buf));
    }

    @Override
    public ResourceLocation getRegistryName() {
        return new ResourceLocation(Reference.MOD_ID, "head_jar");
    }

    @Override
    public float getHeight() {
        if (transform != "")
            return 1.95f;
        else
            return 0.75f;
    }

    @Override
    public float getWidth() {
        return 0.7f;
    }

    @Override
    public float getEyeHeight() {
        return 0.5f;
    }

    @Override
    public boolean getAbility(String abil) {
        return abilities.containsKey(abil) ? abilities.get(abil) : false;
    }

    @Override
    public void setAbility(String abil, boolean bool) {
        abilities.put(abil, bool);
    }

    public boolean canModify() {
        return true;
    }

    @Override
    public Map<String, Boolean> getAbils() {
        return abilities;
    }

    @Override
    public String transform() {
        return transform;
    }

    @Override
    public void setTransfrom(String a) {
        transform = a;
    }

    @Override
    public UUID getPlayerBodyUuid() {
        if (transform() == T5)
            return this.bodyUuid;
        else
            return null;
    }

    @Override
    public void setPlayerBodyUuid(UUID a) {
        if (transform() == T5) {
            this.bodyUuid = a;
        }
    }
}
