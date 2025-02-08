package net.mdembree218;

import net.fabricmc.api.ModInitializer;

import net.mdembree218.items.ModItemGroups;
import net.mdembree218.items.ModToolItems;

public class AncientEquipmentMod implements ModInitializer {
	public static final String MOD_ID = "ancient-equipment-mod";
	public static final String MOD_VERSION = "1.0.0-1.20.1-alpha";
	public static final boolean IS_DEBUG = true;
	@Override
	public void onInitialize() {

		LoggerUtil.logInfoMsg("");
		LoggerUtil.logInfoMsg("================ Ancient Equipment ================");

		ModToolItems.registerModItems();
		ModItemGroups.register();
	}
}