package com.rwbymod.block

import java.util.EnumSet

import com.rwbymod.dust.{EnumDustStimulus, EnumDustType}
import com.rwbymod.dust.DustContainer
import net.minecraft.block.Block
import net.minecraft.block.material.Material
import net.minecraft.block.properties.PropertyEnum
import net.minecraft.block.state.{BlockStateContainer, IBlockState}
import net.minecraft.block.state.pattern.BlockMatcher
import net.minecraft.client.resources.I18n
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.entity.Entity
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init.Blocks
import net.minecraft.item.{Item, ItemStack}
import net.minecraft.util.NonNullList
import net.minecraft.util.math.BlockPos
import net.minecraft.world.{Explosion, World}
import net.minecraftforge.fml.relauncher.{Side, SideOnly}

class BlockDustContainer(
  mat:Material,
  name:String,
  override val dustTypes:EnumSet[EnumDustType] = EnumDustType.all,
  override val explosionStimuli:EnumSet[EnumDustStimulus] = EnumSet.noneOf(classOf[EnumDustStimulus]),
  override val explosionStrength:Float = 1f,
  override val explosionChance:Double = 50,
	private val minFallDistance:Float = 4f
) extends BlockBase(mat, name) with DustContainer {
  import BlockDustContainer._
	this.setDefaultState(this.blockState.getBaseState.withProperty(dustType,
    dustTypes.toArray(Array[EnumDustType]()).headOption.getOrElse(EnumDustType.WIND)
  ));

  type ExplosionContext = (World, BlockPos, IBlockState, EnumDustStimulus, Entity)
  def getPosFromContext(context:ExplosionContext) = (context._2.getX, context._2.getY, context._2.getZ)
  def getWorldFromContext(context:ExplosionContext) = context._1
  def getStimulusFromContext(context:ExplosionContext) = context._4

  // ----- EXPLOSION STIMULI ----- //

	override def onBlockDestroyedByExplosion(world:World, pos:BlockPos, explosion:Explosion) {
  	this.createExplosion(world, pos, world.getBlockState(pos), EnumDustStimulus.EXPLODEBLOCK, null)
  }

	override def onFallenUpon(world:World, pos:BlockPos, entity:Entity, fallDistance:Float) {
  	super.onFallenUpon(world, pos, entity, fallDistance)
  	if(fallDistance >= minFallDistance) this.createExplosion(world, pos, world.getBlockState(pos), EnumDustStimulus.LANDONBLOCK, entity)
  }

	override def onBlockHarvested(world:World, pos:BlockPos, state:IBlockState, player:EntityPlayer) {
  	this.createExplosion(world, pos, state, EnumDustStimulus.MINEBLOCK, player)
  }

	override def neighborChanged(state:IBlockState, world:World, pos:BlockPos, block:Block, fromPos:BlockPos) {
  	if(
		  BlockMatcher.forBlock(Blocks.FIRE).apply(world.getBlockState(fromPos)) ||
		  BlockMatcher.forBlock(Blocks.LAVA).apply(world.getBlockState(fromPos))
	  ) {
  		this.createExplosion(world, fromPos, state, EnumDustStimulus.BURNBLOCK, null)
  	} else if(world.isBlockPowered(pos)) this.createExplosion(world, pos, state, EnumDustStimulus.POWERBLOCK, null);
  }

  // ----- BLOCK STATES ----- //

	override def createBlockState:BlockStateContainer = new BlockStateContainer(this, dustType)
	override def getMetaFromState(state:IBlockState):Int = state.getValue(dustType).ordinal
	override def getStateFromMeta(meta:Int):IBlockState = this.getDefaultState.withProperty(dustType, dustTypes.toArray(Array[EnumDustType]()).lift(meta).getOrElse(EnumDustType.WIND))

  // ----- CLIENT ----- //

	@SideOnly(Side.CLIENT)
  override def getSubBlocks(item:Item, tab:CreativeTabs, subItems:NonNullList[ItemStack]) {
  	for(i <- 0 to this.dustTypes.size) subItems.add(new ItemStack(item, 1, i))
  }

	@SideOnly(Side.CLIENT)
  override def addInformation(stack:ItemStack, player:EntityPlayer, tooltip:java.util.List[String], advanced:Boolean) {
  	tooltip.add(I18n.format("rwbymod.tooltip.dusttype", getDustTypeFromMeta(stack.getMetadata).getLocalizedName))
  }
}

object BlockDustContainer {
	val dustType:PropertyEnum[EnumDustType] = PropertyEnum.create("dusttype", classOf[EnumDustType])
}