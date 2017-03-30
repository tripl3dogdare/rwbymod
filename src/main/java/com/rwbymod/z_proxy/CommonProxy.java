package com.rwbymod.z_proxy;

import com.rwbymod.block.BlockRegistry;
import com.rwbymod.item.ItemRegistry;
import com.rwbymod.misc.CraftingRegistry;
import com.rwbymod.misc.MiscRegistry;

public class CommonProxy {

	public void preInit() {
		MiscRegistry.init();
		ItemRegistry.init();
		BlockRegistry.init();
		CraftingRegistry.init();
	}
	
	public void init() {}
	public void postInit() {}

}
