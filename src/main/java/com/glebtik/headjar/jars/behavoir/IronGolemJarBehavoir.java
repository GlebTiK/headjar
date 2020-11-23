package com.glebtik.headjar.jars.behavoir;

import com.glebtik.headjar.jars.HeadJar;
import com.glebtik.headjar.jars.IJar;
import com.glebtik.headjar.jars.IronGolemJar;
import com.glebtik.headjar.network.PacketHandler;
import com.glebtik.headjar.network.SetPlayerJarMessage;
import com.glebtik.headjar.util.Reference;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockWorldState;
import net.minecraft.block.state.pattern.BlockMaterialMatcher;
import net.minecraft.block.state.pattern.BlockPattern;
import net.minecraft.block.state.pattern.BlockStateMatcher;
import net.minecraft.block.state.pattern.FactoryBlockPattern;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import static com.glebtik.headjar.capabilities.JarProvider.JAR;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class IronGolemJarBehavoir {
    private static FactoryBlockPattern golem = FactoryBlockPattern.start().aisle("###", "~#~").where('#', BlockWorldState.hasState(BlockStateMatcher.forBlock(Blocks.IRON_BLOCK))).where('~', BlockWorldState.hasState(BlockMaterialMatcher.forMaterial(Material.AIR)));

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if(event.player instanceof EntityPlayerMP) {
            EntityPlayerMP player = (EntityPlayerMP) event.player;
            IJar jar = player.getCapability(JAR, null).getJar();
            if(jar instanceof HeadJar) {
                HeadJar headJar = (HeadJar) player.getCapability(JAR, null).getJar();
                if(headJar.canModify()) {
                    BlockPattern pattern = golem.build();
                    BlockPattern.PatternHelper patternHelper = pattern.match(player.world, new BlockPos(player.posX, player.posY-1, player.posZ));
                    if(patternHelper != null){
                        for (int j = 0; j < pattern.getPalmLength(); ++j) {
                            for (int k = 0; k < pattern.getThumbLength(); ++k) {
                                player.world.setBlockState(patternHelper.translateOffset(j, k, 0).getPos(), Blocks.AIR.getDefaultState(), 3);
                                HeadJar oldJar = (HeadJar) player.getCapability(JAR, null).getJar();
                                IronGolemJar newJar = new IronGolemJar();
                                newJar.setColor(oldJar.getColor());
                                player.getCapability(JAR, null).setJar(newJar);
                                SetPlayerJarMessage message = SetPlayerJarMessage.create((EntityPlayerMP) player);
                                PacketHandler.INSTANCE.sendToAll(message);
                            }
                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onDamage(LivingHurtEvent event) {
        if(event.getEntityLiving() instanceof EntityPlayerMP) {
            EntityPlayerMP player = (EntityPlayerMP) event.getEntityLiving();
            IJar jar = player.getCapability(JAR, null).getJar();
            if(jar instanceof IronGolemJar) {
                if(event.getSource() == DamageSource.FALL) {
                    event.setCanceled(true);
                    return;
                }
                if(event.getSource() == DamageSource.DROWN) {
                    event.setCanceled(true);
                    return;
                }
            }
        }
    }


}
