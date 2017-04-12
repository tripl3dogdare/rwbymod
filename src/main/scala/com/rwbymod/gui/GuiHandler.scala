package com.rwbymod.gui

import net.minecraftforge.fml.common.network.IGuiHandler
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.world.World

object GuiHandler {
  val guiScroll = 0
}

class GuiHandler extends IGuiHandler {
  import GuiHandler._

  def getServerGuiElement(id:Int, player:EntityPlayer, world:World, x:Int, y:Int, z:Int) = id match {
    case _ => null
  }

  def getClientGuiElement(id:Int, player:EntityPlayer, world:World, x:Int, y:Int, z:Int) = id match {
    case i if i == guiScroll => new GuiScroll()
    case _ => null
  }

}