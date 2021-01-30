package com.glebtik.headjar.entity;

import com.glebtik.headjar.items.JarItem;
import com.glebtik.headjar.jars.HeadJar;
import com.glebtik.headjar.jars.IJar;
import com.glebtik.headjar.jars.NoJar;
import com.glebtik.headjar.network.PacketHandler;
import com.glebtik.headjar.network.SetPlayerJarMessage;
import com.glebtik.headjar.register.PotionInit;

import static com.glebtik.headjar.capabilities.JarProvider.JAR;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;

public class HeadlessBody extends EntityVillager {

    private InventoryBasic inventory = new InventoryBasic("Body's Inventory", false, 27);
    private boolean killed = false;
    private UUID playerUuid = null;

    public HeadlessBody(EntityPlayer player) {
        super(player.world);
        for (ItemStack item : player.inventory.mainInventory) {
            inventory.addItem(item);
        }
        this.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, player.getHeldItemMainhand());
        this.setItemStackToSlot(EntityEquipmentSlot.OFFHAND, player.getHeldItemOffhand());
        this.setItemStackToSlot(EntityEquipmentSlot.CHEST, player.getItemStackFromSlot(EntityEquipmentSlot.CHEST));
        this.setItemStackToSlot(EntityEquipmentSlot.LEGS, player.getItemStackFromSlot(EntityEquipmentSlot.LEGS));
        this.setItemStackToSlot(EntityEquipmentSlot.FEET, player.getItemStackFromSlot(EntityEquipmentSlot.FEET));
        player.inventory.mainInventory.clear();
        player.setItemStackToSlot(EntityEquipmentSlot.CHEST, ItemStack.EMPTY);
        player.setItemStackToSlot(EntityEquipmentSlot.LEGS, ItemStack.EMPTY);
        player.setItemStackToSlot(EntityEquipmentSlot.FEET, ItemStack.EMPTY);
        player.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, ItemStack.EMPTY);
        player.setItemStackToSlot(EntityEquipmentSlot.OFFHAND, ItemStack.EMPTY);
        this.playerUuid = player.getUniqueID();
    }

    @Override
    public void onLivingUpdate() {
        if (this.getHealth() <= 0 && !killed) {
            this.deathTime = 0;
            this.setHealth(1);
        }
    }

    @Override
    public InventoryBasic getVillagerInventory() {
        return this.inventory;
    }

    @Override
    public boolean processInteract(EntityPlayer player, EnumHand hand) {
        IJar jar = player.getCapability(JAR, null).getJar();
        if (this.isPotionActive(PotionInit.TRANSFORM) && jar instanceof HeadJar) {
            HeadJar headjar = (HeadJar) jar;
            if (headjar.canModify()) {
                NoJar newJar = new NoJar();
                ItemStack itemStackIn = new JarItem(headjar.getColor()).getItem();
                if (player.getUniqueID() == playerUuid) {
                    itemStackIn.setTagCompound(new NBTTagCompound());
                    itemStackIn.getTagCompound().setBoolean("fire", jar.getAbility("fire"));
                    itemStackIn.getTagCompound().setBoolean("prot", jar.getAbility("prot"));
                    itemStackIn.getTagCompound().setBoolean("water", jar.getAbility("water"));
                    List<String> lore = new ArrayList<>();
                    itemStackIn.getItem().addInformation(itemStackIn, this.world, lore, null);
                    player.getCapability(JAR, null).setJar(newJar);
                    for (int i = 0; i < this.inventory.getSizeInventory(); i++) {
                        player.addItemStackToInventory(this.inventory.getStackInSlot(i));
                    }
                    if (!player.world.isRemote) {
                        SetPlayerJarMessage message = SetPlayerJarMessage.create((EntityPlayerMP) player);
                        PacketHandler.INSTANCE.sendToAll(message);
                        player.world.spawnEntity(
                                new EntityItem(player.world, player.posX, player.posY, player.posZ, itemStackIn));
                    }
                    killed = true;
                    player.attemptTeleport(this.posX, this.posY, this.posZ);
                    this.onKillCommand();
                    return true;
                } else {

                    return true;
                }
            } else
                return false;
        } else
            return false;
    }
    /* tbh need help with this (trades), gonna do it later mayb */
}
