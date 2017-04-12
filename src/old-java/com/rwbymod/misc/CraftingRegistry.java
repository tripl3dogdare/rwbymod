package com.rwbymod.misc;

import com.rwbymod.block.BlockRegistry;
import com.rwbymod.item.ItemRegistry;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class CraftingRegistry {
	
	public static void init() {
		addDustMixingRecipe(EnumDustType.LIGHTNING, EnumDustType.WIND, EnumDustType.FIRE);
		addDustMixingRecipe(EnumDustType.ICE, EnumDustType.WIND, EnumDustType.WATER);
		addDustMixingRecipe(EnumDustType.STEAM, EnumDustType.WATER, EnumDustType.FIRE);
		addDustMixingRecipe(EnumDustType.GRAVITY, EnumDustType.EARTH, EnumDustType.WIND);
		
		for(EnumDustType type : EnumDustType.primary)
			GameRegistry.addSmelting(new ItemStack(BlockRegistry.dustOre, 1, type.ordinal()), new ItemStack(ItemRegistry.dust, 4, type.ordinal()), .7f);
		
		for(EnumDustType type : EnumDustType.all)
			GameRegistry.addShapedRecipe(
				new ItemStack(ItemRegistry.dustCrystal, 1, type.ordinal()),
				" d ",
				"dqd",
				" d ",
				'd', new ItemStack(ItemRegistry.dust, 1, type.ordinal()),
				'q', Items.QUARTZ
			);
		
		GameRegistry.addRecipe(new ShapedOreRecipe(
			new ItemStack(ItemRegistry.scroll), 
			"ini",
			"ngn",
			"ici",
			'i', "ingotIron",
			'n', "nuggetIron",
			'g', "paneGlassColorless",
			'c', new ItemStack(ItemRegistry.dustCrystal, 1, OreDictionary.WILDCARD_VALUE)
		));
	}
	
	private static void addDustMixingRecipe(EnumDustType out, EnumDustType in1, EnumDustType in2) {
		GameRegistry.addShapelessRecipe(
			new ItemStack(ItemRegistry.dust, 2, out.ordinal()),
			new ItemStack(ItemRegistry.dust, 1, in1.ordinal()),
			new ItemStack(ItemRegistry.dust, 1, in2.ordinal())
		);
		
		GameRegistry.addShapelessRecipe(
			new ItemStack(ItemRegistry.dust, 4, out.ordinal()),
			new ItemStack(ItemRegistry.dust, 1, in1.ordinal()),
			new ItemStack(ItemRegistry.dust, 1, in1.ordinal()),
			new ItemStack(ItemRegistry.dust, 1, in2.ordinal()),
			new ItemStack(ItemRegistry.dust, 1, in2.ordinal())
		);
		
		GameRegistry.addShapelessRecipe(
			new ItemStack(ItemRegistry.dust, 6, out.ordinal()),
			new ItemStack(ItemRegistry.dust, 1, in1.ordinal()),
			new ItemStack(ItemRegistry.dust, 1, in1.ordinal()),
			new ItemStack(ItemRegistry.dust, 1, in1.ordinal()),
			new ItemStack(ItemRegistry.dust, 1, in2.ordinal()),
			new ItemStack(ItemRegistry.dust, 1, in2.ordinal()),
			new ItemStack(ItemRegistry.dust, 1, in2.ordinal())
		);
		
		GameRegistry.addShapelessRecipe(
			new ItemStack(ItemRegistry.dust, 8, out.ordinal()),
			new ItemStack(ItemRegistry.dust, 1, in1.ordinal()),
			new ItemStack(ItemRegistry.dust, 1, in1.ordinal()),
			new ItemStack(ItemRegistry.dust, 1, in1.ordinal()),
			new ItemStack(ItemRegistry.dust, 1, in1.ordinal()),
			new ItemStack(ItemRegistry.dust, 1, in2.ordinal()),
			new ItemStack(ItemRegistry.dust, 1, in2.ordinal()),
			new ItemStack(ItemRegistry.dust, 1, in2.ordinal()),
			new ItemStack(ItemRegistry.dust, 1, in2.ordinal())
		);
	}

}
