package com.glebtik.headjar.register;

import com.glebtik.headjar.crafting.RecipesRemoveColor;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class CraftingInit {

    @SubscribeEvent
    public static void onRecipeRegister(RegistryEvent.Register<IRecipe> event) {
        event.getRegistry().register(new RecipesRemoveColor());
    }
}
