package br.com.erling.lendarios;

import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class LendarioItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(LendariosMod.MOD_ID);

    public static final DeferredItem<Item> BOTA_DO_VENTO =
            relic("bota_do_vento", PowerItem.Power.SPEED);
    public static final DeferredItem<Item> BATATA_SALTITANTE =
            relic("batata_saltitante", PowerItem.Power.JUMP);
    public static final DeferredItem<Item> BUSSOLA_DO_LAR =
            relic("bussola_do_lar", PowerItem.Power.HOME_COMPASS);
    public static final DeferredItem<Item> CAPACETE_MARES_UM =
            relic("capacete_mares_um", PowerItem.Power.WATER_BREATHING);
    public static final DeferredItem<Item> CAPACETE_MARES_DOIS =
            relic("capacete_mares_dois", PowerItem.Power.DOLPHINS_GRACE);
    public static final DeferredItem<Item> AMULETO_SOLAR =
            relic("amuleto_solar", PowerItem.Power.FIRE_RESISTANCE);
    public static final DeferredItem<Item> LAMPIAO_LUMINOSO =
            relic("lampiao_luminoso", PowerItem.Power.NIGHT_VISION);
    public static final DeferredItem<Item> PENA_FLUTUANTE =
            relic("pena_flutuante", PowerItem.Power.SLOW_FALLING);
    public static final DeferredItem<Item> PICARETA_CONSTRUTORA =
            relic("picareta_construtora", PowerItem.Power.HASTE);
    public static final DeferredItem<Item> RELOGIO_AURORA =
            relic("relogio_aurora", PowerItem.Power.REGENERATION);
    public static final DeferredItem<Item> RELOGIO_CREPUSCULO =
            relic("relogio_crepusculo", PowerItem.Power.LUCK);
    public static final DeferredItem<Item> MEDALHAO_FORTUNA =
            relic("medalhao_fortuna", PowerItem.Power.HEALTH_BOOST);
    public static final DeferredItem<Item> MAPA_EXPLORADOR =
            relic("mapa_explorador", PowerItem.Power.COORDINATES);

    private LendarioItems() {}

    private static DeferredItem<Item> relic(String id, PowerItem.Power power) {
        return ITEMS.register(id, () -> new PowerItem(new Item.Properties(), power));
    }
}
