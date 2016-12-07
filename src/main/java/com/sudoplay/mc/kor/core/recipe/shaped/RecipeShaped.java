package com.sudoplay.mc.kor.core.recipe.shaped;

/**
 * Created by codetaylor on 11/16/2016.
 */
public class RecipeShaped {

  private boolean mirrored;
  private String output;
  private String[] input;

  @SuppressWarnings("unused")
  private RecipeShaped() {
    // serialization
  }

  public RecipeShaped(String output, String[] input) {
    this(false, output, input);
  }

  public RecipeShaped(boolean mirrored, String output, String[] input) {
    this.mirrored = mirrored;
    this.output = output;
    this.input = input;
  }

  public String getOutput() {
    return output;
  }

  public String[] getInput() {
    return input;
  }

  public boolean isMirrored() {
    return mirrored;
  }
}
