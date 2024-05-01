package net.ironman.mccourse.worldgen.tree;

import net.ironman.mccourse.MCCourseMod;
import net.ironman.mccourse.worldgen.tree.custom.WalnutFoliagePlacer;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModFoliagePlacerType {
    public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACERS =
            DeferredRegister.create(Registries.FOLIAGE_PLACER_TYPE, MCCourseMod.MOD_ID);

    public static final RegistryObject<FoliagePlacerType<WalnutFoliagePlacer>> WALNUT_FOLIAGE_PALCER =
            FOLIAGE_PLACERS.register("walnut_foliage_placer", () -> new FoliagePlacerType<>(WalnutFoliagePlacer.CODEC));

    public static void register(IEventBus eventBus) {
        FOLIAGE_PLACERS.register(eventBus);
    }
}
