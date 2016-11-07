package com.sudoplay.mc.kor.core.config.json;

import com.sudoplay.mc.kor.spi.config.json.KorConfigObject;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ConfigInstantiator {

  <T extends KorConfigObject> T instantiateKorConfigClass(Class<T> jsonDataClass) {
    T jsonDataInstance;

    try {
      Constructor<T> declaredConstructor = jsonDataClass.getDeclaredConstructor();

      boolean wasAccessible = declaredConstructor.isAccessible();
      declaredConstructor.setAccessible(true);
      jsonDataInstance = declaredConstructor.newInstance();
      declaredConstructor.setAccessible(wasAccessible);

    } catch (InstantiationException e) {
      throw new RuntimeException("Unable to instantiate config class (missing no-arg constructor?): " + jsonDataClass, e);

    } catch (IllegalAccessException e) {
      throw new RuntimeException("Unable to access config class (is it public?): " + jsonDataClass, e);

    } catch (NoSuchMethodException e) {
      throw new RuntimeException("Unable to find config constructor: " + jsonDataClass, e);

    } catch (InvocationTargetException e) {
      throw new RuntimeException("Unable to invoke config constructor: " + jsonDataClass, e);
    }
    return jsonDataInstance;
  }
}