package com.rwbymod;

import com.rwbymod.z_proxy.CommonProxy;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid=RWBYMod.MODID, name=RWBYMod.NAME, version=RWBYMod.VERSION, dependencies=RWBYMod.DEPS)
public class RWBYMod {
	public static final String MODID = "rwbymod";
	public static final String NAME = "The Minecraft RWBY Mod";
	public static final String VERSION = "A-0.1";
	public static final String DEPS = "";
	public static final String CPROXY = "com.rwbymod.z_proxy.ClientProxy";
	public static final String SPROXY = "com.rwbymod.z_proxy.CommonProxy";
	
	@Instance(MODID) public static RWBYMod instance;
	@SidedProxy(clientSide=CPROXY, serverSide=SPROXY) public static CommonProxy proxy;

	@EventHandler public void preInit(FMLPreInitializationEvent e) { proxy.preInit(); }
	@EventHandler public void init(FMLInitializationEvent e) { proxy.init(); }
	@EventHandler public void postInit(FMLPostInitializationEvent e) { proxy.postInit(); }

}
