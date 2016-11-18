package com.sudoplay.mc.kor.core.recipe.shaped;

import com.sudoplay.mc.kor.core.recipe.ParseResult;

import java.util.List;

/**
 * Created by sk3lls on 11/17/2016.
 */
public class RecipeShapedParseResults {

  private String name;
  private boolean mirrored;
  private ParseResult outputParseResult;
  private List<List<ParseResult>> inputParseResultList;

  public RecipeShapedParseResults(
      String name,
      boolean mirrored,
      ParseResult outputParseResult,
      List<List<ParseResult>> inputParseResultList
  ) {
    this.name = name;
    this.mirrored = mirrored;
    this.outputParseResult = outputParseResult;
    this.inputParseResultList = inputParseResultList;
  }

  public String getName() {
    return name;
  }

  public ParseResult getOutputParseResult() {
    return outputParseResult;
  }

  public List<List<ParseResult>> getInputParseResultList() {
    return inputParseResultList;
  }

  public boolean isMirrored() {
    return mirrored;
  }
}
