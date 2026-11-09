package br.com.erling.lendarios;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderArmEvent;
import net.neoforged.neoforge.client.event.RenderHandEvent;
import net.neoforged.neoforge.client.event.RenderPlayerEvent;

/**
 * Visual do Lampião: o portador não é desenhado em terceira pessoa,
 * e braços/mãos/itens também não aparecem na visão em primeira pessoa.
 *
 * O equipamento continua equipado no servidor. Só a renderização é
 * suprimida, então armadura e itens voltam automaticamente quando o
 * Lampião sai das duas mãos.
 */
@EventBusSubscriber(modid = LendariosMod.MOD_ID, value = Dist.CLIENT)
public final class LampiaoClientEvents {
    private LampiaoClientEvents() {
    }

    @SubscribeEvent
    public static void onRenderPlayer(RenderPlayerEvent.Pre event) {
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.level == null) {
            return;
        }

        // Na 1.21.4 os eventos de renderização usam PlayerRenderState.
        // O id do estado identifica o jogador correspondente no ClientLevel.
        Entity entity = minecraft.level.getEntity(event.getRenderState().id);
        if (entity instanceof Player player && isHoldingLampiao(player)) {
            // Cancela o jogador inteiro: corpo, armadura, capas e itens nas mãos.
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onRenderFirstPersonHand(RenderHandEvent event) {
        Player player = Minecraft.getInstance().player;
        if (isHoldingLampiao(player)) {
            // Cancela ambas as mãos, inclusive o item que estiver na mão oposta.
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onRenderFirstPersonArm(RenderArmEvent event) {
        if (isHoldingLampiao(event.getPlayer())) {
            event.setCanceled(true);
        }
    }

    private static boolean isHoldingLampiao(Player player) {
        return player != null
                && (player.getMainHandItem().is(LendarioItems.LAMPIAO_LUMINOSO.get())
                || player.getOffhandItem().is(LendarioItems.LAMPIAO_LUMINOSO.get()));
    }
}
