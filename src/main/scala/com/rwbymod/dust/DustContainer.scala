package com.rwbymod.dust

import java.util.EnumSet

import net.minecraft.world.World

trait DustContainer {
  type ExplosionContext
  protected def getPosFromContext(context:ExplosionContext):(Double,Double,Double)
  protected def getWorldFromContext(context:ExplosionContext):World
  protected def getStimulusFromContext(context:ExplosionContext):EnumDustStimulus

  protected val dustTypes:EnumSet[EnumDustType] = EnumDustType.all
  protected val explosionStimuli:EnumSet[EnumDustStimulus] = EnumSet.noneOf(classOf[EnumDustStimulus])
  protected val explosionStrength:Float = 1f
  protected val explosionChance:Double = 50

  def getDustTypes = dustTypes

  def getExplosionStimuli = explosionStimuli
  def getExplosionStrength = explosionStrength
  def getExplosionChance = explosionChance

  def getExplosionStimuli(context:ExplosionContext):EnumSet[EnumDustStimulus] = getExplosionStimuli
  def getExplosionStrength(context:ExplosionContext):Float = getExplosionStrength
  def getExplosionChance(context:ExplosionContext):Double = getExplosionChance

  protected def onExplosion(context:ExplosionContext) {}

  protected def createExplosion(context:ExplosionContext):Boolean = {
    val stimuli = this.getExplosionStimuli(context);
    val strength = this.getExplosionStrength(context);
    val chance = this.getExplosionChance(context);

    val pos = this.getPosFromContext(context)
    val world = this.getWorldFromContext(context)
    val stimulus = this.getStimulusFromContext(context)

    if(!world.isRemote && stimuli.contains(stimulus) && Math.random()*100 < chance) {
      world.createExplosion(null, pos._1, pos._1, pos._1, strength * stimulus.getExplosionModifier, true);
      this.onExplosion(context);
      return true;
    }
    return false
  }

  def getDustTypeFromMeta(meta:Int) = this.getDustTypes.toArray(Array[EnumDustType]()).lift(meta).getOrElse(EnumDustType.WIND)
}
