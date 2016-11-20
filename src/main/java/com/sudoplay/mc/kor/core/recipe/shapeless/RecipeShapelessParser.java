package com.sudoplay.mc.kor.core.recipe.shapeless;

import com.sudoplay.mc.kor.core.recipe.ParseResult;
import com.sudoplay.mc.kor.core.recipe.RecipeItemParser;
import com.sudoplay.mc.kor.core.recipe.exception.MalformedRecipeItemException;

import java.util.ArrayList;
import java.util.List;

public class RecipeShapelessParser {

  private final RecipeItemParser recipeItemParser;

  public RecipeShapelessParser(
      RecipeItemParser recipeItemParser
  ) {
    this.recipeItemParser = recipeItemParser;
  }

  public RecipeShapelessParseResults getRecipeShapelessParseResults(String name, RecipeShapeless recipe) throws MalformedRecipeItemException {

    String output = recipe.getOutput();
    String inputUnsplit = recipe.getInput();
    String[] input = inputUnsplit.split(",");

    ParseResult outputResult = this.recipeItemParser.parse(output);
    List<ParseResult> inputResultList = new ArrayList<>();

    for (String in : input) {
      inputResultList.add(this.recipeItemParser.parse(in));
    }

    return new RecipeShapelessParseResults(name, outputResult, inputResultList);
  }
}