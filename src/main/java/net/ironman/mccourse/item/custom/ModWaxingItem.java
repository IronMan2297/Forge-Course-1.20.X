package net.ironman.mccourse.item.custom;

import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import net.ironman.mccourse.block.ModBlocks;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;

import java.util.Optional;
import java.util.function.Supplier;

public class ModWaxingItem extends Item {
    public static final com.google.common.base.Supplier<ImmutableBiMap<Object, Object>> WAXABLES = Suppliers.memoize(() -> {
        return ImmutableBiMap.builder()
                .put(ModBlocks.RUBY_BLOCK, ModBlocks.WAXED_RUBY_BLOCK)
                .put(ModBlocks.EXPOSED_RUBY_BLOCK, ModBlocks.WAXED_EXPOSED_RUBY_BLOCK)
                .put(ModBlocks.WEATHERED_RUBY_BLOCK, ModBlocks.WAXED_WEATHERED_RUBY_BLOCK)
                .put(ModBlocks.DEGRADED_RUBY_BLOCK, ModBlocks.WAXED_DEGRADED_RUBY_BLOCK).build();
    });
    public static final Supplier<BiMap<Block, Block>> WAX_OFF_BY_BLOCK = Suppliers.memoize(() -> {
        return ((BiMap)WAXABLES.get()).inverse();
    });

    public ModWaxingItem(Item.Properties pProperties) {
        super(pProperties);
    }

    public InteractionResult useOn(UseOnContext pContext) {
        Level $$1 = pContext.getLevel();
        BlockPos $$2 = pContext.getClickedPos();
        BlockState $$3 = $$1.getBlockState($$2);
        return (InteractionResult)getWaxed($$3).map((p_238251_) -> {
            Player $$4 = pContext.getPlayer();
            ItemStack $$5 = pContext.getItemInHand();
            if ($$4 instanceof ServerPlayer) {
                CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger((ServerPlayer)$$4, $$2, $$5);
            }

            $$5.shrink(1);
            $$1.setBlock($$2, p_238251_, 11);
            $$1.gameEvent(GameEvent.BLOCK_CHANGE, $$2, GameEvent.Context.of($$4, p_238251_));
            $$1.levelEvent($$4, 3003, $$2, 0);
            return InteractionResult.sidedSuccess($$1.isClientSide);
        }).orElse(InteractionResult.PASS);
    }

    public static Optional<BlockState> getWaxed(BlockState pState) {
        return Optional.ofNullable((Block)((BiMap)WAXABLES.get()).get(pState.getBlock())).map((p_150877_) -> {
            return p_150877_.withPropertiesOf(pState);
        });
    }
}
