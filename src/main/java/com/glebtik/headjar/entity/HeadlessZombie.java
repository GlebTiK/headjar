package com.glebtik.headjar.entity;

import com.glebtik.headjar.register.PotionInit;

import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

import static com.glebtik.headjar.capabilities.JarProvider.JAR;

import com.glebtik.headjar.jars.HeadJar;
import com.glebtik.headjar.jars.IJar;
import com.glebtik.headjar.network.PacketHandler;
import com.glebtik.headjar.network.SetPlayerJarMessage;

public class HeadlessZombie extends EntityZombie {

    public HeadlessZombie(World worldIn) {
        super(worldIn);
        this.setSize(0.6F, 1.70F);
    }

    @Override
    public boolean processInteract(EntityPlayer player, EnumHand hand) {
        IJar jar = player.getCapability(JAR, null).getJar();
        if (this.isPotionActive(PotionInit.TRANSFORM) && jar instanceof HeadJar) {
            HeadJar headjar = (HeadJar) jar;
            if(headjar.canModify()) {
                headjar.setTransfrom(HeadJar.T1);
                if (!player.world.isRemote) {
                    SetPlayerJarMessage message = SetPlayerJarMessage.create((EntityPlayerMP) player);
                    PacketHandler.INSTANCE.sendToAll(message);
                }
                return true;
            } else return super.processInteract(player, hand);
        } else return super.processInteract(player, hand);
    }

    //@Override
    //public AxisAlignedBB getEntityBoundingBox() {
    //    super.getEntityBoundingBox();
    //    AxisAlignedBB aabb = this.getEntityBoundingBox();
    //    if (this.isChild())
    //    //    return new AxisAlignedBB(this.posX - 0.15, this.posY + 0, this.posZ - 0.15, this.posX + 0.15,
    //    //            this.posY + 0.5, this.posZ + 0.15);
    //    return new AxisAlignedBB(aabb.minX, aabb.minY, aabb.minZ, aabb.maxX,
    //                aabb.minY + 0.5, aabb.maxZ);
    //    else
    //        return new AxisAlignedBB(aabb.minX, aabb.minY, aabb.minZ, aabb.maxX, aabb.maxY + 1.5,
    //        aabb.maxZ);
    //}
    //ok i had enough of this (angery)

    //public int a = 0;
    //public int b = 0;

    //@Override
    //public void onLivingUpdate() {
    //    super.onLivingUpdate();
    //    if (this.isPotionActive(PotionInit.TRANSFORM)) {
    //        this.setNoAI(true);
    //        this.isAIDisabled();
    //    } else {
    //        this.setNoAI(false);
    //    }
    //    //if (this.isChild() && a == 0) {
    //    //    System.out.println("isChild: " + this.isChild());
    //    //    AxisAlignedBB AABB1 = this.getEntityBoundingBox();
    //    //    System.out.println("AABB1: " + AABB1);
    //    //    System.out.println("Normal AABB1: [" + (AABB1.minX - this.posX) + " " + (AABB1.minY - this.posY) + " "
    //    //            + (AABB1.minZ - this.posZ) + "] -> [" + (AABB1.maxX - this.posX) + " " + (AABB1.maxY - this.posY)
    //    //            + " " + (AABB1.maxZ - this.posZ) + "]");
    //    //    a++;
    //    //} else if (!this.isChild() && b == 0) {
    //    //    System.out.println("isChild: " + this.isChild());
    //    //    AxisAlignedBB AABB1 = this.getEntityBoundingBox();
    //    //    System.out.println("AABB1: " + AABB1);
    //    //    System.out.println("Normal AABB1: [" + (AABB1.minX - this.posX) + " " + (AABB1.minY - this.posY) + " "
    //    //            + (AABB1.minZ - this.posZ) + "] -> [" + (AABB1.maxX - this.posX) + " " + (AABB1.maxY - this.posY)
    //    //            + " " + (AABB1.maxZ - this.posZ) + "]");
    //    //    b++;
    //    //}
    //}

}