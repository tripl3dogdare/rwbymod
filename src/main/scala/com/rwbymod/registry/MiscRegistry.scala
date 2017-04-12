package com.rwbymod.registry

import net.minecraft.creativetab.CreativeTabs
import com.rwbymod.RWBYMod
import net.minecraft.item.ItemStack
import net.minecraftforge.fml.common.registry.GameRegistry
import com.rwbymod.world.WorldGenDustOre

object MiscRegistry {

  val tabMain = new CreativeTabs(RWBYMod.MODID) {
			override def getTabIconItem = new ItemStack(ItemRegistry.dust)
  }

  def init {
		GameRegistry.registerWorldGenerator(new WorldGenDustOre(), 0)
  }

}