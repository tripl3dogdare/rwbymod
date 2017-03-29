package com.rwbymod.client;

import com.rwbymod.misc.EnumDustType;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class ColorizerDust implements IItemColor, IBlockColor {
	private int tintIndex;
	
	public ColorizerDust() { this(0); }
	public ColorizerDust(int tintIndex) { this.tintIndex = tintIndex; }
	
	public int getColorFromItemstack(ItemStack stack, int tintIndex) {
		if(tintIndex != this.tintIndex || stack.getItemDamage() >= EnumDustType.values().length) return 0xFFFFFF;
        return EnumDustType.values()[stack.getItemDamage()].color;
	}
	
	public int colorMultiplier(IBlockState state, IBlockAccess worldIn, BlockPos pos, int tintIndex) {
		if(tintIndex != this.tintIndex || state.getBlock().getMetaFromState(state) >= EnumDustType.values().length) return 0x000000;
        return EnumDustType.values()[state.getBlock().getMetaFromState(state)].color;
	}
}
