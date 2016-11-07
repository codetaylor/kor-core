package com.sudoplay.mc.kor.core.config.text;

import com.sudoplay.mc.kor.spi.config.forge.KorForgeConfigurationAdapter;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

/**
 * Created by sk3lls on 10/26/2016.
 */
public class TextConfigLoader implements
    ITextConfigLoader<TextConfigData> {

  @Override
  public TextConfigData loadConfiguration(
      String configurationPathname,
      String configurationFilename,
      TextConfigData textConfigData,
      KorForgeConfigurationAdapter... modConfigurationAdapters
  ) {
    Configuration configuration;
    File configurationPath;
    String path;
    File configurationFile;

    configurationPath = new File(configurationPathname);
    //noinspection ResultOfMethodCallIgnored
    configurationPath.mkdirs();
    path = configurationPath.getPath();
    configurationFile = new File(path, configurationFilename);

    configuration = new Configuration(configurationFile);

    for (KorForgeConfigurationAdapter adapter : modConfigurationAdapters) {
      //noinspection unchecked
      adapter.adapt(configuration, textConfigData);
    }

    configuration.save();

    return textConfigData;
  }
}
