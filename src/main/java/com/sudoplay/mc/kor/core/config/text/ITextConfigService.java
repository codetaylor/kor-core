package com.sudoplay.mc.kor.core.config.text;

import com.sudoplay.mc.kor.spi.config.forge.KorForgeConfigurationAdapter;

import java.io.File;

/**
 * Created by sk3lls on 11/1/2016.
 */
public interface ITextConfigService {
  TextConfigData get(String name);

  TextConfigData loadConfiguration(
      File configurationFile,
      KorForgeConfigurationAdapter<TextConfigData>... configurationAdapters
  );
}
