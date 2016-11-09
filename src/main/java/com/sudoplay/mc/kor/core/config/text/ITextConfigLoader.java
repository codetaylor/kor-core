package com.sudoplay.mc.kor.core.config.text;

import com.sudoplay.mc.kor.spi.config.forge.KorForgeConfigurationAdapter;

import java.io.File;

/**
 * Created by sk3lls on 10/30/2016.
 */
public interface ITextConfigLoader<T> {

  T loadConfiguration(
      File file,
      TextConfigData textConfigData,
      KorForgeConfigurationAdapter<T>... modConfigurationAdapters
  );
}
