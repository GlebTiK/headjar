/**
 * 
 * I need help with these GUIs :cry_for_help: iwi
 * 
package com.glebtik.headjar.modificator;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.items.IItemHandler;

public class JarModificatorContainer extends Container {

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return !playerIn.isSpectator();
    }
    
    public JarModificatorContainer(IInventory playerInv, TileEntity te) {
        IItemHandler h = te.getCapability(Ca, facing)
    }

    public ItemStack transferStackInSlot(EntityPlayer playerIn, int fromSlot) {
        ItemStack previous = ItemStack.EMPTY;
        Slot slot = (Slot) this.inventorySlots.get(fromSlot);
    
        if (slot != null && slot.getHasStack()) {
            ItemStack current = slot.getStack();
            previous = current.copy();
    
            if (fromSlot < this.handler.getSlots()) {
                // From the block breaker inventory to player's inventory
                if (!this.mergeItemStack(current, handler.getSlots(), handler.getSlots() + 36, true))
                    return ItemStack.EMPTY;
            } else {
                // From the player's inventory to block breaker's inventory
                if (!this.mergeItemStack(current, 0, handler.getSlots(), false))
                    return ItemStack.EMPTY;
            }
    
            if (current.getCount() == 0) //Use func_190916_E() instead of stackSize 1.11 only 1.11.2 use getCount()
                slot.putStack(ItemStack.EMPTY); //Use ItemStack.field_190927_a instead of (ItemStack)null for a blank item stack. In 1.11.2 use ItemStack.EMPTY
            else
                slot.onSlotChanged();
    
            if (current.getCount() == previous.getCount())
                return null;
            slot.onTake(playerIn, current);
        }
        return previous;
    }
        
}
**/