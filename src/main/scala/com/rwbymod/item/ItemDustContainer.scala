package com.rwbymod.item

import net.minecraft.util.EnumHand
import net.minecraft.util.ActionResult
import net.minecraft.entity.EntityLivingBase
import net.minecraft.util.math.BlockPos
import net.minecraft.util.EnumActionResult
import net.minecraft.util.NonNullList
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.creativetab.CreativeTabs
import java.util.EnumSet
import net.minecraft.entity.item.EntityItem
import net.minecraft.item.ItemStack
import net.minecraft.block.state.IBlockState
import net.minecraftforge.fml.relauncher.SideOnly
import net.minecraft.client.resources.I18n
import net.minecraft.entity.Entity
import net.minecraft.item.Item
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.Side
import com.rwbymod.dust.EnumDustStimulus
import com.rwbymod.dust.EnumDustType
import com.rwbymod.dust.DustContainer

abstract class ItemDustContainer(
  name:String,
  override val dustTypes:EnumSet[EnumDustType] = EnumDustType.all,
  override val explosionStimuli:EnumSet[EnumDustStimulus] = EnumSet.noneOf(classOf[EnumDustStimulus]),
  override val explosionStrength:Float = 1f,
  override val explosionChance:Double = 50
) extends ItemBase(name) with DustContainer {
  this.setHasSubtypes(true)

  type ExplosionContext = (ItemStack, Entity, EnumDustStimulus)
  def getPosFromContext(context:ExplosionContext) = (context._2.posX, context._2.posY, context._2.posZ)
  def getWorldFromContext(context:ExplosionContext) = context._2.world
  def getStimulusFromContext(context:ExplosionContext) = context._3

  // --- EXPLOSION STIMULI --- //

	override def onItemRightClick(world:World, player:EntityPlayer, hand:EnumHand):ActionResult[ItemStack] = {
  	this.createExplosion(player.getHeldItem(hand), player, EnumDustStimulus.USEITEM)
    return new ActionResult(EnumActionResult.PASS, player.getHeldItem(hand))
  }

	override def hitEntity(stack:ItemStack, target:EntityLivingBase, attacker:EntityLivingBase):Boolean = {
  	this.createExplosion(stack, attacker, EnumDustStimulus.ATTACKWITHITEM)
    return false
  }

	override def onDroppedByPlayer(item:ItemStack, player:EntityPlayer):Boolean = {
  	return !this.createExplosion(item, player, EnumDustStimulus.DROPITEM)
  }

	override def onBlockDestroyed(stack:ItemStack, world:World, state:IBlockState, pos:BlockPos, entity:EntityLivingBase):Boolean = {
  	this.createExplosion(stack, entity, EnumDustStimulus.MINEWITHITEM)
    return false
  }

	override def onEntityItemUpdate(entityItem:EntityItem):Boolean = {
  	if(entityItem.isBurning) this.createExplosion(entityItem.getEntityItem, entityItem, EnumDustStimulus.BURNITEM)
    return false
  }

  // --- CLIENT --- //

	@SideOnly(Side.CLIENT)
  override def getSubItems(item:Item, tab:CreativeTabs, subItems:NonNullList[ItemStack]) {
  	for(i <- 0 to this.dustTypes.size) subItems.add(new ItemStack(item, 1, i))
  }

	@SideOnly(Side.CLIENT)
  override def addInformation(stack:ItemStack, player:EntityPlayer, tooltip:java.util.List[String], advanced:Boolean) {
  	tooltip.add(I18n.format("rwbymod.tooltip.dusttype", getDustTypeFromMeta(stack.getMetadata).getLocalizedName))
  }
}