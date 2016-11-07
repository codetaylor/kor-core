package com.sudoplay.mc.kor.spi.recipe;

import com.sudoplay.mc.kor.spi.registry.strategy.KorInitStrategy;
import com.sudoplay.mc.kor.spi.registry.provider.KorInitStrategyProvider;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by sk3lls on 10/28/2016.
 */
public class KorRecipeSmelting implements
    KorInitStrategyProvider {

  private KorRecipeInput input;
  private ItemStack output;
  private float xp;

  public KorRecipeSmelting(Block input, ItemStack output, float xp) {
    this.input = KorRecipeInput.from(input);
    this.output = output;
    this.xp = xp;
  }

  public KorRecipeSmelting(Item input, ItemStack output, float xp) {
    this.input = KorRecipeInput.from(input);
    this.output = output;
    this.xp = xp;
  }

  public KorRecipeSmelting(KorRecipeInput input, ItemStack output, float xp) {
    this.input = input;
    this.output = output;
    this.xp = xp;
  }

  @Override
  public KorInitStrategy getInitStrategy() {
    return (mod) -> {
      Object input = this.input.get();

      if (input instanceof Block) {
        GameRegistry.addSmelting((Block) input, this.output, this.xp);

      } else if (input instanceof Item) {
        GameRegistry.addSmelting((Item) input, this.output, this.xp);
      }
    };
  }
}
