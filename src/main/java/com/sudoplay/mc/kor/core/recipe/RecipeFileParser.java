package com.sudoplay.mc.kor.core.recipe;

import com.sudoplay.mc.kor.core.log.LoggerService;
import com.sudoplay.mc.kor.core.recipe.exception.MalformedRecipeItemException;
import com.sudoplay.mc.kor.core.recipe.furnace.RecipeFurnace;
import com.sudoplay.mc.kor.core.recipe.furnace.RecipeFurnaceParseResults;
import com.sudoplay.mc.kor.core.recipe.furnace.RecipeFurnaceParser;
import com.sudoplay.mc.kor.core.recipe.furnace.RecipeFurnaceValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by codetaylor on 11/16/2016.
 */
public class RecipeFileParser {

  private final RecipeFurnaceParser recipeFurnaceParser;
  private final RecipeFurnaceValidator recipeFurnaceValidator;
  private LoggerService loggerService;

  public RecipeFileParser(
      String modId,
      RecipeItemWhiteList recipeItemWhiteList,
      LoggerService loggerService
  ) {
    this.loggerService = loggerService;
    RecipeItemParser recipeItemParser = new RecipeItemParser();

    this.recipeFurnaceParser = new RecipeFurnaceParser(
        recipeItemParser
    );

    this.recipeFurnaceValidator = new RecipeFurnaceValidator(
        modId,
        recipeItemWhiteList,
        loggerService
    );
  }

  public RecipeFileParseResults parseRecipeFile(RecipeFile recipeFile) {

    List<RecipeFurnaceParseResults> furnaceParseResultsList = new ArrayList<>();

    for (Map.Entry<String, RecipeFurnace> entry : recipeFile.getRecipeFurnaceMap().entrySet()) {
      String name = entry.getKey();
      RecipeFurnace recipe = entry.getValue();

      try {
        RecipeFurnaceParseResults results;
        results = this.recipeFurnaceParser.getRecipeFurnaceParseResults(name, recipe);

        if (this.recipeFurnaceValidator.isValidFurnace(name, results)) {
          furnaceParseResultsList.add(results);
        }

      } catch (MalformedRecipeItemException e) {
        this.loggerService.error(String.format("Furnace recipe [%s] is malformed", name), e);
      }

    }

    return new RecipeFileParseResults(
        furnaceParseResultsList
    );
  }
}
