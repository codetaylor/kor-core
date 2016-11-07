package com.sudoplay.mc.kor.spi.item;

/**
 * Created by sk3lls on 11/3/2016.
 */
public class KorSubType implements
    ISubType {

  private final int meta;
  private final String name;

  public static KorSubType from(int meta, String name) {
    return new KorSubType(meta, name);
  }

  private KorSubType(int meta, String name) {
    this.meta = meta;
    this.name = name;
  }

  @Override
  public int getMeta() {
    return this.meta;
  }

  @Override
  public String getName() {
    return this.name;
  }
}
