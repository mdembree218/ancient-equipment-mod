package net.mdembree218;

import net.fabricmc.api.ModInitializer;

public class AncientEquipmentMod implements ModInitializer {
	public static final String MOD_ID = "ancient-equipment-mod";
//	public static final String MOD_VERSION = "1.21.4.1.0.0-alpha";
	public static final boolean IS_DEBUG = true;
	@Override
	public void onInitialize() {

		LoggerUtil.logInfoMsg("");
		LoggerUtil.logInfoMsg("================ Ancient Equipment ================");

		ModToolItems.registerModItems();
		ModEquipmentItems.registerModItems();
		ModItemGroups.register();
	}
}