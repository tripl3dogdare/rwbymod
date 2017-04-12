package com.rwbymod.item

import net.minecraft.entity.player.EntityPlayer
import com.rwbymod.dust.EnumDustStimulus

class ItemDust extends ItemDustContainer("dust", explosionStimuli = EnumDustStimulus.item) {

  override def onExplosion(context:ExplosionContext) =
    if(context._2.isInstanceOf[EntityPlayer] && !context._2.asInstanceOf[EntityPlayer].isCreative) context._1.setCount(0)

}