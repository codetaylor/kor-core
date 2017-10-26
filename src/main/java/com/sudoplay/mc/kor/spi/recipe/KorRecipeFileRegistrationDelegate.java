package com.sudoplay.mc.kor.spi.recipe;

import com.sudoplay.mc.kor.core.recipe.RecipeFile;
import com.sudoplay.mc.kor.core.recipe.RecipeFileParseResults;
import com.sudoplay.mc.kor.core.recipe.RecipeFileParser;
import com.sudoplay.mc.kor.core.recipe.exception.RecipeItemNotFoundInRegistryException;
import com.sudoplay.mc.kor.core.recipe.furnace.RecipeFurnaceParseResults;
import com.sudoplay.mc.kor.core.recipe.furnace.RecipeFurnaceRegistrationDelegate;
import com.sudoplay.mc.kor.spi.registry.KorRegistrationDelegate;
import com.sudoplay.mc.kor.spi.registry.strategy.KorInitStrategy;

import java.util.List;

/**
 * Created by codetaylor on 11/18/2016.
 */
public class KorRecipeFileRegistrationDelegate
    extends KorRegistrationDelegate {

  private RecipeFile recipeFile;
  private RecipeFurnaceRegistrationDelegate recipeFurnaceRegistrationDelegate;

  public KorRecipeFileRegistrationDelegate(
      RecipeFile recipeFile
  ) {

    this.recipeFile = recipeFile;
    this.recipeFurnaceRegistrationDelegate = new RecipeFurnaceRegistrationDelegate();
  }

  @Override
  public KorInitStrategy getInitStrategy() {

    return kor -> {
      RecipeFileParser recipeFileParser = kor.getRecipeFileParser();
      RecipeFileParseResults recipeFileParseResults = recipeFileParser.parseRecipeFile(this.recipeFile);

      List<RecipeFurnaceParseResults> furnaceParseResultsList = recipeFileParseResults.getFurnaceParseResultsList();

      for (RecipeFurnaceParseResults results : furnaceParseResultsList) {

        try {
          this.recipeFurnaceRegistrationDelegate.registerFurnaceRecipe(results);

        } catch (RecipeItemNotFoundInRegistryException e) {
          kor.getLoggerService().error(String.format("Failed to register furnace recipe [%s]", results.getName()), e);
        }
      }
    };
  }
}
