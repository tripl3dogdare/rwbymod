package com.rwbymod.gui

import net.minecraft.client.gui.GuiScreen
import com.rwbymod.RWBYMod
import net.minecraft.util.ResourceLocation
import net.minecraft.client.renderer.GlStateManager

class GuiScroll extends GuiScreen {
  this.setGuiSize(128, 256)

  val scrollTexture = new ResourceLocation(RWBYMod.MODID, "textures/gui/scroll.png")

	override def drawScreen(mouseX:Int, mouseY:Int, partialTicks:Float) {
		this.drawDefaultBackground();
    super.drawScreen(mouseX, mouseY, partialTicks);

    this.mc.getTextureManager().bindTexture(scrollTexture);
    GlStateManager.color(1f, 1f, 1f);
    this.drawTexturedModalRect(this.width/2-64, this.height/2-128, 0, 0, 128, 256);
    this.drawCenteredString(this.fontRendererObj, "WIP", this.width/2, this.height/2, 0xffffff);
	}


}