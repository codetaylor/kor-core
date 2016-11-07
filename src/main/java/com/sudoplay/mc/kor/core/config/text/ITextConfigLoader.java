package com.sudoplay.mc.kor.core.config.text;

import com.sudoplay.mc.kor.spi.config.forge.KorForgeConfigurationAdapter;

/**
 * Created by sk3lls on 10/30/2016.
 */
public interface ITextConfigLoader<T> {

  T loadConfiguration(
      String configurationPathname,
      String configurationFilename, TextConfigData textConfigData,
      KorForgeConfigurationAdapter<T>... modConfigurationAdapters
  );
}
