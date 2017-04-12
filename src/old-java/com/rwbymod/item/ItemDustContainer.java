package com.rwbymod.item;

import java.util.EnumSet;
import java.util.List;

import com.rwbymod.misc.EnumDustStimulus;
import com.rwbymod.misc.EnumDustType;
import com.rwbymod.misc.IDustContainer;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class ItemDustContainer extends ItemBase implements IDustContainer {
	
	protected EnumSet<EnumDustType> dustTypes;
	private EnumSet<EnumDustStimulus> explosionStimuli = EnumSet.noneOf(EnumDustStimulus.class);
	private float explosionStrength = 1f;
	private double explosionChance = 50;
	
	public ItemDustContainer(String name) { this(name, EnumDustType.all); }	
	public ItemDustContainer(String name, EnumSet<EnumDustType> subset) {
		super(name);
		this.dustTypes = subset;
		this.setHasSubtypes(true);
	}
	
	public EnumSet<EnumDustType> getDustTypes() { return this.dustTypes; }
	
	public ItemDustContainer setExplosionStimuli(EnumSet<EnumDustStimulus> set) { this.explosionStimuli = set; return this; }
	public ItemDustContainer setExplosionStrength(float strength) { this.explosionStrength = strength; return this; }
	public ItemDustContainer setExplosionChance(double chance) { this.explosionChance = chance; return this; }
	
	public EnumSet<EnumDustStimulus> getExplosionStimuli(ItemStack item, Entity entity, EnumDustStimulus stimulus) { return this.explosionStimuli; }
	public float getExplosionStrength(ItemStack item, Entity entity, EnumDustStimulus stimulus) { return this.explosionStrength; }
	public double getExplosionChance(ItemStack item, Entity entity, EnumDustStimulus stimulus) { return this.explosionChance; }
    
    protected boolean createExplosion(ItemStack item, Entity entity, EnumDustStimulus stimulus) {
    	EnumSet<EnumDustStimulus> stimuli = this.getExplosionStimuli(item, entity, stimulus);
    	float strength = this.getExplosionStrength(item, entity, stimulus);
    	double chance = this.getExplosionChance(item, entity, stimulus);
    	
    	if(!entity.world.isRemote && stimuli.contains(stimulus) && this.shouldExplode(chance)) {
    		entity.world.createExplosion(null, entity.posX, entity.posY, entity.posZ, strength * stimulus.getExplosionModifier(), true);
    		this.onExplosion(item, entity, stimulus);
    		return true;
    	}
    	return false;
    }

    protected void onExplosion(ItemStack item, Entity entity, EnumDustStimulus stimulus) {};
    
    // ----- EXPLOSION STIMULI ----- //

    @Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
    	this.createExplosion(player.getHeldItem(hand), player, EnumDustStimulus.USEITEM);
        return new ActionResult(EnumActionResult.PASS, player.getHeldItem(hand));
    }
    
    @Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
    	this.createExplosion(stack, attacker, EnumDustStimulus.ATTACKWITHITEM);
        return false;
    }

    @Override
	public boolean onDroppedByPlayer(ItemStack item, EntityPlayer player) {
    	return !this.createExplosion(item, player, EnumDustStimulus.DROPITEM);
    }
    
    @Override
	public boolean onBlockDestroyed(ItemStack stack, World world, IBlockState state, BlockPos pos, EntityLivingBase entity) {
    	this.createExplosion(stack, entity, EnumDustStimulus.MINEWITHITEM);
        return false;
    }
    
    @Override
	public boolean onEntityItemUpdate(EntityItem entityItem) {
    	if(entityItem.isBurning()) this.createExplosion(entityItem.getEntityItem(), entityItem, EnumDustStimulus.BURNITEM);
        return false;
    }
	
	// ----- CLIENT ----- //

    @Override
	@SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tab, NonNullList<ItemStack> subItems) {
    	for(int i = 0; i < this.dustTypes.size(); i++) subItems.add(new ItemStack(item, 1, i));
    }

    @Override
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced) {
    	tooltip.add(I18n.format("rwbymod.tooltip.dusttype", getDustTypeFromMeta(stack.getMetadata(), dustTypes).getLocalizedName()));
    }

}
