package com.rwbymod.item;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemRegistry {

	public static Item dust;
	public static Item dustCrystal;
	
	public static void init() {
		dust = register(new ItemDust());
		dustCrystal = register(new ItemDustCrystal());
	}
	
	// ----- UTILITY FUNCTIONS ----- //
	
	private static Item register(Item item) { return GameRegistry.register(item); }
	
}
