package com.sudoplay.mc.kor.spi.util;

import com.sudoplay.mc.kor.spi.registry.IKorResourceSubfolderProvider;

/**
 * Created by codetaylor on 11/16/2016.
 */
public class StringUtils {

  /**
   * Takes a name written in snake-case, like_this or like_another_example, and
   * returns a PascalCase version, LikeThis or LikeAnotherExample.
   *
   * @param name the snake_case to convert
   * @return the PascalCase result
   */
  public static String convertSnakeCaseToPascalCase(String name) {
    String[] parts = name.split("_");
    StringBuilder newName = new StringBuilder();

    for (String part : parts) {
      newName.append(part.substring(0, 1).toUpperCase()).append(part.substring(1));
    }

    return newName.toString();
  }

  public static String getResourceSubfolder(Object o) {
    String subfolder = "";

    if (o instanceof IKorResourceSubfolderProvider) {
      subfolder = ((IKorResourceSubfolderProvider) o).getResourceSubfolder();
      subfolder = subfolder.replaceAll("\\\\", "/");

      if (!subfolder.endsWith("/")) {
        subfolder += "/";
      }
    }
    return subfolder;
  }
}
