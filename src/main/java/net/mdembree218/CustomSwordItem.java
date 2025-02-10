package net.mdembree218;

import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;

public class CustomSwordItem extends SwordItem {


    public CustomSwordItem(ToolMaterial material, int attackDamage, float attackSpeed, Item.Settings settings) {
        super(material, attackDamage, attackSpeed, settings.maxCount(1));
    }
}
