package com.sudoplay.mc.kor.core.config.text;

import com.sudoplay.mc.kor.spi.config.forge.KorForgeConfigurationAdapter;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

/**
 * Created by codetaylor on 10/26/2016.
 */
public class TextConfigLoader implements
    ITextConfigLoader<TextConfigData> {

  @Override
  public TextConfigData loadConfiguration(
      File configurationFile,
      TextConfigData textConfigData,
      KorForgeConfigurationAdapter... modConfigurationAdapters
  ) {
    Configuration configuration;

    //noinspection ResultOfMethodCallIgnored
    configurationFile.getParentFile().mkdirs();

    configuration = new Configuration(configurationFile);

    for (KorForgeConfigurationAdapter adapter : modConfigurationAdapters) {
      //noinspection unchecked
      adapter.adapt(configuration, textConfigData);
    }

    configuration.save();

    return textConfigData;
  }
}
