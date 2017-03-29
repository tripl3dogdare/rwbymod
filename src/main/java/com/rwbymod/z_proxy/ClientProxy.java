package com.rwbymod.z_proxy;

import com.rwbymod.block.BlockRegistry;
import com.rwbymod.client.RenderRegistry;
import com.rwbymod.item.ItemRegistry;

public class ClientProxy extends CommonProxy {

	public void preInit() {
		super.preInit();
	}
	
	public void init() {
		super.init();
		RenderRegistry.init();
	}
	
	public void postInit() {
		super.postInit();
	}

}
