package com.sudoplay.mc.kor.core.config.json;

import com.google.gson.Gson;
import com.sudoplay.mc.kor.core.log.LoggerService;
import com.sudoplay.mc.kor.spi.config.json.KorConfigObject;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * Created by codetaylor on 11/1/2016.
 */
public class JsonConfigService
    implements IJsonConfigService {

  private final LoggerService loggerService;
  private final ConfigInstantiator configInstantiator;
  private Gson gson;
  private File modConfigurationDirectory;

  public JsonConfigService(
      LoggerService loggerService,
      File modConfigurationDirectory,
      Gson gson
  ) {

    this.loggerService = loggerService;
    this.modConfigurationDirectory = modConfigurationDirectory;
    this.gson = gson;

    this.configInstantiator = new ConfigInstantiator();
  }

  @Override
  public <T extends KorConfigObject> T get(
      String pathname,
      String filename,
      Class<T> defaultValueClass
  ) {

    File filePath = new File(this.modConfigurationDirectory, pathname);

    if (filePath.mkdirs()) {
      this.loggerService.debug(String.format("Created path: %s", filePath));
    }

    File fileJson = new File(filePath, filename);

    if (fileJson.exists() && fileJson.isFile()) {

      try {
        FileReader fileReader = new FileReader(fileJson);
        T result = this.gson.fromJson(fileReader, defaultValueClass);
        fileReader.close();
        this.loggerService.debug("Loaded json: [%s]", fileJson);
        return result;

      } catch (Exception e) {
        this.loggerService.warn("Unable to load json: [%s], trying defaults...", fileJson);
        return this.configInstantiator.instantiateKorConfigClass(defaultValueClass);
      }

    } else {
      return this._write(defaultValueClass, fileJson);
    }
  }

  @Override
  public <T extends KorConfigObject> T write(
      String pathname,
      String filename,
      Class<T> defaultValueClass
  ) {

    File filePath = new File(this.modConfigurationDirectory, pathname);

    if (filePath.mkdirs()) {
      this.loggerService.debug(String.format("Created path: %s", filePath));
    }

    File fileJson = new File(filePath, filename);

    return this._write(defaultValueClass, fileJson);
  }

  private <T extends KorConfigObject> T _write(Class<T> defaultValueClass, File fileJson) {

    try {
      T defaultValue = this.configInstantiator.instantiateKorConfigClass(defaultValueClass);
      FileWriter fileWriter = new FileWriter(fileJson);
      this.gson.toJson(defaultValue, fileWriter);
      fileWriter.close();
      this.loggerService.debug("Generated json: [%s]", fileJson);
      return defaultValue;

    } catch (Exception e) {
      this.loggerService.warn("Unable to create json: [%s], trying defaults...", fileJson);
      return this.configInstantiator.instantiateKorConfigClass(defaultValueClass);
    }
  }

}
