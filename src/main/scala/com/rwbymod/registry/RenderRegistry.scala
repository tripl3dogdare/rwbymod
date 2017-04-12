package com.rwbymod.registry

import net.minecraft.block.Block
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.client.renderer.color.IBlockColor
import net.minecraft.client.renderer.color.IItemColor
import net.minecraft.item.Item
import com.rwbymod.dust.EnumDustType
import com.rwbymod.dust.ColorizerDust

object RenderRegistry {

  def init {
    register(ItemRegistry.dust, 0 to EnumDustType.all.size)
    register(ItemRegistry.dustCrystal, 0 to EnumDustType.all.size)
    register(ItemRegistry.scroll)
    register(BlockRegistry.dustOre, 0 to EnumDustType.primary.size)

    registerColorizer(ItemRegistry.dust, new ColorizerDust())
    registerColorizer(ItemRegistry.dustCrystal, new ColorizerDust())
    registerColorizer(BlockRegistry.dustOre, new ColorizerDust(1))
  }

  // --- UTILITY METHODS --- //

  def register(in:Item):Unit = register(in, 0)
  def register(in:Block):Unit = register(in, 0)
  def register(in:Item, meta:Int):Unit =
    Minecraft.getMinecraft.getRenderItem.getItemModelMesher.register(in, meta, new ModelResourceLocation(in.getRegistryName(), "inventory"))
  def register(in:Block, meta:Int):Unit =
    Minecraft.getMinecraft.getRenderItem.getItemModelMesher.register(Item.getItemFromBlock(in), meta, new ModelResourceLocation(in.getRegistryName(), "inventory"))
  def register(in:Item, meta:Range):Unit = meta.foreach { m => register(in, m) }
  def register(in:Block, meta:Range):Unit = meta.foreach { m => register(in, m) }

  def registerColorizer(item:Item, color:IItemColor) = Minecraft.getMinecraft.getItemColors.registerItemColorHandler(color, item)
  def registerColorizer(block:Block, color:(IBlockColor with IItemColor)):Unit = registerColorizer(block, color, color)
  def registerColorizer(block:Block, blockColor:IBlockColor, itemColor:IItemColor):Unit = {
    Minecraft.getMinecraft.getBlockColors.registerBlockColorHandler(blockColor, block)
    registerColorizer(Item.getItemFromBlock(block), itemColor)
  }

}