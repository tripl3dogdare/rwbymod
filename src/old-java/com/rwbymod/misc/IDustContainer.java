package com.rwbymod.misc;

import java.util.EnumSet;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IDustContainer {

	public default EnumDustType getDustTypeFromMeta(int meta) { return getDustTypeFromMeta(meta, EnumDustType.all); }
	public default EnumDustType getDustTypeFromMeta(int meta, EnumSet<EnumDustType> set) { return set.toArray(new EnumDustType[0])[meta]; }
	
	public default boolean shouldExplode(double percentChance) { return Math.random()*100 < percentChance; }

}
