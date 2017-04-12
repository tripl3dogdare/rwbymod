package com.rwbymod.z_proxy

import com.rwbymod.registry.RenderRegistry

class ClientProxy extends CommonProxy {

  override def preInit = { super.preInit }

  override def init = {
    super.init
    RenderRegistry.init
  }

  override def postInit = { super.postInit }

}