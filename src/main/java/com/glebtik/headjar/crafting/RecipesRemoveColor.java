package com.glebtik.headjar.crafting;

import com.glebtik.headjar.items.JarItem;
import com.glebtik.headjar.register.ItemInit;
import com.glebtik.headjar.util.Reference;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;
import java.util.ArrayList;
import java.util.List;

public class RecipesRemoveColor extends net.minecraftforge.registries.IForgeRegistryEntry.Impl<IRecipe> implements IRecipe {
    public RecipesRemoveColor() {
        setRegistryName(Reference.MOD_ID, "removeColor");
    }
    @Override
    public boolean matches(InventoryCrafting inv, World worldIn) {
        List<ItemStack> itemStacks = new ArrayList();
        for(int i = 0; i < inv.getSizeInventory(); i++) {
            ItemStack itemStack = inv.getStackInSlot(i);
            if(itemStack.isEmpty()) {
                continue;
            }
            if(itemStack.getItem() instanceof JarItem){
                itemStacks.add(itemStack);
            }else{
                return false;
            }
        }
        return itemStacks.size() == 1;
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting inv) {
        return new ItemStack(ItemInit.JAR);
    }

    @Override
    public boolean canFit(int width, int height) {
        return width*height>1;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return ItemStack.EMPTY;
    }

}
