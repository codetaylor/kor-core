package com.sudoplay.mc.kor.core.recipe.shapeless;

import com.sudoplay.mc.kor.core.recipe.ParseResult;

import java.util.List;

/**
 * Created by codetaylor on 11/17/2016.
 */
public class RecipeShapelessParseResults {

  private String name;
  private ParseResult outputParseResult;
  private List<ParseResult> inputParseResultList;

  public RecipeShapelessParseResults(String name, ParseResult outputParseResult, List<ParseResult> inputParseResultList) {
    this.name = name;
    this.outputParseResult = outputParseResult;
    this.inputParseResultList = inputParseResultList;
  }

  public String getName() {
    return name;
  }

  public ParseResult getOutputParseResult() {
    return outputParseResult;
  }

  public List<ParseResult> getInputParseResultList() {
    return inputParseResultList;
  }
}
