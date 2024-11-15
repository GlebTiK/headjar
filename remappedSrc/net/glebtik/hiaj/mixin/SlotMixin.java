package net.glebtik.hiaj.mixin;

import net.glebtik.hiaj.HeadInAJar;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Slot.class)
public class SlotMixin {

    @Shadow 
    @Final 
    public Inventory inventory;

    @Shadow 
    @Final 
    private int index;

    @Inject(at = @At("HEAD"), method = "canInsert", cancellable = true)
    public void canInsert(ItemStack stack, CallbackInfoReturnable<Boolean> info) {
        if(!MinecraftClient.getInstance().isOnThread()) return;
        HeadInAJar.LOGGER.debug("CanInsert called");
        if(MinecraftClient.getInstance().player != null) {
            PlayerInventory playerInventory = MinecraftClient.getInstance().player.getInventory();
            HeadInAJar.LOGGER.debug("Player is not null, index is {}", index);
            if(inventory == playerInventory && !(index == 0 || index == 39)) {
                HeadInAJar.LOGGER.debug("CanInsert Setting return value to false");
                info.setReturnValue(false);
            }
        }
    }

    @Inject(at = @At("HEAD"), method = "canTakeItems", cancellable = true)
    public void canTakeItems(PlayerEntity playerEntity, CallbackInfoReturnable<Boolean> info) {
        if(!MinecraftClient.getInstance().isOnThread()) return;
        HeadInAJar.LOGGER.debug("CanTake called, index is {}", index);
        if(inventory == playerEntity.getInventory() && !(index == 0 || index == 39)) {
            HeadInAJar.LOGGER.debug("CanTake Setting return value to false");
            info.setReturnValue(false);
        }
    }
}