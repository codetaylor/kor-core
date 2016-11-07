package com.sudoplay.mc.kor.spi.recipe;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

/**
 * Created by sk3lls on 10/28/2016.
 */
public class KorRecipeInput {

  private Object component;

  public static KorRecipeInput from(Block block) {
    return new KorRecipeInput(block);
  }

  public static KorRecipeInput from(Item item) {
    return new KorRecipeInput(item);
  }

  public Object get() {
    return this.component;
  }

  private KorRecipeInput(Object component) {
    this.component = component;
  }
}
