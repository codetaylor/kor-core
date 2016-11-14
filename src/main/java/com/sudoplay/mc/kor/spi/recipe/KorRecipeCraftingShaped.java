package com.sudoplay.mc.kor.spi.recipe;

import com.sudoplay.mc.kor.spi.registry.strategy.KorInitStrategy;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import java.util.Arrays;

/**
 * Created by sk3lls on 10/28/2016.
 */
public class KorRecipeCraftingShaped extends
    KorRecipeCrafting {

  private ItemStack output;
  private Object[] params;
  private Object[] mirroredParams;

  public enum Mirrored {
    True, False
  }

  public KorRecipeCraftingShaped(ItemStack output, Object[] params) {
    this(output, params, Mirrored.False);
  }

  public KorRecipeCraftingShaped(ItemStack output, Object[] params, Mirrored mirrored) {
    this.output = output;
    this.params = params;

    this.resolveRecipeComponentsInObjectArray(params);

    if (mirrored == Mirrored.True) {
      this.mirroredParams = Arrays.copyOf(this.params, this.params.length);

      for (int i = 0; i < this.mirroredParams.length; i++) {

        if (this.mirroredParams[i] instanceof String && ((String) this.mirroredParams[i]).length() > 1) {
          this.mirroredParams[i] = new StringBuilder((String) this.mirroredParams[i]).reverse().toString();
        }
      }
    }
  }

  @Override
  public KorInitStrategy getInitStrategy() {
    return (mod) -> {
      GameRegistry.addRecipe(new ShapedOreRecipe(this.output, this.params));

      if (this.mirroredParams != null) {
        GameRegistry.addRecipe(new ShapedOreRecipe(this.output, this.mirroredParams));
      }
    };
  }
}
