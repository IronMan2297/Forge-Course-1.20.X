package net.ironman.mccourse.datagen;

import net.ironman.mccourse.MCCourseMod;
import net.ironman.mccourse.block.ModBlocks;
import net.ironman.mccourse.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, MCCourseMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ModItems.ALEXANDRITE);
        simpleItem(ModItems.RAW_ALEXANDRITE);

        simpleItem(ModItems.KOHLRABI);
        simpleItem(ModItems.METAL_DETECTOR);
        simpleItem(ModItems.PEAT_BRICK);

        handHeldItem(ModItems.ALEXANDRITE_SWORD);
        handHeldItem(ModItems.ALEXANDRITE_PICKAXE);
        handHeldItem(ModItems.ALEXANDRITE_AXE);
        handHeldItem(ModItems.ALEXANDRITE_SHOVEL);
        handHeldItem(ModItems.ALEXANDRITE_HOE);
        handHeldItem(ModItems.ALEXANDRITE_PAXEL);
        handHeldItem(ModItems.ALEXANDRITE_HAMMER);

        //simpleItem(ModItems.ALEXANDRITE_HELMET);
        //simpleItem(ModItems.ALEXANDRITE_CHESTPALTE);
        //simpleItem(ModItems.ALEXANDRITE_LEGGINGS);
        //simpleItem(ModItems.ALEXANDRITE_BOOTS);

        simpleItem(ModItems.ALEXANDRITE_HORSE_ARMOR);

        //simpleItem(ModItems.DATA_TABLET);
        simpleItem(ModItems.KOHLRABI_SEEDS);
        simpleItem(ModItems.BAR_BRAWL_RECORD);
        simpleItem(ModItems.SOAP_WATER_BUCKET);

        buttonItem(ModBlocks.ALEXANDRITE_BUTTON, ModBlocks.ALEXANDRITE_BLOCK);
        fenceItem(ModBlocks.ALEXANDRITE_FENCE, ModBlocks.ALEXANDRITE_BLOCK);
        wallItem(ModBlocks.ALEXANDRITE_WALL, ModBlocks.ALEXANDRITE_BLOCK);

        simpleBlockItem(ModBlocks.ALEXANDRITE_DOOR);
        simpleBlockItem(ModBlocks.SNAPDRAGON);

        complexBlock(ModBlocks.GEM_EMPOWERING_STATION.get());

        saplingItem(ModBlocks.WALNUT_SAPLING);

        simpleItem(ModItems.WALNUT_SIGN);
        simpleItem(ModItems.WALNUT_HANGING_SIGN);

        withExistingParent(ModItems.RHINO_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));

        simpleItem(ModItems.DICE);
        simpleItem(ModItems.WALNUT_BOAT);
        simpleItem(ModItems.WALNUT_CHEST_BOAT);

        simpleItem(ModItems.CATTAIL);
        simpleItem(ModItems.CATTAIL_SEEDS);
    }

    private ItemModelBuilder saplingItem(RegistryObject<Block> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(MCCourseMod.MOD_ID, "block/" + item.getId().getPath()));
    }

    private ItemModelBuilder complexBlock(Block block) {
        return withExistingParent(ForgeRegistries.BLOCKS.getKey(block).getPath(), new ResourceLocation(MCCourseMod.MOD_ID,
                "block/" + ForgeRegistries.BLOCKS.getKey(block).getPath()));
    }

    public void fenceItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/fence_inventory"))
                .texture("texture", new ResourceLocation(MCCourseMod.MOD_ID, "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    public void wallItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/wall_inventory"))
                .texture("wall", new ResourceLocation(MCCourseMod.MOD_ID, "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    public void buttonItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/button_inventory"))
                .texture("texture",  new ResourceLocation(MCCourseMod.MOD_ID, "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    private ItemModelBuilder handHeldItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(MCCourseMod.MOD_ID, "item/" + item.getId().getPath()));
    }

    private ItemModelBuilder simpleBlockItem(RegistryObject<Block> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(MCCourseMod.MOD_ID, "item/" + item.getId().getPath()));
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(MCCourseMod.MOD_ID,"item/" + item.getId().getPath()));
    }
}