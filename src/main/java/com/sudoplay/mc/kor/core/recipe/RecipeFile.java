package com.sudoplay.mc.kor.core.recipe;

import com.google.gson.annotations.SerializedName;
import com.sudoplay.mc.kor.core.recipe.furnace.RecipeFurnace;
import com.sudoplay.mc.kor.spi.config.json.KorConfigObject;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by codetaylor on 11/16/2016.
 */
public class RecipeFile
    extends KorConfigObject {

  @SerializedName("furnace")
  protected Map<String, RecipeFurnace> recipeFurnaceMap;

  public RecipeFile() {

    this.recipeFurnaceMap = new LinkedHashMap<>();
  }

  public Map<String, RecipeFurnace> getRecipeFurnaceMap() {

    return recipeFurnaceMap;
  }
}
