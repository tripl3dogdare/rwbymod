package com.rwbymod.world;

import java.util.Random;

import com.rwbymod.block.BlockDustContainer;
import com.rwbymod.block.BlockRegistry;
import com.rwbymod.misc.EnumDustType;

import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

public class WorldGenDustOre implements IWorldGenerator {
	
	private static final int veinsPerChunk = 6;
	private static final int blocksPerVein = 14;
	private static final int minY = 6;
	private static final int maxY = 34;
	private static final int maxX = 10;
	private static final int maxZ = 10;

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		if(world.provider.getDimension() != DimensionType.OVERWORLD.getId()) return;
		
		for(int n = 0; n < veinsPerChunk; n++) {
			IBlockState block = BlockRegistry.dustOre.getDefaultState().withProperty(BlockDustContainer.dustType, EnumDustType.random(EnumDustType.primary));
			int posX = chunkX*16 + random.nextInt(maxX);
			int posY = minY + random.nextInt(maxY - minY);
			int posZ = chunkZ*16 + random.nextInt(maxZ);
			(new WorldGenMinable(block, blocksPerVein, BlockMatcher.forBlock(Blocks.STONE))).generate(world, random, new BlockPos(posX, posY, posZ));
		}
	}
	
}
