package com.rwbymod.block;

import java.util.EnumSet;
import java.util.List;

import com.rwbymod.misc.EnumDustStimulus;
import com.rwbymod.misc.EnumDustType;
import com.rwbymod.misc.IDustContainer;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockDustContainer extends BlockBase implements IDustContainer {
	
	protected EnumSet<EnumDustType> dustTypes;
	public static PropertyEnum<EnumDustType> dustType = PropertyEnum.create("dusttype", EnumDustType.class);
	private EnumSet<EnumDustStimulus> explosionStimuli = EnumSet.noneOf(EnumDustStimulus.class);
	private float explosionStrength = 1f;
	private double explosionChance = 50;
	
	protected float minFallDistance = 4f;

	public BlockDustContainer(Material mat, String name) { this(mat, name, EnumDustType.all); }
	public BlockDustContainer(Material mat, String name, EnumSet<EnumDustType> subset) {
		super(mat, name);
		this.dustTypes = subset;
		this.setDefaultState(this.blockState.getBaseState().withProperty(dustType, subset.toArray(new EnumDustType[0])[0]));
	}
	
	public EnumSet<EnumDustType> getDustTypes() { return this.dustTypes; }
	
	public BlockDustContainer setExplosionStimuli(EnumSet<EnumDustStimulus> set) { this.explosionStimuli = set; return this; }
	public BlockDustContainer setExplosionStrength(float strength) { this.explosionStrength = strength; return this; }
	public BlockDustContainer setExplosionChance(double chance) { this.explosionChance = chance; return this; }
	
	public EnumSet<EnumDustStimulus> getExplosionStimuli(World world, BlockPos pos, IBlockState state, EnumDustStimulus stimulus) { return this.explosionStimuli; }
	public float getExplosionStrength(World world, BlockPos pos, IBlockState state, EnumDustStimulus stimulus) { return this.explosionStrength; }
	public double getExplosionChance(World world, BlockPos pos, IBlockState state, EnumDustStimulus stimulus) { return this.explosionChance; }
    
    protected boolean createExplosion(World world, BlockPos pos, IBlockState state, EnumDustStimulus stimulus) {
    	EnumSet<EnumDustStimulus> stimuli = this.getExplosionStimuli(world, pos, state, stimulus);
    	float strength = this.getExplosionStrength(world, pos, state, stimulus);
    	double chance = this.getExplosionChance(world, pos, state, stimulus);
    	
    	if(!world.isRemote && stimuli.contains(stimulus) && this.shouldExplode(chance)) {
    		world.createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), strength * stimulus.getExplosionModifier(), true);
    		this.onExplosion(world, pos, state, stimulus);
    		return true;
    	}
    	return false;
    }

    protected void onExplosion(World world, BlockPos pos, IBlockState state, EnumDustStimulus stimulus) {};
    
    // ----- EXPLOSION STIMULI ----- //

    @Override
	public void onBlockDestroyedByExplosion(World world, BlockPos pos, Explosion explosion) {
    	this.createExplosion(world, pos, world.getBlockState(pos), EnumDustStimulus.EXPLODEBLOCK);
    }

    @Override
	public void onFallenUpon(World world, BlockPos pos, Entity entity, float fallDistance) {
    	super.onFallenUpon(world, pos, entity, fallDistance);
    	if(fallDistance > this.minFallDistance) this.createExplosion(world, pos, world.getBlockState(pos), EnumDustStimulus.LANDONBLOCK);
    }

    @Override
	public void onBlockHarvested(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
    	this.createExplosion(world, pos, state, EnumDustStimulus.MINEBLOCK);
    }
    
    @Override
	public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block, BlockPos fromPos) {
    	BlockMatcher fire = BlockMatcher.forBlock(Blocks.FIRE);
    	BlockMatcher lava = BlockMatcher.forBlock(Blocks.LAVA);
    	
    	if(
			fire.apply(world.getBlockState( pos.down()  )) ||
    		fire.apply(world.getBlockState( pos.up()    )) ||
    		fire.apply(world.getBlockState( pos.north() )) ||
    		fire.apply(world.getBlockState( pos.south() )) ||
    		fire.apply(world.getBlockState( pos.east()  )) ||
    		fire.apply(world.getBlockState( pos.west()  )) ||
    		lava.apply(world.getBlockState( pos.down()  )) ||
    		lava.apply(world.getBlockState( pos.up()    )) ||
    		lava.apply(world.getBlockState( pos.north() )) ||
    		lava.apply(world.getBlockState( pos.south() )) ||
    		lava.apply(world.getBlockState( pos.east()  )) ||
    		lava.apply(world.getBlockState( pos.west()  ))
		) {
    		this.createExplosion(world, fromPos, state, EnumDustStimulus.BURNBLOCK);
    	} else if(world.isBlockPowered(pos)) this.createExplosion(world, pos, state, EnumDustStimulus.POWERBLOCK);
    }
    
    // ----- BLOCK STATES ----- //
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, dustType);
	}
	
    @Override
	public int getMetaFromState(IBlockState state) {
    	return state.getValue(dustType).ordinal();
    }
    
    @Override
	public IBlockState getStateFromMeta(int meta) {
    	return this.getDefaultState().withProperty(dustType, this.dustTypes.toArray(new EnumDustType[0])[meta < this.dustTypes.size() ? meta : 0]);
    }
    
    // ----- CLIENT ----- //
    
    @Override
	@SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, NonNullList<ItemStack> list) {
    	for(EnumDustType t : this.dustTypes) list.add(new ItemStack(this, 1, t.ordinal()));
    }

    @Override
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced) {
    	tooltip.add(I18n.format("rwbymod.tooltip.dusttype", getDustTypeFromMeta(stack.getMetadata()).getLocalizedName()));
    }
    
}
