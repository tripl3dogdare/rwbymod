package com.rwbymod.misc;

import java.util.EnumSet;
import java.util.Random;

import net.minecraft.client.resources.I18n;
import net.minecraft.util.IStringSerializable;

public enum EnumDustType implements IStringSerializable {
	
	WIND(0x55F257),
	FIRE(0xF5270C),
	WATER(0x0C6DF5),
	EARTH(0x633E13),
	LIGHTNING(0xF6FF00),
	ICE(0xD9FFFB),
	STEAM(0xFFEBEB),
	GRAVITY(0x5A026B);

	public final int color;
	
	EnumDustType(int color) {
		this.color = color;
	}
	
	public String toString() { return this.name(); }
	public String getName() { return this.name().toLowerCase(); }
	public String getLocalizedName() { return I18n.format("rwbymod.dust."+this.getName()); }
	
	public static EnumDustType random() { return random(all); }
	public static EnumDustType random(EnumSet<EnumDustType> set) {
		return set.toArray(new EnumDustType[0])[new Random().nextInt(set.size())];
	}
	
	public static final EnumSet<EnumDustType> all = EnumSet.allOf(EnumDustType.class);
	public static final EnumSet<EnumDustType> primary = EnumSet.range(WIND, EARTH);
	public static final EnumSet<EnumDustType> secondary = EnumSet.range(LIGHTNING, GRAVITY);

}
