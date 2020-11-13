package com.glebtik.headjar.items;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import static com.glebtik.headjar.capabilities.JarProvider.JAR;

import com.glebtik.headjar.capabilities.IJar;
import com.glebtik.headjar.network.Message;
import com.glebtik.headjar.network.PacketHandler;

public class JarItem extends Item
{
    private ItemStack item;
    public JarItem() {
        this.setMaxDamage(0);
        this.setHasSubtypes(false);
        this.setMaxStackSize(1);
        this.setCreativeTab(CreativeTabs.MISC);
        item = new ItemStack(this);
    }
    public ItemStack getItem() {
        return item;
    }
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand hand)
    {
        ItemStack itemStackIn = playerIn.getHeldItem(hand);
        // if (playerIn.world.isRemote) return new ActionResult<>(EnumActionResult.SUCCESS, itemStackIn);
        IJar jar = playerIn.getCapability(JAR, null);
        
        System.out.println("jar.isJar() rmb: " + jar.isJar());
        if (jar.isJar()) {
            jar.setJar(false);
            Message msg = new Message(jar.isJar(), playerIn.getUniqueID());
            if (!worldIn.isRemote) {
                PacketHandler.INSTANCE.sendToAll(msg);
            }
            System.out.println("set jar.isJar() to " + jar.isJar());
            // playerIn.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, (new BodyItem()).getItem());
            return new ActionResult<>(EnumActionResult.SUCCESS, itemStackIn);
        } else if (!jar.isJar()) {
            jar.setJar(true);
            Message msg = new Message(jar.isJar(), playerIn.getUniqueID());
            if(!worldIn.isRemote)PacketHandler.INSTANCE.sendToAll(msg);
            System.out.println("set jar.isJar() to " + jar.isJar());
            return new ActionResult<>(EnumActionResult.SUCCESS, itemStackIn);
        } else {
            jar.setJar(false);
            System.out.println("what in the world of coding and why"); 
            return new ActionResult<>(EnumActionResult.FAIL, itemStackIn);
        }
    }
}