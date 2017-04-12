package com.rwbymod.world

import java.util.Random

import com.rwbymod.block.BlockDustContainer
import com.rwbymod.registry.BlockRegistry

import net.minecraft.block.state.pattern.BlockMatcher
import net.minecraft.init.Blocks
import net.minecraft.util.math.BlockPos
import net.minecraft.world.DimensionType
import net.minecraft.world.World
import net.minecraft.world.chunk.IChunkGenerator
import net.minecraft.world.chunk.IChunkProvider
import net.minecraft.world.gen.feature.WorldGenMinable
import net.minecraftforge.fml.common.IWorldGenerator
import com.rwbymod.dust.EnumDustType

class WorldGenDustOre extends IWorldGenerator {
	private val veinsPerChunk = 6
	private val blocksPerVein = 14
	private val minY = 6
	private val maxY = 34
	private val maxX = 10
	private val maxZ = 10

	def generate(random:Random, chunkX:Int, chunkZ:Int, world:World, chunkGenerator:IChunkGenerator, chunkProvider:IChunkProvider) {
		if(world.provider.getDimension != DimensionType.OVERWORLD.getId) return;

		for(n <- 0 to veinsPerChunk) {
			val block = BlockRegistry.dustOre.getDefaultState.withProperty(BlockDustContainer.dustType, EnumDustType.random(EnumDustType.primary));
			val posX = chunkX*16 + random.nextInt(maxX);
			val posY = minY + random.nextInt(maxY - minY);
			val posZ = chunkZ*16 + random.nextInt(maxZ);
			(new WorldGenMinable(block, blocksPerVein, BlockMatcher.forBlock(Blocks.STONE))).generate(world, random, new BlockPos(posX, posY, posZ));
		}
	}
}