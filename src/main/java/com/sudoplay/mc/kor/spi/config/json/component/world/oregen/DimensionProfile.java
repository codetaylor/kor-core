package com.sudoplay.mc.kor.spi.config.json.component.world.oregen;

import com.sudoplay.mc.kor.spi.config.json.component.common.MinMaxInt;

/**
 * Created by sk3lls on 11/4/2016.
 */
public class DimensionProfile {

  private int dimensionId;
  private int blockCount;
  private String matchBlock;
  private MinMaxInt spawnsPerChunk;
  private MinMaxInt verticalGeneration;

  public DimensionProfile(
      int dimensionId,
      int blockCount,
      String matchBlock,
      MinMaxInt spawnsPerChunk,
      MinMaxInt verticalGeneration
  ) {
    this.dimensionId = dimensionId;
    this.blockCount = blockCount;
    this.matchBlock = matchBlock;
    this.spawnsPerChunk = spawnsPerChunk;
    this.verticalGeneration = verticalGeneration;
  }

  public int getDimensionId() {
    return dimensionId;
  }

  public int getBlockCount() {
    return blockCount;
  }

  public String getMatchBlock() {
    return matchBlock;
  }

  public MinMaxInt getSpawnsPerChunk() {
    return spawnsPerChunk;
  }

  public MinMaxInt getVerticalGeneration() {
    return verticalGeneration;
  }
}
