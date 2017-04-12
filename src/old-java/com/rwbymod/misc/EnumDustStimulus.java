package com.rwbymod.misc;

import java.util.EnumSet;

public enum EnumDustStimulus {
	
	USEITEM(Level.WEAK),
	ATTACKWITHITEM(Level.STRONG),
	DROPITEM(Level.MEDIUM),
	MINEWITHITEM(Level.STRONG),
	BURNITEM(Level.STRONG),
	MINEBLOCK(Level.STRONG),
	LANDONBLOCK(Level.MEDIUM),
	EXPLODEBLOCK(Level.STRONG),
	BURNBLOCK(Level.MEDIUM),
	POWERBLOCK(Level.MEDIUM);
	
	public final Level level;
	
	EnumDustStimulus(Level level) {
		this.level = level;
	}
	
	public float getExplosionModifier() {
		switch(this.level) {
		case WEAK: return .5f;
		case MEDIUM: return 1f;
		case STRONG: return 1.5f;
		default: return 1f;
		}
	}
	
	public static final EnumSet<EnumDustStimulus> all = EnumSet.allOf(EnumDustStimulus.class);
	public static final EnumSet<EnumDustStimulus> item = EnumSet.range(USEITEM, BURNITEM);
	public static final EnumSet<EnumDustStimulus> block = EnumSet.range(MINEBLOCK, POWERBLOCK);
	
	public static enum Level { WEAK, MEDIUM, STRONG }
}
