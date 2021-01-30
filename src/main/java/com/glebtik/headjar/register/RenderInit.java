package com.glebtik.headjar.register;

import com.glebtik.headjar.entity.HeadlessCreeper;
import com.glebtik.headjar.entity.HeadlessSkeleton;
import com.glebtik.headjar.entity.HeadlessVillager;
import com.glebtik.headjar.entity.HeadlessZombie;
import com.glebtik.headjar.entity.render.RenderHeadlessCreeper;
import com.glebtik.headjar.entity.render.RenderHeadlessSkeleton;
import com.glebtik.headjar.entity.render.RenderHeadlessVillager;
import com.glebtik.headjar.entity.render.RenderHeadlessZombie;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
// import net.minecraft.client.renderer.entity.RenderZombie;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class RenderInit {
    public static void registerEntityRenders() {
        RenderingRegistry.registerEntityRenderingHandler(HeadlessZombie.class, new IRenderFactory<HeadlessZombie>() {
            @Override
            public Render<? super HeadlessZombie> createRenderFor(RenderManager manager) {
                return new RenderHeadlessZombie(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(HeadlessCreeper.class, new IRenderFactory<HeadlessCreeper>() {
            @Override
            public Render<? super HeadlessCreeper> createRenderFor(RenderManager manager) {
                return new RenderHeadlessCreeper(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(HeadlessSkeleton.class, new IRenderFactory<HeadlessSkeleton>() {
            @Override
            public Render<? super HeadlessSkeleton> createRenderFor(RenderManager manager) {
                return new RenderHeadlessSkeleton(manager);
            }
        });
        RenderingRegistry.registerEntityRenderingHandler(HeadlessVillager.class, new IRenderFactory<HeadlessVillager>() {
            @Override
            public Render<? super HeadlessVillager> createRenderFor(RenderManager manager) {
                return new RenderHeadlessVillager(manager);
            }
        });
    }
}