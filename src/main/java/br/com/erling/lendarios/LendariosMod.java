package br.com.erling.lendarios;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;

@Mod(LendariosMod.MOD_ID)
public final class LendariosMod {
    public static final String MOD_ID = "lendarios";

    public LendariosMod(IEventBus modEventBus) {
        LendarioItems.ITEMS.register(modEventBus);
        NeoForge.EVENT_BUS.addListener(LendarioCommands::registerCommands);
    }
}
