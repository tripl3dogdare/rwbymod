package com.rwbymod.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public class ItemBlockMeta extends ItemBlock {

	public ItemBlockMeta(Block block) { 
		super(block);
		this.setHasSubtypes(true);
	}
	
	public int getMetadata(int damage) { return damage; }

}
