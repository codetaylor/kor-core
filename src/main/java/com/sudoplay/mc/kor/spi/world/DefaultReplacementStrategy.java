package com.sudoplay.mc.kor.spi.world;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.Random;

/**
 * Created by codetaylor on 11/21/2016.
 */
public class DefaultReplacementStrategy implements
    KorOreGenReplacementStrategy {

  private IBlockState defaultState;

  public DefaultReplacementStrategy(IBlockState defaultState) {
    this.defaultState = defaultState;
  }

  @Override
  public void execute(@Nonnull World worldIn, @Nonnull BlockPos blockpos, @Nonnull Random random) {
    worldIn.setBlockState(blockpos, this.defaultState, 2);
  }
}
