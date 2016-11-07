package com.sudoplay.mc.kor.spi.recipe.armor;

import com.sudoplay.mc.kor.spi.recipe.KorRecipeCraftingShaped;
import com.sudoplay.mc.kor.spi.recipe.KorRecipeInput;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Created by sk3lls on 10/29/2016.
 */
public class KorRecipeLeggings extends
    KorRecipeCraftingShaped {

  public KorRecipeLeggings(KorRecipeInput armorMaterial, Item outputArmor) {
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
