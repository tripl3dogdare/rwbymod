package com.rwbymod.item

import com.rwbymod.RWBYMod
import com.rwbymod.gui.GuiHandler

import net.minecraft.entity.Entity
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.ActionResult
import net.minecraft.util.EnumActionResult
import net.minecraft.util.EnumHand
import net.minecraft.world.World
import net.minecraftforge.fml.relauncher.SideOnly
import net.minecraftforge.fml.relauncher.Side
import net.minecraft.client.resources.I18n

class ItemScroll extends ItemBase("scroll") {

	override def onItemRightClick(world:World, player:EntityPlayer, hand:EnumHand):ActionResult[ItemStack] = {
  	if(world.isRemote) player.openGui(RWBYMod.MODID, GuiHandler.guiScroll, world, 0, 0, 0)
    return new ActionResult(EnumActionResult.PASS, player.getHeldItem(hand))
  }

	override def onUpdate(stack:ItemStack, world:World, entity:Entity, slot:Int, isSelected:Boolean) {
  	if(!stack.hasTagCompound) {
  		val nbt = new NBTTagCompound
  		nbt.setUniqueId("owner", entity.getUniqueID)
  		nbt.setString("ownerName", entity.getName)
  		stack.setTagCompound(nbt)
  	}
  }

	@SideOnly(Side.CLIENT)
  override def addInformation(stack:ItemStack, player:EntityPlayer, tooltip:java.util.List[String], advanced:Boolean) {
  	if(stack.hasTagCompound) {
  		tooltip.add(I18n.format("rwbymod.tooltip.owner", stack.getTagCompound.getString("ownerName")))
  	} else {
  		tooltip.add(I18n.format("rwbymod.tooltip.bindonpickup"))
  	}
  }

}