package com.sudoplay.mc.kor.spi.item;

import net.minecraft.util.IStringSerializable;

import javax.annotation.Nonnull;

/**
 * Created by codetaylor on 10/31/2016.
 */
public interface ISubType extends
    IStringSerializable {

  @Nonnull
  String getName();

  int getMeta();
}
