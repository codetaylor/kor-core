package com.sudoplay.mc.kor.core.recipe.shapeless;

/**
 * Created by sk3lls on 11/16/2016.
 */
public class RecipeShapeless {

  private String output;
  private String input;

  @SuppressWarnings("unused")
  private RecipeShapeless() {
    // serialization
  }

  public RecipeShapeless(String output, String input) {
    this.output = output;
    this.input = input;
  }

  public String getOutput() {
    return output;
  }

  public String getInput() {
    return input;
  }
}
