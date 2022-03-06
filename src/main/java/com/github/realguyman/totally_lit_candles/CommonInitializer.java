package com.github.realguyman.totally_lit_candles;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.world.GameRules.BooleanRule;
import net.minecraft.world.GameRules.Category;
import net.minecraft.world.GameRules.IntRule;
import net.minecraft.world.GameRules.Key;

public class CommonInitializer implements ModInitializer {
    public static final Key<IntRule> CANDLE_BURN_DURATION = GameRuleRegistry.register("candleBurnDuration", Category.UPDATES, GameRuleFactory.createIntRule(18_000));
    public static final Key<BooleanRule> DO_CANDLES_EXTINGUISH_IN_RAIN = GameRuleRegistry.register("doCandlesExtinguishInRain", Category.UPDATES, GameRuleFactory.createBooleanRule(true));
    public static final Key<BooleanRule> DO_CANDLES_EXTINGUISH_OVER_TIME = GameRuleRegistry.register("doCandlesExtinguishOverTime", Category.UPDATES, GameRuleFactory.createBooleanRule(true));

    @Override
    public void onInitialize() {}
}
