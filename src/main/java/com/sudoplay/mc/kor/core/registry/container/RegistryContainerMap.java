package com.sudoplay.mc.kor.core.registry.container;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by sk3lls on 10/29/2016.
 */
public class RegistryContainerMap {

  private Map<Class<?>,
      RegistryContainer<?>> registryMap;

  public RegistryContainerMap() {
    this.registryMap = new LinkedHashMap<>();
  }

  public void createRegistryFor(
      Class<?> registerableClass
  ) {
    RegistryContainer<?> registryContainer = new RegistryContainer<>();
    this.registryMap.put(registerableClass, registryContainer);
  }

  public <R> RegistryContainer<R> getRegistry(
      Class<?> registryClass
  ) {

    RegistryContainer<R> registryContainer = null;

    for (Class<?> registerableClass : this.registryMap.keySet()) {

      if (registerableClass.isAssignableFrom(registryClass)) {
        //noinspection unchecked
        registryContainer = (RegistryContainer<R>) this.registryMap.get(registerableClass);
        break;
      }
    }

    boolean modRegistryNotFound = registryContainer == null;

    if (modRegistryNotFound) {
      throw new RuntimeException("Registry not found for class: " + registryClass);
    }

    return registryContainer;
  }
}
