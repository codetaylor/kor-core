package com.sudoplay.mc.kor.spi.world;

import com.google.common.base.Predicate;
import com.sudoplay.mc.kor.spi.config.json.component.common.MinMaxInt;
import net.minecraft.block.state.IBlockState;

/**
 * Created by codetaylor on 11/19/2016.
 */
public class OreGenStrategy {

  final KorWorldGenMinable worldGenMinable;
  final MinMaxInt spawnsPerChunk;
  final MinMaxInt verticalGeneration;

  private OreGenStrategy(
      KorWorldGenMinable worldGenMinable,
      MinMaxInt spawnsPerChunk,
      MinMaxInt verticalGeneration
  ) {
    this.worldGenMinable = worldGenMinable;
    this.spawnsPerChunk = spawnsPerChunk;
    this.verticalGeneration = verticalGeneration;
  }

  public static OreGenStrategy create(
      int blockCount,
      MinMaxInt spawnsPerChunk,
      MinMaxInt verticalGeneration,
      @SuppressWarnings("Guava") Predicate<IBlockState> blockMatcher,
      KorOreGenReplacementStrategy replacementStrategy
  ) {
    return new OreGenStrategy(
        new KorWorldGenMinable(
            blockCount,
            replacementStrategy,
            blockMatcher
        ),
        spawnsPerChunk,
        verticalGeneration
    );
  }

}
