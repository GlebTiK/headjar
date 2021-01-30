package com.glebtik.headjar.register;

import com.glebtik.headjar.HeadInAJar;
import com.glebtik.headjar.entity.HeadlessBody;
import com.glebtik.headjar.entity.HeadlessCreeper;
import com.glebtik.headjar.entity.HeadlessSkeleton;
import com.glebtik.headjar.entity.HeadlessVillager;
import com.glebtik.headjar.entity.HeadlessZombie;
import com.glebtik.headjar.util.Reference;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class EntityInit {
    public static void RegisterEntities() {
        RegisterEntity("headless_zombie", HeadlessZombie.class, 69 /* Nice */, 50, 41373, 25630);
        EntityRegistry.addSpawn(HeadlessZombie.class, 100, 4, 4, EnumCreatureType.MONSTER);
        RegisterEntity("headless_villager", HeadlessVillager.class, 70, 50, 5322800, 10252635);
        // TODO: make dis spawn
        RegisterEntity("headless_creeper", HeadlessCreeper.class, 71, 50, 826624, 0);
        EntityRegistry.addSpawn(HeadlessCreeper.class, 100, 4, 4, EnumCreatureType.MONSTER);
        RegisterEntity("headless_skeleton", HeadlessSkeleton.class, 72, 50, 11447982, 2434341);
        EntityRegistry.addSpawn(HeadlessSkeleton.class, 100, 4, 4, EnumCreatureType.MONSTER);
        RegisterEntityWithoutEgg("headless_body", HeadlessBody.class, 73, 50);
    }
    private static void RegisterEntity(String name, Class<? extends Entity> entity, int id, int range, int c1 /*the main color*/, int c2 /*the dots*/) {
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID + ":" + name), entity, name, id, HeadInAJar.Instance, range, 1, true, c1, c2);
    }
    private static void RegisterEntityWithoutEgg(String name, Class<? extends Entity> entity, int id, int range) {
        EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID + ":" + name), entity, name, id, HeadInAJar.Instance, range, 1, true);
    }    
}