package net.mdembree218.items;

import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterials;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.mdembree218.AncientEquipmentMod;
import net.mdembree218.LoggerUtil;

public class ModToolItems {
    public static final Item GRUB_HOE = registerCustomToolItem(new GrubHoeItem(ToolMaterials.IRON, 3, -3.2F, new Item.Settings()));

    private static Item registerCustomToolItem(Item item) {
        ModItemGroups.ARCHAEOLOGY_CONTENTS.add(item.getDefaultStack());
        return Registry.register(Registries.ITEM, Identifier.of(AncientEquipmentMod.MOD_ID, "grub_hoe"), item);
    }

    public static void registerModItems() {
        LoggerUtil.logDebugMsg("Registering Mod Tool Items for " + AncientEquipmentMod.MOD_ID);
    }
}
