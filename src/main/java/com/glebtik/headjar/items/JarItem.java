package com.glebtik.headjar.items;

import com.glebtik.headjar.capabilities.Jar;
import com.glebtik.headjar.network.SetPlayerNBTMessage;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import static com.glebtik.headjar.capabilities.JarProvider.JAR;

import com.glebtik.headjar.network.PacketHandler;

public class JarItem extends Item {
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
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand hand) {

        ItemStack itemStackIn = playerIn.getHeldItem(hand);
        if(!worldIn.isRemote){
            Jar jar = (Jar) playerIn.getCapability(JAR, null);

            System.out.println("INFO: jar.isJar() rmb: " + jar.isJar());

            jar.setJar(!jar.isJar());

            SetPlayerNBTMessage message = SetPlayerNBTMessage.create((EntityPlayerMP) playerIn);
            PacketHandler.INSTANCE.sendToAll(message);

            System.out.println("set jar.isJar() to " + jar.isJar());
            return new ActionResult<>(EnumActionResult.SUCCESS, itemStackIn);
        }else{
            return new ActionResult<>(EnumActionResult.SUCCESS, itemStackIn);
        }

    }
}