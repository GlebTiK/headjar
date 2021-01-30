package com.glebtik.headjar.items;

import com.glebtik.headjar.entity.HeadlessBody;
import com.glebtik.headjar.jars.HeadJar;
import com.glebtik.headjar.jars.IJar;
import com.glebtik.headjar.jars.NoJar;
import com.glebtik.headjar.network.SetPlayerJarMessage;
import com.glebtik.headjar.util.Color;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import static com.glebtik.headjar.capabilities.JarProvider.JAR;

import java.util.ArrayList;
import java.util.List;

import com.glebtik.headjar.network.PacketHandler;

public class JarItem extends Item {
    private ItemStack item;
    public final Color color;

    public JarItem(Color color) {
        this.setMaxDamage(0);
        this.setHasSubtypes(false);
        this.setMaxStackSize(1);
        this.setCreativeTab(CreativeTabs.MISC);
        item = new ItemStack(this);

        setUnlocalizedName(color.prefix + "jar");
        setRegistryName(color.prefix + "jar");
        this.color = color;
    }

    public ItemStack getItem() {
        return item;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand hand) {

        ItemStack itemStackIn = playerIn.getHeldItem(hand);
        if (!worldIn.isRemote) {
            if (playerIn.getCapability(JAR, null).getJar() instanceof NoJar) {
                HeadJar jar = new HeadJar();
                jar.setColor(((JarItem) itemStackIn.getItem()).color);
                if (itemStackIn.hasTagCompound()) {
                    if (itemStackIn.getTagCompound().hasKey("fire")) {
                        jar.setAbility("fire", itemStackIn.getTagCompound().getBoolean("fire"));
                    }
                    if (itemStackIn.getTagCompound().hasKey("prot")) {
                        jar.setAbility("prot", itemStackIn.getTagCompound().getBoolean("prot"));
                    }
                    if (itemStackIn.getTagCompound().hasKey("water")) {
                        jar.setAbility("water", itemStackIn.getTagCompound().getBoolean("water"));
                    }
                }
                playerIn.getCapability(JAR, null).setJar(jar);
                itemStackIn.setCount(0);
                new HeadlessBody(playerIn);
                SetPlayerJarMessage message = SetPlayerJarMessage.create((EntityPlayerMP) playerIn);
                PacketHandler.INSTANCE.sendToAll(message);

                return new ActionResult<>(EnumActionResult.SUCCESS, itemStackIn);
            }
            return new ActionResult<>(EnumActionResult.FAIL, itemStackIn);
        } else {
            return new ActionResult<>(EnumActionResult.SUCCESS, itemStackIn);
        }
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        if (stack.hasTagCompound()) {
            if (stack.getTagCompound().hasKey("fire")) {
                tooltip.add(TextFormatting.RESET + "" + TextFormatting.GOLD + "Fire protection: "
                        + (stack.getTagCompound().getBoolean("fire") ? TextFormatting.GREEN + "is equipped"
                                : TextFormatting.RED + "is not equipped"));
            } else {
                tooltip.add(TextFormatting.RESET + "" + TextFormatting.GOLD + "Fire protection: " + TextFormatting.RED
                        + "is not equipped");
            }
            if (stack.getTagCompound().hasKey("prot")) {
                tooltip.add(TextFormatting.RESET + "" + TextFormatting.GRAY + "" + "Protection: "
                        + (stack.getTagCompound().getBoolean("prot") ? TextFormatting.GREEN + "is equipped"
                                : TextFormatting.RED + "is not equipped"));
            } else {
                tooltip.add(TextFormatting.RESET + "" + TextFormatting.GRAY + "" + "Protection: " + TextFormatting.RED
                        + "is not equipped");
            }
            if (stack.getTagCompound().hasKey("water")) {
                tooltip.add(TextFormatting.RESET + "" + TextFormatting.AQUA + "" + "Water protection: "
                        + (stack.getTagCompound().getBoolean("water") ? TextFormatting.GREEN + "is equipped"
                                : TextFormatting.RED + "is not equipped"));
            } else {
                tooltip.add(TextFormatting.RESET + "" + TextFormatting.AQUA + "Water protection: " + TextFormatting.RED
                        + "is not equipped");
            }
            if (stack.getTagCompound().hasKey("transform")) {
                tooltip.add(TextFormatting.RESET + "" + TextFormatting.WHITE + "Can equip a body: "
                        + (stack.getTagCompound().getBoolean("transform") ? TextFormatting.GREEN + "yes"
                                : TextFormatting.RED + "no"));
            } else {
                tooltip.add(TextFormatting.RESET + "" + TextFormatting.WHITE + "Can equip a body: " + TextFormatting.RED
                        + "no");
            }
        } else {
            tooltip.add(TextFormatting.RESET + "" + TextFormatting.GOLD + "Fire protection: " + TextFormatting.RED
                    + "is not equipped");
            tooltip.add(TextFormatting.RESET + "" + TextFormatting.GRAY + "Protection: " + TextFormatting.RED
                    + "is not equipped");
            tooltip.add(TextFormatting.RESET + "" + TextFormatting.AQUA + "Water protection: " + TextFormatting.RED
                    + "is not equipped");
            tooltip.add(TextFormatting.RESET + "" + TextFormatting.WHITE + "Can equip a body: " + TextFormatting.RED
                    + "no");
        }
    }
}