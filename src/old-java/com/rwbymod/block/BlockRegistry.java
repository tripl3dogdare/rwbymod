package com.rwbymod.block;

import com.rwbymod.item.ItemBlockMeta;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlockRegistry {
	
	public static Block dustOre;

	public static void init() {
		dustOre = register(new BlockDustOre(), true);
	}
	
	// ----- UTILITY FUNCTIONS ----- //
	
	private static Block register(Block block, boolean hasMeta) {
		GameRegistry.register(block);
		GameRegistry.register(hasMeta ? new ItemBlockMeta(block) : new ItemBlock(block), block.getRegistryName());
		return block;
	}
	private static Block register(Block block) { return register(block, false); }
	
}
