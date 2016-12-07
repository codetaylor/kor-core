package com.sudoplay.mc.kor.core.recipe;

import com.sudoplay.mc.kor.core.recipe.furnace.RecipeFurnaceParseResults;
import com.sudoplay.mc.kor.core.recipe.shaped.RecipeShapedParseResults;
import com.sudoplay.mc.kor.core.recipe.shapeless.RecipeShapelessParseResults;

import java.util.List;

/**
 * Created by codetaylor on 11/17/2016.
 */
public class RecipeFileParseResults {

  private List<RecipeShapelessParseResults> shapelessParseResultsList;
  private List<RecipeShapedParseResults> shapedParseResultsList;
  private List<RecipeFurnaceParseResults> furnaceParseResultsList;

  public RecipeFileParseResults(
      List<RecipeShapelessParseResults> shapelessParseResultsList,
      List<RecipeShapedParseResults> shapedParseResultsList,
      List<RecipeFurnaceParseResults> furnaceParseResultsList
  ) {
    this.shapelessParseResultsList = shapelessParseResultsList;
    this.shapedParseResultsList = shapedParseResultsList;
    this.furnaceParseResultsList = furnaceParseResultsList;
  }

  public List<RecipeShapelessParseResults> getShapelessParseResultsList() {
    return shapelessParseResultsList;
  }

  public List<RecipeShapedParseResults> getShapedParseResultsList() {
    return shapedParseResultsList;
  }

  public List<RecipeFurnaceParseResults> getFurnaceParseResultsList() {
    return furnaceParseResultsList;
  }
}
