package com.sudoplay.mc.kor.core.registry.service.injection.strategy.parameter;

import com.sudoplay.mc.kor.spi.Kor;

import java.lang.reflect.Parameter;

/**
 * Created by sk3lls on 11/5/2016.
 */
public class KorParameterStrategy implements
    IParameterStrategy<Kor> {

  private Kor kor;

  public KorParameterStrategy(Kor kor) {
    this.kor = kor;
  }

  @Override
  public boolean isValidParameter(Parameter parameter) {
    return Kor.class.isAssignableFrom(parameter.getType());
  }

  @Override
  public Kor getParameterInstance(Parameter parameter) {
    return this.kor;
  }
}
