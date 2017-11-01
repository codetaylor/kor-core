package com.sudoplay.mc.kor.core.config.text;

import com.sudoplay.mc.kor.spi.config.forge.KorForgeConfigurationAdapter;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

/**
 * Created by codetaylor on 10/26/2016.
 */
public class ConfigurationLoader
    implements IConfigurationLoader {

  @Override
  public Configuration loadConfiguration(
      File configurationFile
  ) {

    Configuration configuration;

    //noinspection ResultOfMethodCallIgnored
    configurationFile.getParentFile().mkdirs();

    configuration = new Configuration(configurationFile);

    // TODO: is this necessary?
    configuration.save();

    return configuration;
  }

}
