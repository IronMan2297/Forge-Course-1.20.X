package net.ironman.mccourse.util;

import net.ironman.mccourse.MCCourseMod;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

public class ModWoodTypes {
    public static final WoodType WALNUT = WoodType.register(new WoodType(MCCourseMod.MOD_ID + ":walnut", BlockSetType.OAK));
}
