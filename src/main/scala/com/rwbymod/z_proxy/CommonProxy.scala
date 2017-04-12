package com.rwbymod.z_proxy

import net.minecraftforge.fml.common.network.NetworkRegistry
import com.rwbymod.RWBYMod
import com.rwbymod.registry.MiscRegistry
import com.rwbymod.registry.ItemRegistry
import com.rwbymod.registry.BlockRegistry
import com.rwbymod.registry.CraftingRegistry
import com.rwbymod.gui.GuiHandler

class CommonProxy {

  def preInit {
    MiscRegistry.init
    ItemRegistry.init
    BlockRegistry.init
    CraftingRegistry.init
  }

  def init {
    NetworkRegistry.INSTANCE.registerGuiHandler(RWBYMod.instance, new GuiHandler())
  }

  def postInit {}

}