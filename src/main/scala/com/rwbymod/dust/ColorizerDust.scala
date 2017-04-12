package com.rwbymod.dust

import net.minecraft.client.renderer.color.IItemColor
import net.minecraft.client.renderer.color.IBlockColor
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraft.block.state.IBlockState

class ColorizerDust(private val tintIndex:Int=0) extends IItemColor with IBlockColor {

  def getColor(tintIndex:Int, meta:Int):Int = tintIndex match {
    case this.tintIndex => EnumDustType.values().lift(meta) match {
      case Some(i) => i.color
      case _ => 0xFFFFFF
    }
    case _ => 0xFFFFFF
  }

	def getColorFromItemstack(stack:ItemStack, ti:Int):Int = getColor(ti, stack.getItemDamage)
	def colorMultiplier(state:IBlockState, world:IBlockAccess, pos:BlockPos, ti:Int):Int = getColor(ti, state.getBlock.getMetaFromState(state))

}