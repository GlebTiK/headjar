/**
 * 
 * Everything here works! So don't touch it!
 * 
package com.glebtik.headjar.register;

import com.glebtik.headjar.blocks.JarModificator;
import com.glebtik.headjar.util.Reference;

import net.minecraft.block.Block;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class BlockInit {
    public static final JarModificator MODIFICATOR = new JarModificator();

    @SubscribeEvent
    public static void registerBlock(RegistryEvent.Register<Block> event) {
        PropertyDirection PROP_FACING = PropertyDirection.create("facing");
        IStateMapper mapper = new StateMap.Builder().ignore(PROP_FACING).build();
        ModelLoader.setCustomStateMapper(MODIFICATOR, mapper);
        registerBlock(event, MODIFICATOR);
    }
    @SubscribeEvent
    public static void registerItem(RegistryEvent.Register<Item> event) {
        Item jarModificator = new ItemBlock(MODIFICATOR).setRegistryName("jar_modificator").setUnlocalizedName("jar_modificator").setCreativeTab(CreativeTabs.REDSTONE);
        ItemInit.registerItem(jarModificator, event);
    }

    public static void registerBlock(RegistryEvent.Register<Block> event, Block block) {
        event.getRegistry().register(block);
    }

}
**/