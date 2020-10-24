package com.headinajar.glebtik;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import static com.headinajar.glebtik.JarProvider.JAR;

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
        if (playerIn.world.isRemote) return new ActionResult<>(EnumActionResult.PASS, itemStackIn);
        IJar jar = playerIn.getCapability(JAR, null);
        System.out.println("jar.isJar() rmb: " + jar.isJar());
        if (jar.isJar() == 1) {
            jar.setJar((byte) 0);
            return new ActionResult<>(EnumActionResult.SUCCESS, itemStackIn);
        } else if (jar.isJar() == 0) {
            jar.setJar((byte) 1);
            return new ActionResult<>(EnumActionResult.SUCCESS, itemStackIn);
        } else {
            jar.setJar((byte) 0);
            System.out.println("what in the world of coding and why");
            return new ActionResult<>(EnumActionResult.FAIL, itemStackIn);
        }
    }
}