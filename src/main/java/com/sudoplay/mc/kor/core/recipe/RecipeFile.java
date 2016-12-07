package com.sudoplay.mc.kor.core.recipe;

import com.google.gson.annotations.SerializedName;
import com.sudoplay.mc.kor.core.recipe.furnace.RecipeFurnace;
import com.sudoplay.mc.kor.core.recipe.shaped.RecipeShaped;
import com.sudoplay.mc.kor.core.recipe.shapeless.RecipeShapeless;
import com.sudoplay.mc.kor.spi.config.json.KorConfigObject;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by codetaylor on 11/16/2016.
 */
public class RecipeFile extends
    KorConfigObject {

  @SerializedName("shapeless")
  protected Map<String, RecipeShapeless> recipeShapelessMap;

  @SerializedName("shaped")
  protected Map<String, RecipeShaped> recipeShapedMap;

  @SerializedName("furnace")
  protected Map<String, RecipeFurnace> recipeFurnaceMap;

  public RecipeFile() {
    this.recipeShapelessMap = new LinkedHashMap<>();
    this.recipeShapedMap = new LinkedHashMap<>();
    this.recipeFurnaceMap = new LinkedHashMap<>();
  }

  public Map<String, RecipeShapeless> getRecipeShapelessMap() {
    return recipeShapelessMap;
  }

  public Map<String, RecipeShaped> getRecipeShapedMap() {
    return recipeShapedMap;
  }

  public Map<String, RecipeFurnace> getRecipeFurnaceMap() {
    return recipeFurnaceMap;
  }
}
