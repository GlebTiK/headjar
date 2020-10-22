package com.headinajar.glebtik;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
public class JarItem extends Item
{
    public JarItem() {
        this.setMaxDamage(0);
        this.setHasSubtypes(false);
        this.setMaxStackSize(1);
        this.setCreativeTab(CreativeTabs.MISC);
    }
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand hand)
    {
        ItemStack itemStackIn = playerIn.getHeldItem(hand);
        NBTTagCompound tag = (playerIn.isServerWorld()) ? playerIn.getServer().getEntityFromUuid(playerIn.getUniqueID()).getEntityData() : playerIn.getEntityData();
        if (!tag.hasKey("isJar")) {
            tag.setBoolean("isJar", true);
            System.out.println(tag.getBoolean("isJar") + " just got it in jaritem after setting it to true cuz it does not exist");
        }
        System.out.println(tag.getBoolean("isJar") + " at start of jaritem");
        if(tag.getBoolean("isJar")) {
            tag.setBoolean("isJar", false);
            System.out.println(tag.getBoolean("isJar") + " just got it in jaritem if is");
            return new ActionResult<>(EnumActionResult.SUCCESS, itemStackIn);
        } else if(!tag.getBoolean("isJar")) {
            tag.setBoolean("isJar", true);
            System.out.println(tag.getBoolean("isJar") + " just got it in jaritem if is not");
            return new ActionResult<>(EnumActionResult.SUCCESS, itemStackIn);
        } else {
            playerIn.sendStatusMessage(new TextComponentString("what the heck dude something went wrong"), true);
            System.out.println(tag.getBoolean("isJar") + ": error");
            return new ActionResult<>(EnumActionResult.FAIL, itemStackIn);
        }
    }
}