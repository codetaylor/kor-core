package com.sudoplay.mc.kor.spi.recipe;

import com.sudoplay.mc.kor.spi.registry.strategy.KorInitStrategy;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapelessOreRecipe;

/**
 * Created by codetaylor on 10/28/2016.
 */
public class KorRecipeCraftingShapeless extends
    KorRecipeCrafting {

  private ItemStack output;
  private Object[] params;

  public KorRecipeCraftingShapeless(ItemStack output, Object[] params) {
    this.output = output;
    this.params = params;

    this.resolveRecipeComponentsInObjectArray(params);
  }

  @Override
  public KorInitStrategy getInitStrategy() {
    return (mod) -> {
      GameRegistry.addRecipe(new ShapelessOreRecipe(this.output, this.params));
    };
  }
}
