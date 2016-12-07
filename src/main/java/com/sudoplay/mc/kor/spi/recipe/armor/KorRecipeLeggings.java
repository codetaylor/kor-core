package com.sudoplay.mc.kor.spi.recipe.armor;

import com.sudoplay.mc.kor.spi.recipe.KorRecipeCraftingShaped;
import com.sudoplay.mc.kor.spi.recipe.KorRecipeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Created by codetaylor on 10/29/2016.
 */
public class KorRecipeLeggings extends
    KorRecipeCraftingShaped {

  public KorRecipeLeggings(KorRecipeItem armorMaterial, Item outputArmor) {
    super(
        new ItemStack(outputArmor),
        new Object[]{
            "###",
            "# #",
            "# #",
            '#', armorMaterial
        }
    );
  }

}
