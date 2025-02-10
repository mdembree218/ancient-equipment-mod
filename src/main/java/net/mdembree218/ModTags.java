package net.mdembree218;

import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {
    public static TagKey<Item> BRONZE_TOOL_MATERIALS = TagKey.of(RegistryKeys.ITEM, Identifier.of(AncientEquipmentMod.MOD_ID, "bronze_tool_materials"));
}
