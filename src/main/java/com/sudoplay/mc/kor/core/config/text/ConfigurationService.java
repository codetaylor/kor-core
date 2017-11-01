package com.sudoplay.mc.kor.core.config.text;

import net.minecraftforge.common.config.Configuration;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by codetaylor on 10/27/2016.
 */
public class ConfigurationService
    implements IConfigurationService {

  private Map<String, Configuration> configurationMap;
  private IConfigurationLoader configurationLoader;
  private File modConfigurationDirectory;

  public ConfigurationService(
      IConfigurationLoader configurationLoader,
      File modConfigurationDirectory
  ) {

    this.configurationLoader = configurationLoader;
    this.modConfigurationDirectory = modConfigurationDirectory;
    this.configurationMap = new HashMap<>();
  }

  @Override
  public Configuration get(String name) {

    return this.configurationMap.get(name.replace("\\", "/"));
  }

  @Override
  public Configuration loadConfiguration(
      File configurationFile
  ) {

    String path = configurationFile.getPath();

    configurationFile = new File(this.modConfigurationDirectory, path);

    Configuration configuration = this.configurationLoader.loadConfiguration(
        configurationFile
    );

    this.configurationMap.put(path.replace("\\", "/"), configuration);

    return configuration;
  }

}
