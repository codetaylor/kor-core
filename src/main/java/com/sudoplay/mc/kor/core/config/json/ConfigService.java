package com.sudoplay.mc.kor.core.config.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sudoplay.mc.kor.core.log.LoggerService;
import com.sudoplay.mc.kor.spi.config.json.KorConfigObject;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * Created by sk3lls on 11/1/2016.
 */
public class ConfigService implements IConfigService {

  private final LoggerService loggerService;
  private final ConfigInstantiator configInstantiator;
  private Gson gson;
  private File modConfigurationDirectory;

  public ConfigService(
      LoggerService loggerService,
      File modConfigurationDirectory,
      double configsVersion
  ) {
    this.loggerService = loggerService;
    this.modConfigurationDirectory = modConfigurationDirectory;

    this.configInstantiator = new ConfigInstantiator();

    this.gson = new GsonBuilder()
        .setPrettyPrinting()
        .setVersion(configsVersion)
        .create();
  }

  @Override
  public <T extends KorConfigObject> T get(
      String pathname,
      String filename,
      Class<T> defaultValueClass
  ) {
    File filePath = new File(this.modConfigurationDirectory, pathname);

    if (filePath.mkdirs()) {
      this.loggerService.info(String.format("Created path: %s", filePath));
    }

    File fileJson = new File(filePath, filename);

    if (fileJson.exists() && fileJson.isFile()) {
      // TODO delegate to reader?
      try {
        FileReader fileReader = new FileReader(fileJson);
        T result = this.gson.fromJson(fileReader, defaultValueClass);
        fileReader.close();
        this.loggerService.info("Loaded json: [%s]", fileJson);
        return result;

      } catch (Exception e) {
        this.loggerService.warn("Unable to load json: [%s], trying defaults...", fileJson);
        return this.configInstantiator.instantiateKorConfigClass(defaultValueClass);
      }

    } else {
      // TODO delegate writer?
      try {
        T defaultValue = this.configInstantiator.instantiateKorConfigClass(defaultValueClass);
        FileWriter fileWriter = new FileWriter(fileJson);
        this.gson.toJson(defaultValue, fileWriter);
        fileWriter.close();
        this.loggerService.info("Generated json: [%s]", fileJson);
        return defaultValue;

      } catch (Exception e) {
        this.loggerService.warn("Unable to create json: [%s], trying defaults...", fileJson);
        return this.configInstantiator.instantiateKorConfigClass(defaultValueClass);
      }
    }
  }
}
