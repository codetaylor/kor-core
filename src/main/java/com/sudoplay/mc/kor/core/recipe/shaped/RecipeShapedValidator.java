package com.sudoplay.mc.kor.core.recipe.shaped;

import com.sudoplay.mc.kor.core.log.LoggerService;
import com.sudoplay.mc.kor.core.recipe.ParseResult;
import com.sudoplay.mc.kor.core.recipe.RecipeItemWhiteList;

import java.util.List;

public class RecipeShapedValidator {

  private String modId;
  private RecipeItemWhiteList recipeItemWhiteList;
  private LoggerService loggerService;

  public RecipeShapedValidator(
      String modId,
      RecipeItemWhiteList recipeItemWhiteList,
      LoggerService loggerService
  ) {
    this.modId = modId;
    this.recipeItemWhiteList = recipeItemWhiteList;
    this.loggerService = loggerService;
  }

  public boolean isValidShaped(String name, RecipeShapedParseResults results) {

    ParseResult outputParseResult = results.getOutputParseResult();

    if (outputParseResult == ParseResult.NULL) {
      this.loggerService.info(String.format("Output for shaped recipe [%s] can't be null", name));
      return false;
    }

    if ("ore".equals(outputParseResult.getDomain())) {
      this.loggerService.info(String.format("Output for shaped recipe [%s] can't be an ore dictionary value", name));
      return false;
    }

    if (!this.isValid(outputParseResult)) {
      this.loggerService.info(String.format("Shaped recipe [%s] not loaded because item [%s] has not been white-listed", name, outputParseResult));
      return false;
    }

    List<List<ParseResult>> inputParseResultList = results.getInputParseResultList();

    if (inputParseResultList.size() > 3) {
      this.loggerService.info(String.format("Malformed shaped recipe [%s] too many input rows, max is 3", name));
      return false;
    }

    int rowSize = inputParseResultList.get(0).size();

    for (int i = 1; i < inputParseResultList.size(); i++) {
      List<ParseResult> row = inputParseResultList.get(i);

      if (row.size() != rowSize) {
        this.loggerService.info(String.format(
            "Malformed shaped recipe [%s] rows must be the same size, row %d has %d items, expected %d",
            name,
            i,
            row.size(),
            rowSize
        ));
        return false;
      }
    }

    for (int i = 0; i < inputParseResultList.size(); i++) {
      List<ParseResult> row = inputParseResultList.get(i);

      if (row.size() > 3) {
        this.loggerService.info(String.format("Malformed shaped recipe [%s] too many items in row %d", name, i));
        return false;
      }

      for (ParseResult result : row) {

        if (!this.isValid(result)) {
          this.loggerService.info(String.format("Shaped recipe [%s] not loaded because item [%s] is not white-listed", name, result));
          return false;
        }
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