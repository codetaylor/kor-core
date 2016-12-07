package com.sudoplay.mc.kor.core.registry.service.injection.strategy.parameter;

import java.lang.reflect.Parameter;

/**
 * Created by codetaylor on 11/5/2016.
 */
public interface IParameterStrategy<T> {

  boolean isValidParameter(Parameter parameter);

  T getParameterInstance(Parameter parameter);
}
