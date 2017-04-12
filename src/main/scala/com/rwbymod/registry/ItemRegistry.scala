package com.rwbymod.registry

import com.rwbymod.item.ItemDust
import com.rwbymod.item.ItemDustContainer
import com.rwbymod.item.ItemScroll

import net.minecraft.item.Item
import net.minecraftforge.fml.common.registry.GameRegistry

object ItemRegistry {

  val dust = new ItemDust()
  val dustCrystal = new ItemDustContainer("dust_crystal"){}
  val scroll = new ItemScroll()

  def init {
    Array[Item](
      dust,
      dustCrystal,
      scroll
    ).foreach { GameRegistry.register[Item] }
  }

}