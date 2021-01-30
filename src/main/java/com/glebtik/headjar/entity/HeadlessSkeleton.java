package com.glebtik.headjar.entity;

import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import static com.glebtik.headjar.capabilities.JarProvider.JAR;

import com.glebtik.headjar.jars.HeadJar;
import com.glebtik.headjar.jars.IJar;
import com.glebtik.headjar.network.PacketHandler;
import com.glebtik.headjar.network.SetPlayerJarMessage;
import com.glebtik.headjar.register.PotionInit;

public class HeadlessSkeleton extends EntitySkeleton {

    public HeadlessSkeleton(World worldIn) {
        super(worldIn);
    }
    @Override
    public boolean processInteract(EntityPlayer player, EnumHand hand) {
        IJar jar = player.getCapability(JAR, null).getJar();
        if (this.isPotionActive(PotionInit.TRANSFORM) && jar instanceof HeadJar) {
            HeadJar headjar = (HeadJar) jar;
            if(headjar.canModify()) {
                if (!player.world.isRemote) {
                    SetPlayerJarMessage message = SetPlayerJarMessage.create((EntityPlayerMP) player);
                    PacketHandler.INSTANCE.sendToAll(message);
                }              
                return true;
            } else return super.processInteract(player, hand);
        } else return super.processInteract(player, hand);
    }
}
