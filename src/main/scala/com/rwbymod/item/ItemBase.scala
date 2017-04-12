package com.rwbymod.item

import net.minecraft.item.Item
import com.rwbymod.registry.MiscRegistry
import com.rwbymod.RWBYMod

class ItemBase(name:String) extends Item {
	this.setRegistryName(RWBYMod.MODID, name);
	this.setUnlocalizedName(RWBYMod.MODID+"."+name);
	this.setCreativeTab(MiscRegistry.tabMain);
}