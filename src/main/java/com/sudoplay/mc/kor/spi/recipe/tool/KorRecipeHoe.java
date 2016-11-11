package com.sudoplay.mc.kor.spi.recipe.tool;

import com.sudoplay.mc.kor.spi.recipe.KorRecipeCraftingShaped;
import com.sudoplay.mc.kor.spi.recipe.KorRecipeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Created by sk3lls on 10/28/2016.
 */
public class KorRecipeHoe extends
    KorRecipeCraftingShaped {

  public KorRecipeHoe(KorRecipeItem toolMaterial, KorRecipeItem handleMaterial, Item outputTool) {
    super(
        new ItemStack(outputTool),
        new Object[]{
            "##",
            " |",
            " |",
            '#', toolMaterial,
            '|', handleMaterial
        },
        Mirrored.True
    );
  }
}
