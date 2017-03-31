package com.rwbymod.gui;

import com.rwbymod.RWBYMod;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class GuiScroll extends GuiScreen {
	
	private static final ResourceLocation scrollTexture = new ResourceLocation(RWBYMod.MODID, "textures/gui/scroll.png");
	
	public GuiScroll() {
		this.setGuiSize(128, 256);
	}
	
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();
	    super.drawScreen(mouseX, mouseY, partialTicks);
	    
	    this.mc.getTextureManager().bindTexture(scrollTexture);
	    GlStateManager.color(1f, 1f, 1f);
	    this.drawTexturedModalRect(this.width/2-64, this.height/2-128, 0, 0, 128, 256);
	    this.drawCenteredString(this.fontRendererObj, "WIP", this.width/2, this.height/2, 0xffffff);
	}

}
