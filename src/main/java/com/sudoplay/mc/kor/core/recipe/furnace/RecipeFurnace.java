package com.sudoplay.mc.kor.core.recipe.furnace;

/**
 * Created by sk3lls on 11/16/2016.
 */
public class RecipeFurnace {

  private String output;
  private String input;
  private float xp;

  @SuppressWarnings("unused")
  private RecipeFurnace() {
    // serialization
  }

  public RecipeFurnace(String output, String input, float xp) {
    this.output = output;
    this.input = input;
    this.xp = xp;
  }

  public String getOutput() {
    return output;
  }

  public String getInput() {
    return input;
  }

  public float getXp() {
    return xp;
  }
}
