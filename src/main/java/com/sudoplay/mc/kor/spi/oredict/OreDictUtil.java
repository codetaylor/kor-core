package com.sudoplay.mc.kor.spi.oredict;

/**
 * Created by sk3lls on 11/16/2016.
 */
public class OreDictUtil {

  /**
   * Takes a name written in snake-case, like_this or like_another_example, and
   * returns a camel-case version, LikeThis or LikeAnotherExample.
   *
   * @param name the snake_case to convert
   * @return the CamelCase result
   */
  public static String convertSnakeCaseToCamelCase(String name) {
    String[] parts = name.split("_");

    String newName = "";

    for (String part : parts) {
      newName += part.substring(0, 1).toUpperCase() + part.substring(1);
    }
    return newName;
  }

}
