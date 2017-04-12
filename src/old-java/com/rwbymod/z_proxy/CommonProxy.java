package com.rwbymod.z_proxy;

import com.rwbymod.RWBYMod;
import com.rwbymod.block.BlockRegistry;
import com.rwbymod.gui.GuiHandler;
import com.rwbymod.item.ItemRegistry;
import com.rwbymod.misc.CraftingRegistry;
import com.rwbymod.misc.MiscRegistry;

import net.minecraftforge.fml.common.network.NetworkRegistry;

public class CommonProxy {

	public void preInit() {
		MiscRegistry.init();
		ItemRegistry.init();
		BlockRegistry.init();
		CraftingRegistry.init();
		NetworkRegistry.INSTANCE.registerGuiHandler(RWBYMod.instance, new GuiHandler());
	}
	
	public void init() {}
	public void postInit() {}

}
