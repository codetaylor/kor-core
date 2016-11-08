package com.sudoplay.mc.kor.core.config.text;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by sk3lls on 10/26/2016.
 */
public class TextConfigData {

  private Map<String, TextConfigDataEntry> categoryMap;

  /* package */ TextConfigData() {
    this.categoryMap = new LinkedHashMap<>();
  }

  public TextConfigDataEntry getCategory(String name) {
    TextConfigDataEntry category = this.categoryMap.get(name);

    if (category == null) {
      category = new TextConfigDataEntry();
      this.categoryMap.put(name, category);
    }
    return category;
  }
}
