package com.sudoplay.mc.kor.spi.world;

import com.google.common.base.Predicate;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;

import javax.annotation.Nullable;

/**
 * Created by sk3lls on 11/19/2016.
 */
public class KorBlockMatcher<T extends Comparable<T>> implements Predicate<IBlockState> {

  private Block block;
  private IProperty<T> property;
  private T value;

  public KorBlockMatcher(
      Block block,
      IProperty<T> property,
      T value
  ) {
    this.block = block;
    this.property = property;
    this.value = value;
  }

  @Override
  public boolean apply(@Nullable IBlockState input) {
    return input != null && input.getBlock() == this.block && input.getValue(this.property) == this.value;
  }
}
