package com.rwbymod.block

import net.minecraft.block.SoundType
import net.minecraftforge.fml.relauncher.SideOnly
import net.minecraft.block.material.Material
import net.minecraftforge.fml.relauncher.Side
import net.minecraft.util.BlockRenderLayer
import net.minecraft.util.EnumHand
import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init.Enchantments
import net.minecraft.block.state.IBlockState
import java.util.Random

import net.minecraft.item.Item
import com.rwbymod.registry.ItemRegistry
import com.rwbymod.dust.{EnumDustStimulus, EnumDustType}

class BlockDustOre extends BlockDustContainer(Material.ROCK, "dust_ore", EnumDustType.primary, EnumDustStimulus.block, 1.5f) {
	this.setHardness(3.0F)
	this.setResistance(5.0F)
	this.setSoundType(SoundType.STONE)
	this.setHarvestLevel("pickaxe", 2)
	this.lightValue = 3

	@SideOnly(Side.CLIENT)
  override def getBlockLayer:BlockRenderLayer = BlockRenderLayer.CUTOUT

	override def getItemDropped(state:IBlockState, rand:Random, fortune:Int):Item = ItemRegistry.dust
	override def damageDropped(state:IBlockState):Int = this.getMetaFromState(state)
	override def quantityDroppedWithBonus(fortune:Int, random:Random):Int = random.nextInt(4)+1+(if(fortune > 0) random.nextInt(fortune+1)+fortune else 0)

  override def getExplosionChance(context:ExplosionContext):Double = {
	  if(context._4 != EnumDustStimulus.MINEBLOCK || !context._5.isInstanceOf[EntityPlayer]) return super.getExplosionChance
	  val player = context._5.asInstanceOf[EntityPlayer]

  	if(EnchantmentHelper.getEnchantmentLevel(Enchantments.SILK_TOUCH, player.getHeldItem(EnumHand.MAIN_HAND)) > 0) return 0
  	return super.getExplosionChance - (EnchantmentHelper.getEnchantmentLevel(Enchantments.FORTUNE, player.getHeldItem(EnumHand.MAIN_HAND))*20)
	}

}