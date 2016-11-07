package com.sudoplay.mc.kor.core.registry.service.injection.strategy.parameter;

import com.sudoplay.mc.kor.core.config.json.IConfigService;
import com.sudoplay.mc.kor.spi.config.json.KorConfigObject;
import com.sudoplay.mc.kor.spi.registry.injection.KorConfig;

import java.io.File;
import java.lang.reflect.Parameter;

/**
 * Created by sk3lls on 11/5/2016.
 */
public class ConfigParameterStrategy implements
    IParameterStrategy<KorConfigObject> {

  private IConfigService configService;
  private File modConfigurationDirectory;

  public ConfigParameterStrategy(
      IConfigService configService,
      File modConfigurationDirectory
  ) {
    this.configService = configService;
    this.modConfigurationDirectory = modConfigurationDirectory;
  }

  @Override
  public boolean isValidParameter(Parameter parameter) {
    KorConfig annotation = parameter.getAnnotation(KorConfig.class);
    boolean hasCorrectAnnotation = annotation != null;
    boolean isCorrectType = KorConfigObject.class.isAssignableFrom(parameter.getType());
    return hasCorrectAnnotation && isCorrectType;
  }

  @Override
  public KorConfigObject getParameterInstance(Parameter parameter) {

    //noinspection unchecked
    Class<? extends KorConfigObject> type = (Class<? extends KorConfigObject>) parameter.getType();

    KorConfig annotation = parameter.getAnnotation(KorConfig.class);
    String file = annotation.file();
    String path = annotation.path();

    return this.configService.get(path, file, type);
  }
}
