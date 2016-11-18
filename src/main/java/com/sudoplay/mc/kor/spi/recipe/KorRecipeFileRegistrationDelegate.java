package com.sudoplay.mc.kor.spi.recipe;

import com.sudoplay.mc.kor.core.recipe.RecipeFile;
import com.sudoplay.mc.kor.core.recipe.RecipeFileParseResults;
import com.sudoplay.mc.kor.core.recipe.RecipeFileParser;
import com.sudoplay.mc.kor.core.recipe.RecipeItemNotFoundInRegistry;
import com.sudoplay.mc.kor.core.recipe.furnace.RecipeFurnaceParseResults;
import com.sudoplay.mc.kor.core.recipe.furnace.RecipeFurnaceRegistrationDelegate;
import com.sudoplay.mc.kor.core.recipe.shaped.RecipeShapedParseResults;
import com.sudoplay.mc.kor.core.recipe.shaped.RecipeShapedRegistrationDelegate;
import com.sudoplay.mc.kor.core.recipe.shapeless.RecipeShapelessParseResults;
import com.sudoplay.mc.kor.core.recipe.shapeless.RecipeShapelessRegistrationDelegate;
import com.sudoplay.mc.kor.spi.registry.KorRegistrationDelegate;
import com.sudoplay.mc.kor.spi.registry.strategy.KorInitStrategy;

import java.util.List;

/**
 * Created by sk3lls on 11/18/2016.
 */
public class KorRecipeFileRegistrationDelegate extends
    KorRegistrationDelegate {

  private RecipeFile recipeFile;
  private RecipeShapelessRegistrationDelegate recipeShapelessRegistrationDelegate;
  private RecipeShapedRegistrationDelegate recipeShapedRegistrationDelegate;
  private RecipeFurnaceRegistrationDelegate recipeFurnaceRegistrationDelegate;

  public KorRecipeFileRegistrationDelegate(
      RecipeFile recipeFile
  ) {
    this.recipeFile = recipeFile;
    this.recipeShapelessRegistrationDelegate = new RecipeShapelessRegistrationDelegate();
    this.recipeShapedRegistrationDelegate = new RecipeShapedRegistrationDelegate();
    this.recipeFurnaceRegistrationDelegate = new RecipeFurnaceRegistrationDelegate();
  }

  @Override
  public KorInitStrategy getInitStrategy() {
    return kor -> {
      RecipeFileParser recipeFileParser = kor.getRecipeFileParser();
      RecipeFileParseResults recipeFileParseResults = recipeFileParser.parseRecipeFile(this.recipeFile);

      List<RecipeShapelessParseResults> shapelessParseResultsList = recipeFileParseResults.getShapelessParseResultsList();

      for (RecipeShapelessParseResults results : shapelessParseResultsList) {

        try {
          this.recipeShapelessRegistrationDelegate.registerShapelessRecipe(results);

        } catch (RecipeItemNotFoundInRegistry e) {
          kor.getLoggerService().error(String.format("Failed to register shapeless recipe [%s]", results.getName()), e);
        }
      }

      List<RecipeShapedParseResults> shapedParseResultsList = recipeFileParseResults.getShapedParseResultsList();

      for (RecipeShapedParseResults results : shapedParseResultsList) {

        try {
          this.recipeShapedRegistrationDelegate.registerShapedRecipe(results);

        } catch (RecipeItemNotFoundInRegistry e) {
          kor.getLoggerService().error(String.format("Failed to register shaped recipe [%s]", results.getName()), e);
        }
      }

      List<RecipeFurnaceParseResults> furnaceParseResultsList = recipeFileParseResults.getFurnaceParseResultsList();

      for (RecipeFurnaceParseResults results : furnaceParseResultsList) {

        try {
          this.recipeFurnaceRegistrationDelegate.registerFurnaceRecipe(results);

        } catch (RecipeItemNotFoundInRegistry e) {
          kor.getLoggerService().error(String.format("Failed to register furnace recipe [%s]", results.getName()), e);
        }
      }
    };
  }
}
