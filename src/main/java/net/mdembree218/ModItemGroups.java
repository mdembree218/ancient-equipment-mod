package net.mdembree218;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.LinkedList;
import java.util.List;

public class ModItemGroups {
    public static final List<ItemStack> ARCHAEOLOGY_CONTENTS = new LinkedList<>();
    public static final ItemGroup ARCHAEOLOGY = FabricItemGroup.builder()
            .displayName(Text.translatable("itemGroup." + AncientEquipmentMod.MOD_ID + ".archaeology"))
            .icon(() -> new ItemStack(ModToolItems.GRUB_HOE))
            .entries((displayContext, entries) -> {
                for (ItemStack item : ARCHAEOLOGY_CONTENTS) {
                    entries.add(item);
                }
            })
            .build();

    public static final List<ItemStack> ANCIENT_WEAPONS_CONTENTS = new LinkedList<>();
    public static final ItemGroup ANCIENT_WEAPONS = FabricItemGroup.builder()
            .displayName(Text.translatable("itemGroup." + AncientEquipmentMod.MOD_ID + ".ancient_weapons"))
            .icon(() -> new ItemStack(ModEquipmentItems.LEVANTINE_BRONZE_AGE_SWORD))
            .entries((displayContext, entries) -> {
                for (ItemStack item : ANCIENT_WEAPONS_CONTENTS) {
                    entries.add(item);
                }
            })
            .build();

    public static void register() {
        Registry.register(Registries.ITEM_GROUP, Identifier.of(AncientEquipmentMod.MOD_ID, "archaeology"), ARCHAEOLOGY);
        Registry.register(Registries.ITEM_GROUP, Identifier.of(AncientEquipmentMod.MOD_ID, "ancient_weapons"), ANCIENT_WEAPONS);
    }
}
