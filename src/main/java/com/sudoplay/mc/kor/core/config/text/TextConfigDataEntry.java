package com.sudoplay.mc.kor.core.config.text;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sk3lls on 10/26/2016.
 */
public class TextConfigDataEntry {

  private Map<String, Integer> integerMap;
  private Map<String, Boolean> booleanMap;
  private Map<String, String> stringMap;

  /* package */ TextConfigDataEntry() {
    this.integerMap = new HashMap<>();
    this.booleanMap = new HashMap<>();
    this.stringMap = new HashMap<>();
  }

  public void putInteger(String key, int value) {
    this.integerMap.put(key, value);
  }

  public Integer getInteger(String key) {
    return this.integerMap.get(key);
  }

  public void putBoolean(String key, boolean value) {
    this.booleanMap.put(key, value);
  }

  public Boolean getBoolean(String key) {
    return this.booleanMap.get(key);
  }

  public void putString(String key, String value) {
    this.stringMap.put(key, value);
  }

  public String getString(String key) {
    return this.stringMap.get(key);
  }
}
