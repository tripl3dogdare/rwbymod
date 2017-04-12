package com.rwbymod.item;

import com.rwbymod.RWBYMod;
import com.rwbymod.misc.MiscRegistry;

import net.minecraft.item.Item;

public abstract class ItemBase extends Item {
	
	public ItemBase() { this(null); }
	public ItemBase(String name) {
		super();
		this.setRegistryName(RWBYMod.MODID, name);
		this.setUnlocalizedName(RWBYMod.MODID+"."+name);
		this.setCreativeTab(MiscRegistry.tabMain);
	}

}
