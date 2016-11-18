package com.sudoplay.mc.kor.core.recipe.shaped;

import com.sudoplay.mc.kor.core.recipe.ParseResult;
import com.sudoplay.mc.kor.core.recipe.RecipeItemParser;

import java.util.ArrayList;
import java.util.List;

public class RecipeShapedParser {

  private final RecipeItemParser recipeItemParser;

  public RecipeShapedParser(
      RecipeItemParser recipeItemParser
  ) {
    this.recipeItemParser = recipeItemParser;
  }

  public RecipeShapedParseResults getRecipeShapelessParseResults(String name, RecipeShaped recipe) {

    String output = recipe.getOutput();
    String[] input = recipe.getInput();

    ParseResult outputResult = this.recipeItemParser.parse(output);
    List<List<ParseResult>> inputResultList = new ArrayList<>();

    for (String rowUnsplit : input) {
      ArrayList<ParseResult> rowResultList = new ArrayList<>();
      inputResultList.add(rowResultList);

      String[] row = rowUnsplit.split(",");

      for (String in : row) {
        rowResultList.add(this.recipeItemParser.parse(in));
      }
    }

    return new RecipeShapedParseResults(name, recipe.isMirrored(), outputResult, inputResultList);
  }
}