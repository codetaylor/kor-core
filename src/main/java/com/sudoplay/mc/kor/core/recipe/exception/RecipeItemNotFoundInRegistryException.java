package com.sudoplay.mc.kor.core.recipe.exception;

/**
 * Created by codetaylor on 11/18/2016.
 */
public class RecipeItemNotFoundInRegistryException extends
    Exception {

  public RecipeItemNotFoundInRegistryException() {
    super();
  }

  public RecipeItemNotFoundInRegistryException(String message) {
    super(message);
  }

  public RecipeItemNotFoundInRegistryException(String message, Throwable cause) {
    super(message, cause);
  }

  public RecipeItemNotFoundInRegistryException(Throwable cause) {
    super(cause);
  }

  public RecipeItemNotFoundInRegistryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
