package com.sudoplay.mc.kor.core.recipe;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class RecipeItemParser {

  @Nonnull
  public ParseResult parse(@Nullable String data) {

    if (data == null || "null".equals(data.trim())) {
      return ParseResult.NULL;
    }

    ParseResult result = new ParseResult();
    String[] split = data.split("\\*");

    if (split.length == 0) {
      throw new RuntimeException("Empty data");
    }

    result.setQuantity(1);

    if (split.length > 1) {
      result.setQuantity(Integer.valueOf(split[1].trim()));
    }

    split = split[0].split(":");

    if (split.length < 2 || split.length > 3) {
      throw new RuntimeException(String.format("Too many segments in %s, must be two or three segments: <domain:path> or <domain:path:meta>", data));
    }

    result.setMeta(0);

    if (split.length == 3) {
      result.setMeta(Integer.valueOf(split[2].trim()));
    }

    result.setDomain(split[0].trim());
    result.setPath(split[1].trim());

    return result;
  }
}