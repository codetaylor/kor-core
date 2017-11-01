package com.sudoplay.mc.kor.core.registry.service.injection;

import com.sudoplay.mc.kor.core.registry.service.injection.strategy.constructor.IConstructorStrategy;
import com.sudoplay.mc.kor.spi.registry.injection.KorInject;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class RegistryObjectInjector {

  private IConstructorStrategy[] strategyList;

  public RegistryObjectInjector(IConstructorStrategy[] strategyList) {

    this.strategyList = strategyList;
  }

  public Object createInjectedObject(Class aClass) throws IllegalAccessException, InstantiationException, InvocationTargetException {

    Constructor[] declaredConstructors = aClass.getDeclaredConstructors();

    for (Constructor constructor : declaredConstructors) {

      if (constructor.getParameterCount() == 0
          || constructor.getAnnotation(KorInject.class) != null) {

        for (IConstructorStrategy strategy : this.strategyList) {

          if (strategy.isValidConstructor(constructor)) {
            return this.instantiate(constructor, strategy);
          }
        }
      }
    }

    throw new RuntimeException(String.format("Couldn't find an injection strategy for %s", aClass));
  }

  private Object instantiate(
      Constructor constructor,
      IConstructorStrategy strategy
  ) throws IllegalAccessException, InvocationTargetException, InstantiationException {

    boolean wasAccessible = constructor.isAccessible();
    constructor.setAccessible(true);
    Object o = strategy.instantiate(constructor);
    constructor.setAccessible(wasAccessible);
    return o;
  }
}