package com.rwbymod.client;

import com.rwbymod.block.BlockRegistry;
import com.rwbymod.item.ItemRegistry;
import com.rwbymod.misc.EnumDustType;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.Item;

public class RenderRegistry {
	
	public static void init() {
		registerSimpleMetaItemRenderer(ItemRegistry.dust, EnumDustType.values().length);
		registerItemColorHandler(new ColorizerDust(), ItemRegistry.dust);

		registerSimpleMetaBlockRenderer(BlockRegistry.dustOre, EnumDustType.primary.size());
		registerBlockColorHandler(new ColorizerDust(1), BlockRegistry.dustOre);
		registerItemColorHandler(new ColorizerDust(1), Item.getItemFromBlock(BlockRegistry.dustOre));
	}
	
	// ----- UTILITY FUNCTIONS ----- //
	
	private static void registerItemRenderer(Item item, int meta) {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, meta, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
	private static void registerItemRenderer(Item item) { registerItemRenderer(item, 0); }
	
	private static void registerSimpleMetaItemRenderer(Item item, int metaCount) {
		for(int i = 0; i < metaCount; i++) registerItemRenderer(item, i);
	}
	
	private static void registerItemColorHandler(IItemColor color, Item item) {
		Minecraft.getMinecraft().getItemColors().registerItemColorHandler(color, item);
	}
	
	private static void registerBlockRenderer(Block block, int meta) {
		Item item = Item.getItemFromBlock(block);
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, meta, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
	private static void registerBlockRenderer(Block block) { registerBlockRenderer(block, 0); }
	
	private static void registerSimpleMetaBlockRenderer(Block block, int metaCount) {
		for(int i = 0; i < metaCount; i++) registerBlockRenderer(block, i);
	}
	
	private static void registerBlockColorHandler(IBlockColor color, Block block) {
		Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler(color, block);
	}
}
