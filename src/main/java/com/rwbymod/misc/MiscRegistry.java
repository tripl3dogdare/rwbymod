package com.rwbymod.misc;

import com.rwbymod.RWBYMod;
import com.rwbymod.item.ItemRegistry;
import com.rwbymod.world.WorldGenDustOre;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class MiscRegistry {
	
	public static CreativeTabs tabMain;
	
	public static void init() {
		tabMain = new CreativeTabs(RWBYMod.MODID+".tabMain") {
			public ItemStack getTabIconItem() { return new ItemStack(ItemRegistry.dust); }
		};
		
		GameRegistry.registerWorldGenerator(new WorldGenDustOre(), 0);
	}

}
