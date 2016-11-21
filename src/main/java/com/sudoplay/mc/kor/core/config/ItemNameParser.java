package com.sudoplay.mc.kor.core.config;

import com.sudoplay.mc.kor.core.recipe.ParseResult;
import com.sudoplay.mc.kor.core.recipe.exception.MalformedRecipeItemException;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ItemNameParser {

  @Nonnull
  public ParseResult parse(@Nullable String data) throws MalformedRecipeItemException {

    if (data == null || "null".equals(data.trim())) {
      return ParseResult.NULL;
    }

    ParseResult result = new ParseResult();
    String[] split = data.split(":");

    if (split.length == 0) {
      throw new MalformedRecipeItemException("Empty data");
    }

    if (split.length < 2 || split.length > 3) {
      throw new MalformedRecipeItemException(String.format("Too many segments in [%s], must be two or three segments: <domain:path> or <domain:path:meta>", data));
    }

    result.setMeta(0);

    if (split.length == 3) {
      try {
        result.setMeta(Integer.valueOf(split[2].trim()));

      } catch (NumberFormatException e) {
        throw new MalformedRecipeItemException(String.format("Expected integer, got [%s]", split[2].trim()));
      }
    }

    result.setDomain(split[0].trim());
    result.setPath(split[1].trim());

    return result;
  }
}