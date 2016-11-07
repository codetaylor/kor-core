package com.sudoplay.mc.kor.core.registry.container;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sk3lls on 10/27/2016.
 */
public class RegistryContainer<R> implements
    IRegistryContainer<R> {

  private Map<Class<?>, R> itemMap;

  public RegistryContainer() {
    this.itemMap = new HashMap<>();
  }

  @Override
  public void register(R registerable) {
    Class<?> aClass = registerable.getClass();
    R existingItem = this.itemMap.get(aClass);

    if (existingItem != null) {
      throw new RuntimeException("Attempted to register duplicate class: " + aClass);
    }

    this.itemMap.put(aClass, registerable);
  }

  @Override
  public <E extends R> E get(Class<E> registerableClass) {
    //noinspection unchecked
    return (E) this.itemMap.get(registerableClass);
  }

}
