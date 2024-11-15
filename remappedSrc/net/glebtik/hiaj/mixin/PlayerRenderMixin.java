package net.glebtik.hiaj.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.At;

import net.glebtik.hiaj.HeadInAJar;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.PlayerModelPart;
import net.minecraft.client.render.entity.EntityRendererFactory.Context;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Identifier;
import net.minecraft.client.util.math.MatrixStack;

@Mixin(PlayerEntityRenderer.class)
public class PlayerRenderMixin extends LivingEntityRenderer {

    public PlayerRenderMixin(Context ctx, EntityModel model, float shadowRadius) {
        super(ctx, model, shadowRadius);
        // Auto-generated constructor stub
    }

    @Inject(at = @At("HEAD"), cancellable = true, method = "setModelPose")
    private void setModelPose(AbstractClientPlayerEntity player, CallbackInfo info) {
        info.cancel();
        PlayerEntityModel<AbstractClientPlayerEntity> pem = (PlayerEntityModel)this.getModel();
        HeadInAJar.LOGGER.info("aa");
        pem.setVisible(!player.isSpectator());
        pem.head.visible=true;
        pem.hat.visible=player.isPartVisible(PlayerModelPart.HAT);
        pem.jacket.visible=false;
        pem.leftArm.visible=false;
        pem.leftSleeve.visible=false;
        pem.leftLeg.visible=false;
        pem.leftPants.visible=false;
        pem.body.visible=false;
        pem.rightArm.visible=false;
        pem.rightSleeve.visible=false;
        pem.rightLeg.visible=false;
        pem.rightPants.visible=false;
        
    }

    @Override
    public Identifier getTexture(Entity entity) {
        // TODO Auto-generated method stub
        return ((AbstractClientPlayerEntity)entity).getSkinTexture();
    }

    @Inject(at = @At("TAIL"), method = "render")
    public void render(LivingEntity livingEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        
    }
    
}
