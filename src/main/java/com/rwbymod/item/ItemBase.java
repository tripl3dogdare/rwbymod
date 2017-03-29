package com.rwbymod.item;

import com.rwbymod.RWBYMod;
import com.rwbymod.misc.MiscRegistry;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class ItemBase extends Item {
	
	public ItemBase() { this(null); }
	public ItemBase(String name) {
		super();
		this.setRegistryName(RWBYMod.MODID, name);
		this.setUnlocalizedName(RWBYMod.MODID+"."+name);
		this.setCreativeTab(MiscRegistry.tabMain);
	}

}
