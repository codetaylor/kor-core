package com.sudoplay.mc.kor.core.registry.service;


import com.sudoplay.mc.kor.core.log.LoggerService;
import com.sudoplay.mc.kor.core.registry.container.RegistryContainer;
import com.sudoplay.mc.kor.core.registry.container.RegistryContainerMap;
import com.sudoplay.mc.kor.core.registry.service.injection.RegistryObjectInjector;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by codetaylor on 10/27/2016.
 */
public class RegistryService implements
    IRegistryService {

  private final RegistryObjectInjector registryObjectInjector;
  private final LoggerService loggerService;
  private RegistryContainerMap registryContainerMap;
  private List<IRegistryServicePreRegistrationHook> preHookList;
  private List<IRegistryServicePostRegistrationHook> postHookList;

  public RegistryService(
      Class[] registerableClasses,
      RegistryObjectInjector registryObjectInjector,
      LoggerService loggerService
  ) {
    this.registryObjectInjector = registryObjectInjector;
    this.loggerService = loggerService;
    this.registryContainerMap = new RegistryContainerMap();
    this.preHookList = new ArrayList<>();
    this.postHookList = new ArrayList<>();

    for (Class<?> registerableClass : registerableClasses) {
      this.createRegistryFor(registerableClass);
    }
  }

  public void addPreRegistrationHook(IRegistryServicePreRegistrationHook callback) {
    this.preHookList.add(callback);
  }

  public void addPostRegistrationHook(IRegistryServicePostRegistrationHook callback) {
    this.postHookList.add(callback);
  }

  @Override
  public <R> R get(Class<R> registerableClass) {
    return this.getRegistry(registerableClass).get(registerableClass);
  }

  @Override
  public IRegistryService register(Object... registerableObjects) {

    for (Object registerableObject : registerableObjects) {
      this.register(registerableObject);
    }
    return this;
  }

  @Override
  public IRegistryService register(Object registerableObject) {

    boolean doRegistration = true;

    for (IRegistryServicePreRegistrationHook callback : this.preHookList) {
      doRegistration = doRegistration && callback.onPreRegister(registerableObject);
    }

    if (!doRegistration) {
      this.loggerService.debug("[skipped] %s", registerableObject);
      return this;
    }

    if (registerableObject instanceof Class) {
      Class aClass = (Class) registerableObject;

      try {
        registerableObject = this.registryObjectInjector.createInjectedObject(aClass);

      } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
        throw new RuntimeException("Failed to instantiate " + registerableObject, e);
      }
    }

    this.getRegistry(registerableObject.getClass()).register(registerableObject);

    for (IRegistryServicePostRegistrationHook callback : this.postHookList) {
      callback.onPostRegister(registerableObject);
    }
    return this;
  }

  private void createRegistryFor(Class<?> registerableClass) {
    this.registryContainerMap.createRegistryFor(registerableClass);
  }

  private <R> RegistryContainer<R> getRegistry(Class<?> registryClass) {
    return this.registryContainerMap.getRegistry(registryClass);
  }
}
