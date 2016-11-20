package com.sudoplay.mc.kor.core.recipe.furnace;

import com.sudoplay.mc.kor.core.log.LoggerService;
import com.sudoplay.mc.kor.core.recipe.ParseResult;
import com.sudoplay.mc.kor.core.recipe.RecipeItemWhiteList;

public class RecipeFurnaceValidator {

  private String modId;
  private RecipeItemWhiteList recipeItemWhiteList;
  private LoggerService loggerService;

  public RecipeFurnaceValidator(
      String modId,
      RecipeItemWhiteList recipeItemWhiteList,
      LoggerService loggerService
  ) {
    this.modId = modId;
    this.recipeItemWhiteList = recipeItemWhiteList;
    this.loggerService = loggerService;
  }

  public boolean isValidFurnace(String name, RecipeFurnaceParseResults results) {

    ParseResult inputParseResult = results.getInputParseResult();
    ParseResult outputParseResult = results.getOutputParseResult();

    if (inputParseResult == ParseResult.NULL) {
      this.loggerService.error(String.format("Input for furnace recipe [%s] can't be null", name));
      return false;
    }

    if (inputParseResult.getQuantity() > 1) {
      this.loggerService.error(String.format("Input for furnace recipe [%s] must not have quantity larger than 1", name));
      return false;
    }

    if (outputParseResult == ParseResult.NULL) {
      this.loggerService.error(String.format("Output for furnace recipe [%s] can't be null", name));
      return false;
    }

    if ("ore".equals(inputParseResult.getDomain())) {
      this.loggerService.error(String.format("Input for furnace recipe [%s] can't be an ore dictionary value", name));
      return false;
    }

    if ("ore".equals(outputParseResult.getDomain())) {
      this.loggerService.error(String.format("Output for furnace recipe [%s] can't be an ore dictionary value", name));
      return false;
    }

    if (!this.isValid(outputParseResult)) {
      this.loggerService.error(String.format("Furnace recipe [%s] not loaded because item [%s] has not been white-listed", name, outputParseResult));
      return false;
    }

    return true;
  }

  private boolean isValid(ParseResult result) {
    boolean isOwnedItem = modId.equals(result.getDomain());
    boolean inWhiteList = this.recipeItemWhiteList.contains(result.getDomain() + ":" + result.getPath() + ":" + result.getMeta());
    return !isOwnedItem || inWhiteList;
  }
}