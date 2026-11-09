package br.com.erling.lendarios;

import java.util.List;

import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.ItemContainerContents;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

public final class LendarioCommands {
    private LendarioCommands() {}

    public static void registerCommands(RegisterCommandsEvent event) {
        CommandDispatcher<CommandSourceStack> dispatcher = event.getDispatcher();

        dispatcher.register(Commands.literal("lendario")
                .executes(context -> giveRelics(context.getSource())));

        // Alias with the capitalization requested by the project owner.
        dispatcher.register(Commands.literal("Lendario")
                .executes(context -> giveRelics(context.getSource())));
    }

    private static int giveRelics(CommandSourceStack source) throws com.mojang.brigadier.exceptions.CommandSyntaxException {
        ServerPlayer player = source.getPlayerOrException();

        List<ItemStack> relics = List.of(
                new ItemStack(LendarioItems.BOTA_DO_VENTO.get()),
                new ItemStack(LendarioItems.BATATA_SALTITANTE.get()),
                new ItemStack(LendarioItems.BUSSOLA_DO_LAR.get()),
                new ItemStack(LendarioItems.CAPACETE_MARES_UM.get()),
                new ItemStack(LendarioItems.CAPACETE_MARES_DOIS.get()),
                new ItemStack(LendarioItems.AMULETO_SOLAR.get()),
                new ItemStack(LendarioItems.LAMPIAO_LUMINOSO.get()),
                new ItemStack(LendarioItems.PENA_FLUTUANTE.get()),
                new ItemStack(LendarioItems.PICARETA_CONSTRUTORA.get()),
                new ItemStack(LendarioItems.RELOGIO_AURORA.get()),
                new ItemStack(LendarioItems.RELOGIO_CREPUSCULO.get()),
                new ItemStack(LendarioItems.MEDALHAO_FORTUNA.get()),
                new ItemStack(LendarioItems.MAPA_EXPLORADOR.get())
        );

        ItemStack box = new ItemStack(Items.BLACK_SHULKER_BOX);
        box.set(DataComponents.CUSTOM_NAME, Component.literal("Caixa dos Lendários"));
        box.set(DataComponents.CONTAINER, ItemContainerContents.fromItems(relics));

        if (!player.getInventory().add(box)) {
            player.drop(box, false);
        }

        source.sendSuccess(() -> Component.literal(
                "Você recebeu a Caixa dos Lendários com 13 relíquias!"), false);
        return 1;
    }
}
