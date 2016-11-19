package com.sudoplay.mc.kor.core.recipe;

import com.sudoplay.mc.kor.core.log.LoggerService;
import com.sudoplay.mc.kor.core.recipe.furnace.RecipeFurnace;
import com.sudoplay.mc.kor.core.recipe.furnace.RecipeFurnaceParseResults;
import com.sudoplay.mc.kor.core.recipe.furnace.RecipeFurnaceParser;
import com.sudoplay.mc.kor.core.recipe.furnace.RecipeFurnaceValidator;
import com.sudoplay.mc.kor.core.recipe.shaped.RecipeShaped;
import com.sudoplay.mc.kor.core.recipe.shaped.RecipeShapedParseResults;
import com.sudoplay.mc.kor.core.recipe.shaped.RecipeShapedParser;
import com.sudoplay.mc.kor.core.recipe.shaped.RecipeShapedValidator;
import com.sudoplay.mc.kor.core.recipe.shapeless.RecipeShapeless;
import com.sudoplay.mc.kor.core.recipe.shapeless.RecipeShapelessParseResults;
import com.sudoplay.mc.kor.core.recipe.shapeless.RecipeShapelessParser;
import com.sudoplay.mc.kor.core.recipe.shapeless.RecipeShapelessValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by sk3lls on 11/16/2016.
 */
public class RecipeFileParser {

  private final RecipeShapelessParser recipeShapelessParser;
  private final RecipeShapelessValidator recipeShapelessValidator;

  private final RecipeShapedParser recipeShapedParser;
  private final RecipeShapedValidator recipeShapedValidator;

  private final RecipeFurnaceParser recipeFurnaceParser;
  private final RecipeFurnaceValidator recipeFurnaceValidator;

  public RecipeFileParser(
      String modId,
      RecipeItemWhiteList recipeItemWhiteList,
      LoggerService loggerService
  ) {
    RecipeItemParser recipeItemParser = new RecipeItemParser();

    this.recipeShapelessParser = new RecipeShapelessParser(
        recipeItemParser
    );

    this.recipeShapelessValidator = new RecipeShapelessValidator(
        modId,
        recipeItemWhiteList,
        loggerService
    );

    this.recipeShapedParser = new RecipeShapedParser(
        recipeItemParser
    );

    this.recipeShapedValidator = new RecipeShapedValidator(
        modId,
        recipeItemWhiteList,
        loggerService
    );

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

    List<RecipeShapelessParseResults> shapelessParseResultsList = new ArrayList<>();
    List<RecipeShapedParseResults> shapedParseResultsList = new ArrayList<>();
    List<RecipeFurnaceParseResults> furnaceParseResultsList = new ArrayList<>();

    for (Map.Entry<String, RecipeShapeless> entry : recipeFile.getRecipeShapelessMap().entrySet()) {
      String name = entry.getKey();
      RecipeShapeless recipe = entry.getValue();

      RecipeShapelessParseResults results = this.recipeShapelessParser.getRecipeShapelessParseResults(name, recipe);

      if (this.recipeShapelessValidator.isValidShapeless(name, results)) {
        shapelessParseResultsList.add(results);
      }
    }

    for (Map.Entry<String, RecipeShaped> entry : recipeFile.getRecipeShapedMap().entrySet()) {
      String name = entry.getKey();
      RecipeShaped recipe = entry.getValue();

      RecipeShapedParseResults results = this.recipeShapedParser.getRecipeShapelessParseResults(name, recipe);

      if (this.recipeShapedValidator.isValidShaped(name, results)) {
        shapedParseResultsList.add(results);
      }
    }

    for (Map.Entry<String, RecipeFurnace> entry : recipeFile.getRecipeFurnaceMap().entrySet()) {
      String name = entry.getKey();
      RecipeFurnace recipe = entry.getValue();

      RecipeFurnaceParseResults results = this.recipeFurnaceParser.getRecipeFurnaceParseResults(name, recipe);

      if (this.recipeFurnaceValidator.isValidFurnace(name, results)) {
        furnaceParseResultsList.add(results);
      }
    }

    return new RecipeFileParseResults(
        shapelessParseResultsList,
        shapedParseResultsList,
        furnaceParseResultsList
    );
  }
}