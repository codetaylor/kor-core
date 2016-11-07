package com.sudoplay.mc.kor.spi.recipe;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

/**
 * Created by sk3lls on 10/28/2016.
 */
public class KorRecipeOutput {

  private Object component;

  public static KorRecipeOutput from(Block block) {
    return new KorRecipeOutput(block);
  }

  public static KorRecipeOutput from(Item item) {
    return new KorRecipeOutput(item);
  }

  public Object get() {
    return this.component;
  }

  private KorRecipeOutput(Object component) {
    this.component = component;
  }
}
