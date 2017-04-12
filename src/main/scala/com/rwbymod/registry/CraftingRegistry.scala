package com.rwbymod.registry

import net.minecraftforge.fml.common.registry.GameRegistry
import net.minecraft.item.ItemStack
import scala.collection.JavaConversions._
import net.minecraft.init.Items
import net.minecraftforge.oredict.OreDictionary
import net.minecraftforge.oredict.ShapedOreRecipe
import com.rwbymod.dust.EnumDustType

object CraftingRegistry {

  def init {
    Array[(EnumDustType, EnumDustType, EnumDustType)](
      (EnumDustType.LIGHTNING, EnumDustType.WIND, EnumDustType.FIRE),
      (EnumDustType.ICE, EnumDustType.WIND, EnumDustType.WATER),
      (EnumDustType.STEAM, EnumDustType.FIRE, EnumDustType.WATER),
      (EnumDustType.GRAVITY, EnumDustType.EARTH, EnumDustType.WIND)
    ).foreach { ((out:EnumDustType, in1:EnumDustType, in2:EnumDustType) => {
      for(i <- 1 to 4) GameRegistry.addShapelessRecipe(
        new ItemStack(ItemRegistry.dust, i*2, out.ordinal),
        List.fill(i){ new ItemStack(ItemRegistry.dust, 1, in1.ordinal) } ++
        List.fill(i){ new ItemStack(ItemRegistry.dust, 1, in2.ordinal) }:_*
      )
    }).tupled }

    for(t <- EnumDustType.primary)
      GameRegistry.addSmelting(BlockRegistry.dustOre, new ItemStack(ItemRegistry.dust, 4, t.ordinal), .7f)

    for(t <- EnumDustType.all)
			GameRegistry.addShapedRecipe(
				new ItemStack(ItemRegistry.dustCrystal, 1, t.ordinal),
				" d ",
				"dqd",
				" d ",
				'd'.asInstanceOf[Character], new ItemStack(ItemRegistry.dust, 1, t.ordinal),
				'q'.asInstanceOf[Character], Items.QUARTZ
			)

		GameRegistry.addRecipe(new ShapedOreRecipe(
			new ItemStack(ItemRegistry.scroll),
			"ini",
			"ngn",
			"ici",
			new Character('i'), "ingotIron",
			'n'.asInstanceOf[Character], "nuggetIron",
			'g'.asInstanceOf[Character], "paneGlassColorless",
			'c'.asInstanceOf[Character], new ItemStack(ItemRegistry.dustCrystal, 1, OreDictionary.WILDCARD_VALUE)
		));
  }

}