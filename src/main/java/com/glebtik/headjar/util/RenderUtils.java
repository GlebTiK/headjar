package com.glebtik.headjar.util;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

public class RenderUtils {

    public static void cloneRenderValues(ModelRenderer source, ModelRenderer dest) {
        if(source.rotateAngleX != 0) {
            ModelBase.copyModelAngles(source, dest);
            //System.out.println("Angle: " + "X " + source.rotateAngleX + " Y " + source.rotateAngleY + " Z " + source.rotateAngleZ);
            //System.out.println("Point: " + "X " + source.rotationPointX + " Y " + source.rotationPointY + " Z " + source.rotationPointZ);
        }
    }


    public static void cloneValues(EntityPlayer player, Entity entity) {
        if(entity instanceof EntityLivingBase) {
            copyEntityLivingBase(player, (EntityLivingBase) entity);
        }
        copyEntity(player, entity);
    }
    private static void copyEntityLivingBase(EntityPlayer player, EntityLivingBase entity) {
        //entity.arrowHitTimer = player.arrowHitTimer;
        //entity.attackedAtYaw = player.attackedAtYaw;
        //entity.isSwingInProgress = player.isSwingInProgress;
        //entity.swingingHand = player.swingingHand;
        //entity.swingProgress = player.swingProgress;
        //entity.swingProgressInt = player.swingProgressInt;
        //entity.hurtTime = player.hurtTime;
        //entity.maxHurtTime = player.maxHurtTime;
        entity.deathTime = player.deathTime;
        entity.prevLimbSwingAmount = player.prevLimbSwingAmount;
        entity.limbSwingAmount = player.limbSwingAmount;
        entity.limbSwing = player.limbSwing;
        entity.renderYawOffset = player.renderYawOffset;
        entity.prevRenderYawOffset = player.prevRenderYawOffset;
        entity.rotationYawHead = player.rotationYawHead;
        entity.prevRotationYawHead = player.prevRotationYawHead;
    }
    private static void copyEntity(EntityPlayer player, Entity entity) {
        entity.rotationPitch = player.rotationPitch;
        entity.prevRotationPitch = player.prevRotationPitch;
        entity.rotationYaw = player.rotationYaw;
        entity.prevRotationYaw = player.prevRotationYaw;
        entity.onGround = player.onGround;
        entity.hurtResistantTime = player.hurtResistantTime;
        entity.ticksExisted = player.ticksExisted;
    }
}
