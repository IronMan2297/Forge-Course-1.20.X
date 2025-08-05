package net.ironman.mccourse.block.custom;

import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import net.ironman.mccourse.block.ModBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ChangeOverTimeBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Optional;
import java.util.function.Supplier;

public interface GemDegradable extends ChangeOverTimeBlock<GemDegradable.GemDegradationLevel> {
    com.google.common.base.Supplier<ImmutableBiMap<Object, Object>> NEXT_BY_BLOCK = Suppliers.memoize(() -> {
        return ImmutableBiMap.builder()
                .put(ModBlocks.RUBY_BLOCK.get(), ModBlocks.EXPOSED_RUBY_BLOCK.get())
                .put(ModBlocks.EXPOSED_RUBY_BLOCK.get(), ModBlocks.WEATHERED_RUBY_BLOCK.get())
                .put(ModBlocks.WEATHERED_RUBY_BLOCK.get(), ModBlocks.DEGRADED_RUBY_BLOCK.get()).build();
    });
    Supplier<BiMap<Block, Block>> PREVIOUS_BY_BLOCK = Suppliers.memoize(() -> {
        return ((BiMap)NEXT_BY_BLOCK.get()).inverse();
    });

    static Optional<Block> getPrevious(Block pBlock) {
        return Optional.ofNullable((Block)((BiMap)PREVIOUS_BY_BLOCK.get()).get(pBlock));
    }

    static Block getFirst(Block pBlock) {
        Block $$1 = pBlock;

        for(Block $$2 = (Block)((BiMap)PREVIOUS_BY_BLOCK.get()).get($$1); $$2 != null; $$2 = (Block)((BiMap)PREVIOUS_BY_BLOCK.get()).get($$1)) {
            $$1 = $$2;
        }

        return $$1;
    }

    static Optional<BlockState> getPrevious(BlockState pState) {
        return getPrevious(pState.getBlock()).map((p_154903_) -> {
            return p_154903_.withPropertiesOf(pState);
        });
    }

    static Optional<Block> getNext(Block pBlock) {
        return Optional.ofNullable((Block)((BiMap)NEXT_BY_BLOCK.get()).get(pBlock));
    }

    static BlockState getFirst(BlockState pState) {
        return getFirst(pState.getBlock()).withPropertiesOf(pState);
    }

    default Optional<BlockState> getNext(BlockState pState) {
        return getNext(pState.getBlock()).map((p_154896_) -> {
            return p_154896_.withPropertiesOf(pState);
        });
    }

    default float getChanceModifier() {
        return this.getAge() == GemDegradationLevel.UNAFFECTED ? 0.75F : 1.0F;
    }

    public static enum GemDegradationLevel {
        UNAFFECTED,
        EXPOSED,
        WEATHERED,
        DEGRADED;

        private GemDegradationLevel() {
        }
    }
}
