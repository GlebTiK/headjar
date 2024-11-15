package net.glebtik.hiaj.mixin;

import net.minecraft.entity.player.HungerManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributes;

import net.glebtik.hiaj.HeadInAJar;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class PlayerMixin {

	@Shadow
	@Inject(at = @At("HEAD"), method = "tick")
	private void tick(CallbackInfo info) {
		if (((LivingEntity) (Object) this) instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity) (Object) this;
			if (!player.isCreative() && (player.world instanceof ServerWorld)) {
				EntityAttributeInstance HP = player.getAttributeInstance(EntityAttributes.GENERIC_MAX_HEALTH);
				if (HP != null) {
					if (HP.getBaseValue() != 6.0D) {
						HP.setBaseValue(6.0D);
					}
					if (player.getHealth() > 6.0F) player.setHealth(6.0F);
				}
				HungerManager hunger = player.getHungerManager();
				
				// There is no way to set player's maximum hunger bar to lower then 20..
				// So I just lower it to 6 when it's more then it.
				if (hunger.getFoodLevel() > 6) hunger.setFoodLevel(6);

				for (int i = 1; i < player.getInventory().size(); i++) {
					if (i == 39) continue;
					if (!player.getInventory().getStack(i).isEmpty())
						HeadInAJar.LOGGER.info("Droppped {} in slot {}",
								player.getInventory().getStack(i).getName(), i);
					player.dropItem(player.getInventory().getStack(i), false, false);
					player.getInventory().removeStack(i);
				}

			}
		}
	}
}
