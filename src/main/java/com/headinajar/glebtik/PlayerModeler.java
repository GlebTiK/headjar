package com.headinajar.glebtik;

import com.headinajar.glebtik.util.Reference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraft.entity.Entity;

import static com.headinajar.glebtik.JarProvider.JAR;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class PlayerModeler {
    @SubscribeEvent
    public void onPlayerRenderEvent(RenderPlayerEvent.Pre event) {
        PlayerModel model = new PlayerModel();
        IJar jar = event.getEntityPlayer().getCapability(JAR, null);
        System.out.println("jar.isJar() PlayerModler: " + jar.isJar());
        if (jar.isJar() == 1) {
            event.setCanceled(true);
            ResourceLocation texture = new ResourceLocation("headjar", "textures/player/jar");
            event.getRenderer().bindTexture(texture);
            //public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
            model.render(event.getEntity(), event.getEntityLiving().limbSwing, event.getEntityLiving().limbSwingAmount,
                    event.getEntityLiving().ticksExisted, event.getEntityLiving().rotationYaw,
                    event.getEntityLiving().rotationPitch, 0.1f);
//			bb_main.rotateAngleY = event.getEntityPlayer().rotationYawHead;
        } else {
            event.setCanceled(false);
        }
//
////        NBTTagCompound tag = (!event.getEntityPlayer().isServerWorld()) ? event.getEntityPlayer().getServer().getEntityFromUuid(event.getEntityPlayer().getUniqueID()).getEntityData() : event.getEntityPlayer().getEntityData();
//
//        if (!tag.hasKey(event.getEntity().getName()+"isJar")) {
//            tag.setBoolean(event.getEntity().getName()+"isJar", false);
//            System.out.println(tag.getBoolean(event.getEntity().getName()+"isJar") + " just got it in playermodel after setting it to false cuz it does not exist");
//        }
//        System.out.println(tag.getBoolean(event.getEntity().getName()+"isJar") + " at start of playermodel");
//        if (tag.getBoolean(event.getEntity().getName()+"isJar")) {
//            System.out.println(tag.getBoolean(event.getEntity().getName()+"isJar") + " just got it in playermodel if is");
//
//        } else {
//            event.setCanceled(false);
//            System.out.println(tag.getBoolean(event.getEntity().getName()+"isJar") + " just got it in playermodel if is not");
//        }
    }
    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event) {
//        System.out.println("Does this at least work?");
//        if (!tag.hasKey(event.player.getName()+"isJar")) {
//            tag.setBoolean(event.player.getName()+"isJar", false);
//            System.out.println(tag.getBoolean(event.player.getName()+"isJar") + " just got it in onplayertick after setting it to false cuz it does not exist");
//        }
//        System.out.println(tag.getBoolean(event.player.getName()+"isJar") + " -!-!-!- onPlayerTick");
//        if (tag.getBoolean(event.player.getName()+"isJar")) {
//
//        }
        // what is this code please help
    }
    public static final ResourceLocation JAR_LOC = new ResourceLocation(Reference.MOD_ID, "jar");
    @SubscribeEvent
    public void attachCapability(AttachCapabilitiesEvent<Entity> event)
    {
        if (!(event.getObject() instanceof EntityPlayer)) return;

        if(!(event.getObject().hasCapability(JAR, null))) event.addCapability(JAR_LOC, new JarProvider());
    }
}
