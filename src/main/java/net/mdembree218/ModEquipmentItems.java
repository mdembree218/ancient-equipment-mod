package net.mdembree218;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class ModEquipmentItems {

    public static final Item LEVANTINE_BRONZE_AGE_SWORD = registerItemHandheld("levantine_bronze_age_sword", (settings)  -> new CustomSwordItem(CustomToolMaterials.BRONZE, 3, -2.4F, settings), new Item.Settings());

    public static void registerModItems() {
        LoggerUtil.logDebugMsg("Registering Mod Equipment Items for " + AncientEquipmentMod.MOD_ID);
    }

    private static Item registerItemHandheld(String name, Function<Item.Settings, Item> factory, Item.Settings settings) {
        Item item = factory.apply(settings.registryKey(ModBlocks.keyOfItem(name)));
        ModItemGroups.ANCIENT_WEAPONS_CONTENTS.add(item.getDefaultStack());
        SimpleHandheldItemModel.items.add(item);
        return Registry.register(Registries.ITEM, Identifier.of(AncientEquipmentMod.MOD_ID, name), item);
    }
}
