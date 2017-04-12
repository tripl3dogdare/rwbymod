package com.rwbymod.registry

import net.minecraft.block.Block
import net.minecraftforge.fml.common.registry.GameRegistry
import net.minecraft.item.ItemBlock
import com.rwbymod.block.BlockDustOre
import com.rwbymod.item.ItemBlockMeta

object BlockRegistry {

  val dustOre = new BlockDustOre()

  def init {
    //Non-meta blocks
    Array[Block](
    ).foreach { block => {
      GameRegistry.register(block)
      GameRegistry.register(new ItemBlock(block), block.getRegistryName)
    }}

    //Meta blocks
    Array[Block](
      dustOre
    ).foreach { block => {
      GameRegistry.register(block)
      GameRegistry.register(new ItemBlockMeta(block), block.getRegistryName)
    }}
  }

}