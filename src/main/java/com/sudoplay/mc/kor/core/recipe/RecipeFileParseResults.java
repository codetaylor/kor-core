package com.sudoplay.mc.kor.core.recipe;

import com.sudoplay.mc.kor.core.recipe.furnace.RecipeFurnaceParseResults;

import java.util.List;

/**
 * Created by codetaylor on 11/17/2016.
 */
public class RecipeFileParseResults {

  private List<RecipeFurnaceParseResults> furnaceParseResultsList;

  public RecipeFileParseResults(
      List<RecipeFurnaceParseResults> furnaceParseResultsList
  ) {

    this.furnaceParseResultsList = furnaceParseResultsList;
  }

  public List<RecipeFurnaceParseResults> getFurnaceParseResultsList() {

    return furnaceParseResultsList;
  }
}
