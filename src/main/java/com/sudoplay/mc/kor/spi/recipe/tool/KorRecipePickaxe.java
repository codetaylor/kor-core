package com.sudoplay.mc.kor.spi.recipe.tool;

import com.sudoplay.mc.kor.spi.recipe.KorRecipeCraftingShaped;
import com.sudoplay.mc.kor.spi.recipe.KorRecipeInput;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Created by sk3lls on 10/28/2016.
 */
public class KorRecipePickaxe extends
    KorRecipeCraftingShaped {

  public KorRecipePickaxe(KorRecipeInput toolMaterial, KorRecipeInput handleMaterial, Item outputTool) {
    super(
        new ItemStack(outputTool),
        new Object[]{
            "###",
            " | ",
            " | ",
            '#', toolMaterial,
            '|', handleMaterial
        }
    );
  }
}
