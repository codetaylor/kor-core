package com.sudoplay.mc.kor.spi.world;

import com.sudoplay.mc.kor.core.IntMap;
import com.sudoplay.mc.kor.spi.registry.provider.KorInitStrategyProvider;
import com.sudoplay.mc.kor.spi.registry.strategy.KorInitStrategy;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by codetaylor on 11/4/2016.
 */
public class KorOreGen
    implements IWorldGenerator,
    KorInitStrategyProvider {

  private IntMap<List<OreGenStrategy>> worldGenMap;

  public KorOreGen() {

    this.worldGenMap = new IntMap<>();
  }

  public void addOreGenStrategy(
      int dimensionId,
      OreGenStrategy oreGenStrategy
  ) {

    List<OreGenStrategy> worldGenMinableList = this.worldGenMap.get(dimensionId);

    if (worldGenMinableList == null) {
      worldGenMinableList = new ArrayList<>();
      this.worldGenMap.put(dimensionId, worldGenMinableList);
    }

    worldGenMinableList.add(oreGenStrategy);
  }

  @Override
  public KorInitStrategy getInitStrategy() {

    return mod -> GameRegistry.registerWorldGenerator(this, 0);
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

    List<OreGenStrategy> worldGenStrategies = this.worldGenMap.get(dimensionId);

    if (worldGenStrategies != null) {

      for (OreGenStrategy strategy : worldGenStrategies) {

        KorWorldGenMinable worldGenMinable = strategy.worldGenMinable;

        int min = strategy.spawnsPerChunk.getMin();
        int max = strategy.spawnsPerChunk.getMax();
        int spawnsPerChunk = random.nextInt(max - min + 1) + min;

        min = strategy.verticalGeneration.getMin();
        max = strategy.verticalGeneration.getMax();
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
