package com.sudoplay.mc.kor.core.recipe.furnace;

import com.sudoplay.mc.kor.core.recipe.ParseResult;
import com.sudoplay.mc.kor.core.recipe.RecipeItemParser;

public class RecipeFurnaceParser {

  private final RecipeItemParser recipeItemParser;

  public RecipeFurnaceParser(
      RecipeItemParser recipeItemParser
  ) {
    this.recipeItemParser = recipeItemParser;
  }

  public RecipeFurnaceParseResults getRecipeFurnaceParseResults(String name, RecipeFurnace recipe) {

    String output = recipe.getOutput();
    String input = recipe.getInput();
    float xp = recipe.getXp();

    ParseResult outputResult = this.recipeItemParser.parse(output);
    ParseResult inputResult = this.recipeItemParser.parse(input);

    return new RecipeFurnaceParseResults(name, outputResult, inputResult, xp);
  }
}