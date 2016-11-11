package com.sudoplay.mc.kor.spi.recipe;

import com.sudoplay.mc.kor.spi.registry.provider.KorInitStrategyProvider;
import com.sudoplay.mc.kor.spi.registry.strategy.KorInitStrategy;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by sk3lls on 10/28/2016.
 */
public class KorRecipeSmelting implements
    KorInitStrategyProvider {

  private KorRecipeItem input;
  private KorRecipeItem output;
  private float xp;

  public KorRecipeSmelting(KorRecipeItem input, KorRecipeItem output, float xp) {
    this.input = input;
    this.output = output;
    this.xp = xp;
  }

  @Override
  public KorInitStrategy getInitStrategy() {
    return (mod) -> {
      GameRegistry.addSmelting(this.input.getItemStack(), this.output.getItemStack(), this.xp);
    };
  }
}
