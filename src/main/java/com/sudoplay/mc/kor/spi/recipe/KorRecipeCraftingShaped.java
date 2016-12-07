package com.sudoplay.mc.kor.spi.recipe;

import com.sudoplay.mc.kor.spi.registry.strategy.KorInitStrategy;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;

/**
 * Created by codetaylor on 10/28/2016.
 */
public class KorRecipeCraftingShaped extends
    KorRecipeCrafting {

  private ItemStack output;
  private Object[] params;

  public KorRecipeCraftingShaped(ItemStack output, Object[] params) {
    this.output = output;
    this.params = params;

    this.resolveRecipeComponentsInObjectArray(params);
  }

  @Override
  public KorInitStrategy getInitStrategy() {
    return (mod) -> {
      GameRegistry.addRecipe(new ShapedOreRecipe(this.output, this.params));
    };
  }
}
