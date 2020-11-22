package com.glebtik.headjar.jars;

import com.glebtik.headjar.client.render.head.HeadModel;
import com.glebtik.headjar.client.render.head.JarModel;
import com.glebtik.headjar.client.render.iron_golem.RenderIronGolemForm;
import com.glebtik.headjar.util.RenderUtils;
import com.glebtik.headjar.util.Reference;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelIronGolem;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import javax.swing.text.JTextComponent;

public class IronGolemJar extends HeadJar {

    RenderIronGolemForm form = new RenderIronGolemForm(Minecraft.getMinecraft().getRenderManager());

    public IronGolemJar() {
        headXOff = 0;
        headZOff = 0;
        headYOff = 0.1f;
    }
    @Override
    public void doRender(EntityPlayer player, float partialRenderTick, RenderPlayer playerRenderer) {
        super.doRender(player, partialRenderTick, playerRenderer);
        EntityIronGolem ironGolem = new EntityIronGolem(player.world);
        RenderUtils.cloneValues(player, ironGolem);

        RenderUtils.cloneRenderValues(((ModelIronGolem)form.getMainModel()).ironGolemHead, ((JarModel)rendererJar.getMainModel()).bb_main);
        RenderUtils.cloneRenderValues(((ModelIronGolem)form.getMainModel()).ironGolemHead, ((HeadModel)rendererHead.getMainModel()).bb_main);
        form.doRender(ironGolem, renderXOff, renderYOff, renderZOff, player.getRotationYawHead(),partialRenderTick);
    }

    @Override
    public void serverTick(TickEvent.PlayerTickEvent event) {
        event.player.setSprinting(false);
    }
    public void clientTick(TickEvent.PlayerTickEvent event) {
        event.player.setSprinting(false);
        KeyBinding.setKeyBindState(Minecraft.getMinecraft().gameSettings.keyBindSprint.getKeyCode(), false);
    }

    @Override
    public void writeNBT(NBTTagCompound nbt) {
        super.writeNBT(nbt);
    }

    @Override
    public void readNBT(NBTTagCompound nbt) {
        super.readNBT(nbt);
    }

    @Override
    public void writeByteBuf(ByteBuf buf) {
        super.writeByteBuf(buf);
    }

    @Override
    public void readByteBuf(ByteBuf buf) {
        super.readByteBuf(buf);
    }

    @Override
    public ResourceLocation getRegistryName() {
        return new ResourceLocation(Reference.MOD_ID, "iron_golem_jar");
    }

    @Override
    public float getEyeHeight() {
        return 2.45f;
    }

    @Override
    public float getHeight() {
        return 2.7f;
    }

    @Override
    public float getWidth() {
        return 2.45f;
    }

    @Override
    public boolean canModify() {
        return false;
    }
}
