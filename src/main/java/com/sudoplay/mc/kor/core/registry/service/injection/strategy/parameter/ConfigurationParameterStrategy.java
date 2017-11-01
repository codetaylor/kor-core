package com.sudoplay.mc.kor.core.registry.service.injection.strategy.parameter;

import com.sudoplay.mc.kor.core.config.text.IConfigurationService;
import com.sudoplay.mc.kor.spi.registry.injection.KorTextConfig;
import net.minecraftforge.common.config.Configuration;

import java.io.File;
import java.lang.reflect.Parameter;

/**
 * Created by codetaylor on 11/5/2016.
 */
public class ConfigurationParameterStrategy
    implements IParameterStrategy<Configuration> {

  private IConfigurationService service;

  public ConfigurationParameterStrategy(
      IConfigurationService service
  ) {

    this.service = service;
  }

  @Override
  public boolean isValidParameter(Parameter parameter) {

    KorTextConfig annotation = parameter.getAnnotation(KorTextConfig.class);
    boolean hasCorrectAnnotation = annotation != null;
    boolean isCorrectType = Configuration.class.isAssignableFrom(parameter.getType());
    return hasCorrectAnnotation && isCorrectType;
  }

  @Override
  public Configuration getParameterInstance(Parameter parameter) {

    KorTextConfig annotation = parameter.getAnnotation(KorTextConfig.class);
    String file = annotation.file();
    String path = annotation.path();

    String filename = (path.length() > 0) ? new File(path, file).getPath() : new File(file).getPath();

    Configuration configuration = this.service.get(filename);

    if (configuration == null) {
      // config wasn't loaded
      throw new IllegalStateException(String.format(
          "Unable to inject configuration file [%s] because it hasn't been loaded, " +
              "make sure it is loaded during the module's OnLoadConfigurationsEvent event.",
          filename
      ));
    }

    return configuration;
  }
}
