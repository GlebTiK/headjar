package com.headinajar.glebtik;

import com.headinajar.glebtik.util.Reference;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class PlayerModeler {
    @SubscribeEvent
    public static void onPlayerRenderEvent(RenderPlayerEvent.Pre event) {;
        NBTTagCompound tag = (event.getEntityPlayer().isServerWorld()) ? event.getEntityPlayer().getServer().getEntityFromUuid(event.getEntityPlayer().getUniqueID()).getEntityData() : event.getEntityPlayer().getEntityData();
        PlayerModel model = new PlayerModel();
//        if (!tag.hasKey("isJar")) {
//            tag.setBoolean("isJar", false);
//            System.out.println(tag.getBoolean("isJar") + " just got it in playermodel after setting it to false cuz it does not exist");
//        }
        System.out.println(tag.getBoolean("isJar") + " at start of playermodel");
        if (tag.getBoolean("isJar")) {
            System.out.println(tag.getBoolean("isJar") + " just got it in playermodel if is");
            event.setCanceled(true);
            ResourceLocation texture = new ResourceLocation("headjar", "textures/player/male.png");
            event.getRenderer().bindTexture(texture);
            float f = event.getPartialRenderTick();
            //public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
            model.render(event.getEntity(), event.getEntityLiving().limbSwing, event.getEntityLiving().limbSwingAmount, event.getEntityLiving().ticksExisted, event.getEntityLiving().rotationYaw, event.getEntityLiving().rotationPitch, event.getPartialRenderTick());
//			bb_main.rotateAngleY = event.getEntityPlayer().rotationYawHead;
        } else {
            event.setCanceled(false);
            System.out.println(tag.getBoolean("isJar") + " just got it in playermodel if is not");
        }
    }
}
