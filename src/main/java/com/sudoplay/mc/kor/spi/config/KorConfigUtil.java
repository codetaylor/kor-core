package com.sudoplay.mc.kor.spi.config;

import com.sudoplay.mc.kor.core.config.text.TextConfigData;
import net.minecraftforge.common.config.Configuration;

/**
 * Created by sk3lls on 11/22/2016.
 */
public class KorConfigUtil {

  public static void adaptBoolean(String category, String key, boolean defaultValue, Configuration configuration, TextConfigData textConfigData) {
    boolean value = configuration.get(category, key, defaultValue).getBoolean();
    textConfigData.getCategory(category).putBoolean(key, value);
  }

  private KorConfigUtil() {
    //
  }
}
