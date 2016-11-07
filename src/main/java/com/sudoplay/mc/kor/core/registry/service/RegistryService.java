package com.sudoplay.mc.kor.core.registry.service;


import com.sudoplay.mc.kor.core.registry.container.RegistryContainer;
import com.sudoplay.mc.kor.core.registry.container.RegistryContainerMap;
import com.sudoplay.mc.kor.core.registry.service.injection.RegistryObjectInjector;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sk3lls on 10/27/2016.
 */
public class RegistryService implements
    IRegistryService {

  private final RegistryObjectInjector registryObjectInjector;
  private RegistryContainerMap registryContainerMap;
  private List<IRegistryServiceCallback> callbackList;

  public RegistryService(
      Class[] registerableClasses,
      RegistryObjectInjector registryObjectInjector
  ) {
    this.registryObjectInjector = registryObjectInjector;
    this.registryContainerMap = new RegistryContainerMap();
    this.callbackList = new ArrayList<>();

    for (Class<?> registerableClass : registerableClasses) {
      this.createModRegistryFor(registerableClass);
    }
  }

  public void addCallback(IRegistryServiceCallback callback) {
    this.callbackList.add(callback);
  }

  @Override
  public <R> R get(Class<R> registerableClass) {
    return this.getModRegistry(registerableClass).get(registerableClass);
  }

  @Override
  public IRegistryService register(Object registerableObject) {

    if (registerableObject instanceof Class) {
      Class aClass = (Class) registerableObject;

      try {
        registerableObject = this.registryObjectInjector.createInjectedObject(aClass);

      } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
        throw new RuntimeException("Failed to instantiate " + registerableObject, e);
      }
    }

    this.getModRegistry(registerableObject.getClass()).register(registerableObject);

    for (IRegistryServiceCallback callback : this.callbackList) {
      callback.onRegister(registerableObject);
    }
    return this;
  }

  private void createModRegistryFor(Class<?> registerableClass) {
    this.registryContainerMap.createRegistryFor(registerableClass);
  }

  private <R> RegistryContainer<R> getModRegistry(Class<?> registryClass) {
    return this.registryContainerMap.getRegistry(registryClass);
  }
}
