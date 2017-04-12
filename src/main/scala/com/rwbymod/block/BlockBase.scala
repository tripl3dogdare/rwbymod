package com.rwbymod.block

import com.rwbymod.registry.MiscRegistry
import com.rwbymod.RWBYMod
import net.minecraft.block.material.Material
import net.minecraft.block.Block

class BlockBase(mat:Material, name:String) extends Block(mat) {
	this.setRegistryName(RWBYMod.MODID, name);
	this.setUnlocalizedName(RWBYMod.MODID+"."+name);
	this.setCreativeTab(MiscRegistry.tabMain);
}