package com.sudoplay.mc.kor.spi.world;

import com.sudoplay.mc.kor.core.IntMap;
import com.sudoplay.mc.kor.spi.config.json.component.world.oregen.DimensionProfile;
import com.sudoplay.mc.kor.spi.config.json.component.world.oregen.KorOreGenConfig;
import com.sudoplay.mc.kor.spi.registry.provider.KorInitStrategyProvider;
import com.sudoplay.mc.kor.spi.registry.strategy.KorInitStrategy;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by sk3lls on 11/4/2016.
 */
public abstract class KorWorldGen implements
    IWorldGenerator,
    KorInitStrategyProvider {

  private int modGenerationWeight;
  private IntMap<List<DimensionProfileWorldGenPair>> worldGenMap;

  private class DimensionProfileWorldGenPair {
    WorldGenMinable worldGenMinable;
    DimensionProfile dimensionProfile;

    DimensionProfileWorldGenPair(WorldGenMinable worldGenMinable, DimensionProfile dimensionProfile) {
      this.worldGenMinable = worldGenMinable;
      this.dimensionProfile = dimensionProfile;
    }
  }

  protected KorWorldGen(KorOreGenConfig config, IBlockState defaultState) {
    this.modGenerationWeight = config.getModGenerationWeight();
    this.worldGenMap = new IntMap<>();

    for (DimensionProfile dimensionProfile : config.getDimensionProfileList()) {
      int dimensionId = dimensionProfile.getDimensionId();
      List<DimensionProfileWorldGenPair> worldGenMinableList = this.worldGenMap.get(dimensionId);

      if (worldGenMinableList == null) {
        worldGenMinableList = new ArrayList<>();
        this.worldGenMap.put(dimensionId, worldGenMinableList);
      }

      worldGenMinableList.add(new DimensionProfileWorldGenPair(
          new WorldGenMinable(
              defaultState,
              dimensionProfile.getBlockCount(),
              BlockMatcher.forBlock(Block.REGISTRY.getObject(new ResourceLocation(dimensionProfile.getMatchBlock())))
          ),
          dimensionProfile
      ));
    }
  }

  @Override
  public KorInitStrategy getInitStrategy() {
    return mod -> GameRegistry.registerWorldGenerator(this, this.modGenerationWeight);
  }

  @Override
  public void generate(
      Random random,
      int chunkX,
      int chunkZ,
      World world,
      IChunkGenerator chunkGenerator,
      IChunkProvider chunkProvider
  ) {
    BlockPos chunkPos = new BlockPos(chunkX * 16, 0, chunkZ * 16);
    int dimensionId = world.provider.getDimension();

    List<DimensionProfileWorldGenPair> dimensionProfileWorldGenPairs = this.worldGenMap.get(dimensionId);

    if (dimensionProfileWorldGenPairs != null) {

      for (DimensionProfileWorldGenPair pair : dimensionProfileWorldGenPairs) {

        DimensionProfile dimensionProfile = pair.dimensionProfile;
        WorldGenMinable worldGenMinable = pair.worldGenMinable;

        int min = dimensionProfile.getSpawnsPerChunk().getMin();
        int max = dimensionProfile.getSpawnsPerChunk().getMax();
        int spawnsPerChunk = random.nextInt(max - min + 1) + min;

        min = dimensionProfile.getVerticalGeneration().getMin();
        max = dimensionProfile.getVerticalGeneration().getMax();
        int range = max - min + 1;

        for (int i = 0; i < spawnsPerChunk; i++) {
          worldGenMinable.generate(
              world,
              random,
              chunkPos.add(
                  random.nextInt(16),
                  random.nextInt(range) + min,
                  random.nextInt(16)
              )
          );
        }
      }
    }
  }
}
