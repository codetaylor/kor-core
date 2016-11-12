package com.sudoplay.mc.kor.spi.registry;

/**
 * Created by sk3lls on 11/11/2016.
 */
public class KorOreDictionaryEntry {

  private final String name;
  private final int meta;

  public KorOreDictionaryEntry(String name, int meta) {
    this.name = name;
    this.meta = meta;
  }

  public String getName() {
    return name;
  }

  public int getMeta() {
    return meta;
  }
}
