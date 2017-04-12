package com.rwbymod.block;

import com.rwbymod.RWBYMod;
import com.rwbymod.misc.MiscRegistry;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public abstract class BlockBase extends Block {
	
	public BlockBase(Material mat) { this(mat, null); }
	public BlockBase(Material mat, String name) {
		super(mat);
		this.setRegistryName(RWBYMod.MODID, name);
		this.setUnlocalizedName(RWBYMod.MODID+"."+name);
		this.setCreativeTab(MiscRegistry.tabMain);
	}
	
}
