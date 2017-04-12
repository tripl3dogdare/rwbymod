package com.rwbymod.item;

import java.util.List;

import com.rwbymod.RWBYMod;
import com.rwbymod.gui.GuiHandler;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemScroll extends ItemBase {
	
	public ItemScroll() {
		super("scroll");
	}

    @Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
    	if(world.isRemote) player.openGui(RWBYMod.MODID, GuiHandler.guiScroll, world, 0, 0, 0);
        return new ActionResult(EnumActionResult.PASS, player.getHeldItem(hand));
    }
    
    @Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int slot, boolean isSelected) {
    	if(!stack.hasTagCompound()) {
    		NBTTagCompound nbt = new NBTTagCompound();
    		nbt.setUniqueId("owner", entity.getUniqueID());
    		nbt.setString("ownerName", entity.getName());
    		stack.setTagCompound(nbt);
    	}
    }

    @Override
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced) {
    	if(stack.hasTagCompound()) {
    		tooltip.add(I18n.format("rwbymod.tooltip.owner", stack.getTagCompound().getString("ownerName")));
    	} else {
    		tooltip.add(I18n.format("rwbymod.tooltip.bindonpickup"));
    	}
    }

}
