package com.glebtik.headjar.items.modifications;

import com.glebtik.headjar.jars.HeadJar;
import com.glebtik.headjar.jars.IJar;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import static com.glebtik.headjar.capabilities.JarProvider.JAR;

public class Template extends Item {
    public Template() {
        
    }
    
    public Template(String a) {
        this.setMaxDamage(0);
        this.setHasSubtypes(false);
        this.setMaxStackSize(1);
        this.setCreativeTab(CreativeTabs.TOOLS);

        setUnlocalizedName(a);
        setRegistryName(a);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand hand) {
        ItemStack itemStackIn = playerIn.getHeldItem(hand);
        if (playerIn.getCapability(JAR, null).getJar() instanceof HeadJar) {
            if (!worldIn.isRemote) {
                IJar jar = playerIn.getCapability(JAR, null).getJar();
                if (jar.getAbility(getAbilityName())) {
                    return new ActionResult<>(EnumActionResult.FAIL, itemStackIn);
                } else {
                    jar.setAbility(getAbilityName(), true);
                    itemStackIn.setCount(itemStackIn.getCount()-1);
                    playerIn.inventoryContainer.detectAndSendChanges();
                    return new ActionResult<>(EnumActionResult.SUCCESS, itemStackIn);
                }
            } else {
                return new ActionResult<>(EnumActionResult.PASS, itemStackIn);
            }
        } else {
            return new ActionResult<>(EnumActionResult.FAIL, itemStackIn);
        }
    }
    public String getAbilityName() {
        return "prot";
    }
}