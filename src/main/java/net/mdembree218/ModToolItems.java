package net.mdembree218;

import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class ModToolItems {
    public static final Item GRUB_HOE = registerItemHandheld("grub_hoe", (settings) -> new GrubHoeItem(ToolMaterial.IRON, 3, -3.2F, settings), new Item.Settings());

    private static Item registerItemHandheld(String name, Function<Item.Settings, Item> factory, Item.Settings settings) {
        Item item = factory.apply(settings.registryKey(ModBlocks.keyOfItem(name)));
        ModItemGroups.ARCHAEOLOGY_CONTENTS.add(item.getDefaultStack());
        SimpleHandheldItemModel.items.add(item);
        return Registry.register(Registries.ITEM, Identifier.of(AncientEquipmentMod.MOD_ID, name), item);
    }

    public static void registerModItems() {
        LoggerUtil.logDebugMsg("Registering Mod Tool Items for " + AncientEquipmentMod.MOD_ID);
    }
}
