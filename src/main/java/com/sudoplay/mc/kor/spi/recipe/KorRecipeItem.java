package com.sudoplay.mc.kor.spi.recipe;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Created by sk3lls on 10/28/2016.
 */
public class KorRecipeItem {

  private Object component;
  private int quantity;
  private int meta;

  public static KorRecipeItem from(Block block) {
    return new KorRecipeItem(block, 1, 32767);
  }

  public static KorRecipeItem from(Block block, int quantity) {
    return new KorRecipeItem(block, quantity, 32767);
  }

  public static KorRecipeItem from(Block block, int quantity, int meta) {
    return new KorRecipeItem(block, quantity, meta);
  }

  public static KorRecipeItem from(Item item) {
    return new KorRecipeItem(item, 1, 32767);
  }

  public static KorRecipeItem from(Item item, int quantity) {
    return new KorRecipeItem(item, quantity, 32767);
  }

  public static KorRecipeItem from(Item item, int quantity, int meta) {
    return new KorRecipeItem(item, quantity, meta);
  }

  public ItemStack getItemStack() {

    if (this.component instanceof Block) {
      return new ItemStack((Block) this.component, this.quantity, this.meta);

    } else if (this.component instanceof Item) {
      return new ItemStack((Item) this.component, this.quantity, this.meta);

    } else {
      throw new IllegalStateException("Expected Item or Block, got " + this.component.getClass());
    }
  }

  private KorRecipeItem(Object component, int quantity, int meta) {
    this.component = component;
    this.quantity = quantity;
    this.meta = meta;
  }
}
