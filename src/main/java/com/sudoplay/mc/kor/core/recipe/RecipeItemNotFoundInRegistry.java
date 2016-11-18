package com.sudoplay.mc.kor.core.recipe;

/**
 * Created by sk3lls on 11/18/2016.
 */
public class RecipeItemNotFoundInRegistry extends
    Exception {

  public RecipeItemNotFoundInRegistry() {
    super();
  }

  public RecipeItemNotFoundInRegistry(String message) {
    super(message);
  }

  public RecipeItemNotFoundInRegistry(String message, Throwable cause) {
    super(message, cause);
  }

  public RecipeItemNotFoundInRegistry(Throwable cause) {
    super(cause);
  }

  public RecipeItemNotFoundInRegistry(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
