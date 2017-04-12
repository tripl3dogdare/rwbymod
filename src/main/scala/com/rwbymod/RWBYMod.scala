package com.rwbymod

import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.SidedProxy
import net.minecraftforge.fml.common.Mod.Instance
import net.minecraftforge.fml.common.Mod.EventHandler
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import com.rwbymod.z_proxy.CommonProxy

@Mod(modid = RWBYMod.MODID, name = RWBYMod.NAME, version = RWBYMod.VERSION, dependencies = RWBYMod.DEPS, modLanguage = "scala")
object RWBYMod {
	final val MODID = "rwbymod"
	final val NAME = "The Minecraft RWBY Mod"
	final val VERSION = "A-0.1"
	final val DEPS = ""
	final val CPROXY = "com.rwbymod.z_proxy.ClientProxy"
	final val SPROXY = "com.rwbymod.z_proxy.CommonProxy"

	@Instance(MODID) var instance = null
	@SidedProxy(clientSide=CPROXY, serverSide=SPROXY) var proxy:CommonProxy = null

	@EventHandler def preInit(e:FMLPreInitializationEvent) = proxy.preInit
	@EventHandler def init(e:FMLInitializationEvent) = proxy.init
	@EventHandler def postInit(e:FMLPostInitializationEvent) = proxy.postInit

}