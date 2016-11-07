package com.sudoplay.mc.kor.core.registry.service.injection.strategy.constructor;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by sk3lls on 11/5/2016.
 */
public interface IConstructorStrategy {

  boolean isValidConstructor(Constructor constructor);

  Object instantiate(Constructor constructor) throws IllegalAccessException, InvocationTargetException, InstantiationException;
}
