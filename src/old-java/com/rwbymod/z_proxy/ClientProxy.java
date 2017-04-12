package com.rwbymod.z_proxy;

import com.rwbymod.client.RenderRegistry;

public class ClientProxy extends CommonProxy {

	@Override
	public void preInit() {
		super.preInit();
	}
	
	@Override
	public void init() {
		super.init();
		RenderRegistry.init();
	}
	
	@Override
	public void postInit() {
		super.postInit();
	}

}
