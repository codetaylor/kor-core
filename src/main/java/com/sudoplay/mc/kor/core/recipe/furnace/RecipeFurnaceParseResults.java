package com.sudoplay.mc.kor.core.recipe.furnace;

import com.sudoplay.mc.kor.core.recipe.ParseResult;

/**
 * Created by codetaylor on 11/17/2016.
 */
public class RecipeFurnaceParseResults {

  private String name;
  private ParseResult outputParseResult;
  private ParseResult inputParseResult;
  private float xp;

  public RecipeFurnaceParseResults(
      String name,
      ParseResult outputParseResult,
      ParseResult inputParseResult,
      float xp
  ) {
    this.name = name;
    this.outputParseResult = outputParseResult;
    this.inputParseResult = inputParseResult;
    this.xp = xp;
  }

  public String getName() {
    return name;
  }

  public ParseResult getOutputParseResult() {
    return outputParseResult;
  }

  public ParseResult getInputParseResult() {
    return inputParseResult;
  }

  public float getXp() {
    return xp;
  }
}
