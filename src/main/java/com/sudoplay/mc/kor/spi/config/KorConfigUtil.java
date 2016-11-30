package com.sudoplay.mc.kor.spi.config;

import com.sudoplay.mc.kor.core.config.text.TextConfigData;
import net.minecraftforge.common.config.Configuration;

/**
 * Created by sk3lls on 11/22/2016.
 */
public class KorConfigUtil {

  public static void adaptBoolean(String category, String key, String description, boolean defaultValue, Configuration configuration, TextConfigData textConfigData) {
    boolean value = configuration.get(category, key, defaultValue, description).getBoolean();
    textConfigData.getCategory(category).putBoolean(key, value);
  }

  public static void adaptBoolean(String category, String key, boolean defaultValue, Configuration configuration, TextConfigData textConfigData) {
    boolean value = configuration.get(category, key, defaultValue).getBoolean();
    textConfigData.getCategory(category).putBoolean(key, value);
  }

  public static void adaptInteger(String category, String key, int defaultValue, Configuration configuration, TextConfigData textConfigData) {
    int value = configuration.get(category, key, defaultValue).getInt();
    textConfigData.getCategory(category).putInteger(key, value);
  }

  public static void adaptInteger(String category, String key, String description, int defaultValue, Configuration configuration, TextConfigData textConfigData) {
    int value = configuration.get(category, key, defaultValue, description).getInt();
    textConfigData.getCategory(category).putInteger(key, value);
  }

  public static void adaptInteger(String category, String key, String description, int defaultValue, int minValue, int maxValue, Configuration configuration, TextConfigData textConfigData) {
    int value = configuration.get(category, key, defaultValue, description, minValue, maxValue).getInt();
    textConfigData.getCategory(category).putInteger(key, value);
  }

  public static void adaptDouble(String category, String key, double defaultValue, Configuration configuration, TextConfigData textConfigData) {
    double value = configuration.get(category, key, defaultValue).getDouble();
    textConfigData.getCategory(category).putDouble(key, value);
  }

  public static void adaptDouble(String category, String key, String description, double defaultValue, Configuration configuration, TextConfigData textConfigData) {
    double value = configuration.get(category, key, defaultValue, description).getDouble();
    textConfigData.getCategory(category).putDouble(key, value);
  }

  public static void adaptDouble(String category, String key, String description, double defaultValue, int minValue, int maxValue, Configuration configuration, TextConfigData textConfigData) {
    double value = configuration.get(category, key, defaultValue, description, minValue, maxValue).getDouble();
    textConfigData.getCategory(category).putDouble(key, value);
  }

  private KorConfigUtil() {
    //
  }
}
