package com.sudoplay.mc.kor.core.registry.service.injection.strategy.constructor;

import com.sudoplay.mc.kor.core.registry.service.injection.strategy.parameter.IParameterStrategy;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by sk3lls on 11/5/2016.
 */
public class ArgConstructorStrategy implements
    IConstructorStrategy {

  private LinkedHashMap<Class<?>, IParameterStrategy> parameterProviderMap;

  public ArgConstructorStrategy(
      LinkedHashMap<Class<?>, IParameterStrategy> parameterProviderMap
  ) {
    this.parameterProviderMap = parameterProviderMap;
  }

  @Override
  public boolean isValidConstructor(Constructor constructor) {

    boolean isValid = true;

    for (Parameter parameter : constructor.getParameters()) {
      IParameterStrategy strategy = this.getParameterStrategy(parameter);

      if (strategy != null) {
        isValid = isValid && strategy.isValidParameter(parameter);

      } else {
        isValid = false;
      }
    }

    return isValid;
  }

  @Nullable
  private IParameterStrategy getParameterStrategy(@Nonnull Parameter parameter) {

    for (Class<?> aClass : this.parameterProviderMap.keySet()) {

      if (aClass.isAssignableFrom(parameter.getType())) {
        return this.parameterProviderMap.get(aClass);
      }
    }

    return null;
  }

  @Override
  public Object instantiate(Constructor constructor) throws IllegalAccessException, InvocationTargetException, InstantiationException {

    List<Object> parameterList = new ArrayList<>();

    for (Parameter parameter : constructor.getParameters()) {
      IParameterStrategy strategy = this.getParameterStrategy(parameter);
      // this has already been checked in the isValidConstructor call
      //noinspection ConstantConditions
      parameterList.add(strategy.getParameterInstance(parameter));
    }

    return constructor.newInstance(parameterList.toArray());
  }
}
