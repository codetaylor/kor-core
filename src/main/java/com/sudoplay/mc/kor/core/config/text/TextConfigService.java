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
      File configurationFile,
      KorForgeConfigurationAdapter<TextConfigData>... configurationAdapters
  ) {
    TextConfigData textConfigData = new TextConfigData();

    String path = configurationFile.getPath();

    configurationFile = new File(this.modConfigurationDirectory, path);

    this.configurationLoader.loadConfiguration(
        configurationFile,
        textConfigData,
        configurationAdapters
    );

    this.put(path.replace("\\", "/"), textConfigData);

    return textConfigData;
  }

  private void put(String name, TextConfigData textConfigData) {
    this.putConfiguration(name, textConfigData);
  }

  private void putConfiguration(String name, TextConfigData textConfigData) {
    this.configurationMap.put(name, textConfigData);
  }
}
