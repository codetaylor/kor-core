package com.sudoplay.mc.kor.core.config.text;

import com.sudoplay.mc.kor.spi.config.forge.KorForgeConfigurationAdapter;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sk3lls on 10/27/2016.
 */
public class TextConfigService implements
    ITextConfigService {

  private Map<String, TextConfigData> configurationMap;
  private ITextConfigLoader<TextConfigData> configurationLoader;
  private File modConfigurationDirectory;

  public TextConfigService(
      ITextConfigLoader<TextConfigData> configurationLoader,
      File modConfigurationDirectory
  ) {
    this.configurationLoader = configurationLoader;
    this.modConfigurationDirectory = modConfigurationDirectory;
    this.configurationMap = new HashMap<>();
  }

  @Override
  public TextConfigData get(String name) {
    return this.configurationMap.get(name);
  }

  @Override
  public TextConfigData loadConfiguration(
      String configurationPathname,
      String configurationFilename,
      KorForgeConfigurationAdapter<TextConfigData>... configurationAdapters
  ) {
    TextConfigData textConfigData = new TextConfigData();
    configurationPathname = new File(this.modConfigurationDirectory, configurationPathname).getPath();

    this.configurationLoader.loadConfiguration(
        configurationPathname,
        configurationFilename,
        textConfigData,
        configurationAdapters
    );

    this.put(configurationFilename, textConfigData);

    return textConfigData;
  }

  private void put(String name, TextConfigData textConfigData) {
    this.putConfiguration(name, textConfigData);
  }

  private void putConfiguration(String name, TextConfigData textConfigData) {
    this.configurationMap.put(name, textConfigData);
  }
}
