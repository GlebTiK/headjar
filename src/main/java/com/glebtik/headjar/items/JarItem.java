package com.glebtik.headjar.items;

// import com.glebtik.headjar.entity.HeadlessBody;
// import com.glebtik.headjar.jars.HeadJar;
// import com.glebtik.headjar.jars.NoJar;
// import com.glebtik.headjar.network.SetPlayerJarMessage;
import com.glebtik.headjar.util.Color;

// import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
// import net.minecraft.entity.player.EntityPlayer;
// import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
// import net.minecraft.util.ActionResult;
// import net.minecraft.util.EnumActionResult;
// import net.minecraft.util.EnumHand;
// import net.minecraft.util.text.TextFormatting;
// import net.minecraft.world.World;

// import static com.glebtik.headjar.capabilities.JarProvider.JAR;

// import java.util.List;

// import com.glebtik.headjar.network.PacketHandler;

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

    /*
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
                HeadlessBody hb = new HeadlessBody(playerIn);
                hb.setPosition(playerIn.posX, playerIn.posY, playerIn.posZ);
                worldIn.spawnEntity(hb);
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
        TextFormatting res = TextFormatting.RESET;
        TextFormatting rd = TextFormatting.RED;
        TextFormatting gre = TextFormatting.GREEN;
        TextFormatting gol = TextFormatting.GOLD;
        TextFormatting gr = TextFormatting.GRAY;
        TextFormatting a = TextFormatting.AQUA;
        TextFormatting w = TextFormatting.WHITE;
        if (stack.hasTagCompound()) {
            if (stack.getTagCompound().hasKey("fire")) {
                tooltip.add(res + "" + gol + "Fire protection: "
                        + (stack.getTagCompound().getBoolean("fire") ? gre + "is equipped" : rd + "is not equipped"));
            } else {
                tooltip.add(res + "" + gol + "Fire protection: " + rd + "is not equipped");
            }
            if (stack.getTagCompound().hasKey("prot")) {
                tooltip.add(res + "" + gr + "" + "Protection: "
                        + (stack.getTagCompound().getBoolean("prot") ? gre + "is equipped" : rd + "is not equipped"));
            } else {
                tooltip.add(res + "" + gr + "" + "Protection: " + rd + "is not equipped");
            }
            if (stack.getTagCompound().hasKey("water")) {
                tooltip.add(res + "" + a + "" + "Water protection: "
                        + (stack.getTagCompound().getBoolean("water") ? gre + "is equipped" : rd + "is not equipped"));
            } else {
                tooltip.add(res + "" + a + "Water protection: " + rd + "is not equipped");
            }
            if (stack.getTagCompound().hasKey("transform")) {
                tooltip.add(res + "" + w + "Can equip a body: "
                        + (stack.getTagCompound().getBoolean("transform") ? gre + "yes" : rd + "no"));
            } else {
                tooltip.add(res + "" + w + "Can equip a body: " + rd + "no");
            }
        } else {
            tooltip.add(res + "" + gol + "Fire protection: " + rd + "is not equipped");
            tooltip.add(res + "" + gr + "Protection: " + rd + "is not equipped");
            tooltip.add(res + "" + a + "Water protection: " + rd + "is not equipped");
            tooltip.add(res + "" + w + "Can equip a body: " + rd + "no");
        }
    }*/
}