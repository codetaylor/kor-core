package com.sudoplay.mc.kor.core.registry.service.injection.strategy.parameter;

import com.sudoplay.mc.kor.core.config.text.ITextConfigService;
import com.sudoplay.mc.kor.core.config.text.TextConfigData;
import com.sudoplay.mc.kor.spi.registry.injection.KorTextConfig;

import java.io.File;
import java.lang.reflect.Parameter;

/**
 * Created by sk3lls on 11/5/2016.
 */
public class TextConfigParameterStrategy implements
    IParameterStrategy<TextConfigData> {

  private ITextConfigService service;

  public TextConfigParameterStrategy(
      ITextConfigService service
  ) {
    this.service = service;
  }

  @Override
  public boolean isValidParameter(Parameter parameter) {
    KorTextConfig annotation = parameter.getAnnotation(KorTextConfig.class);
    boolean hasCorrectAnnotation = annotation != null;
    boolean isCorrectType = TextConfigData.class.isAssignableFrom(parameter.getType());
    return hasCorrectAnnotation && isCorrectType;
  }

  @Override
  public TextConfigData getParameterInstance(Parameter parameter) {
    KorTextConfig annotation = parameter.getAnnotation(KorTextConfig.class);
    String file = annotation.file();
    String path = annotation.path();

    String filename = (path.length() > 0) ? new File(path, file).getPath() : new File(file).getPath();

    TextConfigData textConfigData = this.service.get(filename);

    if (textConfigData == null) {
      // config wasn't loaded
      throw new IllegalStateException(String.format(
          "Unable to inject text config file [%s] because it hasn't been loaded, " +
              "make sure it is loaded during the module's OnLoadConfigurationsEvent event.",
          filename
      ));
    }

    return textConfigData;
  }
}
