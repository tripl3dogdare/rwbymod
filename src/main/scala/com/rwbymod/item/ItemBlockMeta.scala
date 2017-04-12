package com.rwbymod.item

import net.minecraft.item.ItemBlock
import net.minecraft.block.Block

class ItemBlockMeta(block:Block) extends ItemBlock(block) {
  this.setHasSubtypes(true)
  override def getMetadata(damage:Int) = damage
}