package net.mdembree218;

import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static RegistryKey<Item> keyOfItem(String id) {
        return RegistryKey.of(RegistryKeys.ITEM, Identifier.of(AncientEquipmentMod.MOD_ID, id));
    }
}
