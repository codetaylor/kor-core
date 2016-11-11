package com.sudoplay.mc.kor.spi.recipe;

import com.sudoplay.mc.kor.spi.registry.provider.KorInitStrategyProvider;

/**
 * Created by sk3lls on 10/28/2016.
 */
/* package */ abstract class KorRecipeCrafting implements
    KorInitStrategyProvider {

  /* package */ void resolveRecipeComponentsInObjectArray(Object[] objectArray) {
    for (int i = 0; i < objectArray.length; i++) {

      if (objectArray[i] instanceof KorRecipeItem) {
        objectArray[i] = ((KorRecipeItem) objectArray[i]).getItemStack();
      }
    }
  }
}
