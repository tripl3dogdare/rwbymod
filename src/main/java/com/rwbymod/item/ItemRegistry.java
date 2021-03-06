package com.rwbymod.item;

import com.rwbymod.misc.EnumDustStimulus;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class ItemRegistry {

	public static Item dust;
	public static Item dustCrystal;
	public static Item scroll;
	
	public static void init() {
		dust = register(new ItemDust());
		dustCrystal = register(new ItemDustContainer("dust_crystal") {});
		scroll = register(new ItemScroll());
	}
	
	// ----- UTILITY FUNCTIONS ----- //
	
	private static Item register(Item item) { return GameRegistry.register(item); }
	
}
