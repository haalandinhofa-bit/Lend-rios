package br.com.erling.lendarios;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public final class PowerItem extends Item {
    public enum Power {
        SPEED,
        JUMP,
        HOME_COMPASS,
        WATER_BREATHING,
        DOLPHINS_GRACE,
        FIRE_RESISTANCE,
        NIGHT_VISION,
        SLOW_FALLING,
        HASTE,
        REGENERATION,
        LUCK,
        HEALTH_BOOST,
        COORDINATES
    }

    private final Power power;

    public PowerItem(Properties properties, Power power) {
        super(properties.stacksTo(1));
        this.power = power;
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (!level.isClientSide) {
            activate(level, player);
            player.getCooldowns().addCooldown(stack, 20);
        }

        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    private void activate(Level level, Player player) {
        switch (power) {
            case SPEED -> apply(player, MobEffects.MOVEMENT_SPEED, 45, 1,
                    "A Bota do Vento aumentou sua velocidade!");
            case JUMP -> apply(player, MobEffects.JUMP, 45, 1,
                    "A Batata Saltitante melhorou seu salto!");
            case HOME_COMPASS -> {
                if (level instanceof ServerLevel serverLevel) {
                    BlockPos spawn = serverLevel.getSharedSpawnPos();
                    player.displayClientMessage(Component.literal(
                            "Ponto inicial do mundo: X " + spawn.getX()
                                    + ", Y " + spawn.getY() + ", Z " + spawn.getZ()), true);
                }
            }
            case WATER_BREATHING -> apply(player, MobEffects.WATER_BREATHING, 90, 0,
                    "Respiração aquática ativada!");
            case DOLPHINS_GRACE -> apply(player, MobEffects.DOLPHINS_GRACE, 45, 0,
                    "Você nada como um golfinho!");
            case FIRE_RESISTANCE -> apply(player, MobEffects.FIRE_RESISTANCE, 60, 0,
                    "Resistência ao fogo ativada!");
            case NIGHT_VISION -> apply(player, MobEffects.NIGHT_VISION, 90, 0,
                    "Visão noturna ativada!");
            case SLOW_FALLING -> apply(player, MobEffects.SLOW_FALLING, 45, 0,
                    "Queda lenta ativada!");
            case HASTE -> apply(player, MobEffects.DIG_SPEED, 45, 1,
                    "Mineração rápida ativada!");
            case REGENERATION -> apply(player, MobEffects.REGENERATION, 20, 0,
                    "Regeneração ativada!");
            case LUCK -> apply(player, MobEffects.LUCK, 90, 1,
                    "Sorte aumentada!");
            case HEALTH_BOOST -> apply(player, MobEffects.HEALTH_BOOST, 60, 1,
                    "Vida máxima aumentada temporariamente!");
            case COORDINATES -> {
                BlockPos pos = player.blockPosition();
                player.displayClientMessage(Component.literal(
                        "Sua posição: X " + pos.getX()
                                + ", Y " + pos.getY() + ", Z " + pos.getZ()), true);
            }
        }
    }

    private static void apply(Player player,
                              net.minecraft.core.Holder<net.minecraft.world.effect.MobEffect> effect,
                              int seconds,
                              int amplifier,
                              String message) {
        player.addEffect(new MobEffectInstance(effect, seconds * 20, amplifier));
        player.displayClientMessage(Component.literal(message), true);
    }
}
