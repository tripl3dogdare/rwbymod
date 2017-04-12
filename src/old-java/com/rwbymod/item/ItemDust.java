package com.rwbymod.item;

import com.rwbymod.misc.EnumDustStimulus;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ItemDust extends ItemDustContainer {
	
	public ItemDust() {
		super("dust");
		this.setExplosionStimuli(EnumDustStimulus.item);
		this.setExplosionStrength(1f);
		this.setExplosionChance(50);
	}

	@Override
	protected void onExplosion(ItemStack item, Entity entity, EnumDustStimulus stimulus) {
		if(entity instanceof EntityPlayer) {
			if(!((EntityPlayer)entity).isCreative()) item.setCount(0); 
		}
	}

}
