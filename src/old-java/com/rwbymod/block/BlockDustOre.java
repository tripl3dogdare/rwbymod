package com.rwbymod.block;

import java.util.Random;

import com.rwbymod.item.ItemRegistry;
import com.rwbymod.misc.EnumDustStimulus;
import com.rwbymod.misc.EnumDustType;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockDustOre extends BlockDustContainer {

	public BlockDustOre() {
		super(Material.ROCK, "dust_ore", EnumDustType.primary);
		this.setHardness(3.0F);
		this.setResistance(5.0F);
		this.setSoundType(SoundType.STONE);
		this.setHarvestLevel("pickaxe", 2);
		this.lightValue = 3;
		this.setExplosionStimuli(EnumDustStimulus.block);
		this.setExplosionStrength(1.5f);
		this.setExplosionChance(50);
	}

    @Override
	@SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT;
    }
    
    @Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) { return ItemRegistry.dust; }
    @Override
	public int damageDropped(IBlockState state) { return this.getMetaFromState(state); }
    @Override
	public int quantityDroppedWithBonus(int fortune, Random random) { return random.nextInt(4)+1+(fortune > 0 ? random.nextInt(fortune+1)+fortune : 0); }

    @Override
	public void onBlockHarvested(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
    	if(EnchantmentHelper.getEnchantmentLevel(Enchantments.SILK_TOUCH, player.getHeldItem(EnumHand.MAIN_HAND)) > 0) return;
    	double oldExplosionChance = this.getExplosionChance(world, pos, state, EnumDustStimulus.MINEBLOCK);
    	this.setExplosionChance(oldExplosionChance - (EnchantmentHelper.getEnchantmentLevel(Enchantments.FORTUNE, player.getHeldItem(EnumHand.MAIN_HAND))*20));
    	
    	this.createExplosion(world, pos, state, EnumDustStimulus.MINEBLOCK);
    	
    	this.setExplosionChance(oldExplosionChance);
    }

}
