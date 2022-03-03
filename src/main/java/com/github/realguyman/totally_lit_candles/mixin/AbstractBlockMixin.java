package com.github.realguyman.totally_lit_candles.mixin;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.AbstractCandleBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.server.world.ServerTickScheduler;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.Properties;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(AbstractBlock.class)
public abstract class AbstractBlockMixin {
    @Inject(method = "randomTick", at = @At("HEAD"), cancellable = true)
    private void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo ci) {
        if (AbstractCandleBlock.isLitCandle(state) && !world.hasRain(pos.up())) {
            final ServerTickScheduler<Block> scheduler = world.getBlockTickScheduler();
            final Block block = state.getBlock();

            if (!scheduler.isScheduled(pos, block)) {
                scheduler.schedule(pos, block, 18_000);
            }

            ci.cancel();
        }
    }

    @Inject(method = "scheduledTick", at = @At("HEAD"))
    private void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo ci) {
        if ((state.isIn(BlockTags.CANDLES) || state.isIn(BlockTags.CANDLE_CAKES)) && state.contains(Properties.LIT)) {
            AbstractCandleBlock.extinguish(null, state, world, pos);
        }
    }
}
