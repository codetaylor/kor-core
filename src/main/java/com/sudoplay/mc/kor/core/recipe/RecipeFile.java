package com.sudoplay.mc.kor.core.recipe;

import com.google.gson.annotations.SerializedName;
import com.sudoplay.mc.kor.core.recipe.furnace.RecipeFurnace;
import com.sudoplay.mc.kor.core.recipe.shaped.RecipeShaped;
import com.sudoplay.mc.kor.core.recipe.shapeless.RecipeShapeless;
import com.sudoplay.mc.kor.spi.config.json.KorConfigObject;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by sk3lls on 11/16/2016.
 */
public class RecipeFile extends
    KorConfigObject {

  @SerializedName("shapeless")
  protected Map<String, RecipeShapeless> recipeShapelessList;

  @SerializedName("shaped")
  protected Map<String, RecipeShaped> recipeShapedList;

  @SerializedName("furnace")
  protected Map<String, RecipeFurnace> recipeFurnaceList;

  public RecipeFile() {
    this.recipeShapelessList = new LinkedHashMap<>();
    this.recipeShapedList = new LinkedHashMap<>();
    this.recipeFurnaceList = new LinkedHashMap<>();
  }

  public Map<String, RecipeShapeless> getRecipeShapelessMap() {
    return recipeShapelessList;
  }

  public Map<String, RecipeShaped> getRecipeShapedMap() {
    return recipeShapedList;
  }

  public Map<String, RecipeFurnace> getRecipeFurnaceList() {
    return recipeFurnaceList;
  }
}
