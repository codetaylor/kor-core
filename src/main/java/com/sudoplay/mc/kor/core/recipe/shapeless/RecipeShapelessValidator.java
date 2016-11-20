package com.sudoplay.mc.kor.core.recipe.shapeless;

import com.sudoplay.mc.kor.core.log.LoggerService;
import com.sudoplay.mc.kor.core.recipe.ParseResult;
import com.sudoplay.mc.kor.core.recipe.RecipeItemWhiteList;

import java.util.List;

public class RecipeShapelessValidator {

  private String modId;
  private RecipeItemWhiteList recipeItemWhiteList;
  private LoggerService loggerService;

  public RecipeShapelessValidator(
      String modId,
      RecipeItemWhiteList recipeItemWhiteList,
      LoggerService loggerService
  ) {
    this.modId = modId;
    this.recipeItemWhiteList = recipeItemWhiteList;
    this.loggerService = loggerService;
  }

  public boolean isValidShapeless(String name, RecipeShapelessParseResults results) {

    ParseResult outputParseResult = results.getOutputParseResult();

    if (outputParseResult == ParseResult.NULL) {
      this.loggerService.error(String.format("Output for shapeless recipe [%s] can't be null", name));
      return false;
    }

    if ("ore".equals(outputParseResult.getDomain())) {
      this.loggerService.error(String.format("Output for shapeless recipe [%s] can't be an ore dictionary value", name));
      return false;
    }

    if (!this.isValid(outputParseResult)) {
      this.loggerService.error(String.format("Shapeless recipe [%s] not loaded because item [%s] has not been white-listed", name, outputParseResult));
      return false;
    }

    List<ParseResult> inputParseResultList = results.getInputParseResultList();

    if (inputParseResultList.size() > 9) {
      this.loggerService.error(String.format("Malformed shapeless recipe [%s] too many input items, max is 9", name));
      return false;
    }

    for (ParseResult result : inputParseResultList) {

      if (!this.isValid(result)) {
        this.loggerService.error(String.format("Shapeless recipe [%s] not loaded because item [%s] has not been white-listed", name, result));
        return false;
      }
    }

    return true;
  }

  private boolean isValid(ParseResult result) {
    boolean isOwnedItem = modId.equals(result.getDomain());
    boolean inWhiteList = this.recipeItemWhiteList.contains(result.getDomain() + ":" + result.getPath() + ":" + result.getMeta());
    return !isOwnedItem || inWhiteList;
  }
}