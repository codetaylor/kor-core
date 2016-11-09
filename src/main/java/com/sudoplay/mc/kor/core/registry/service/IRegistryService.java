package com.sudoplay.mc.kor.core.registry.service;

/**
 * Created by sk3lls on 11/3/2016.
 */
public interface IRegistryService {
  <R> R get(Class<R> registerableClass);

  IRegistryService register(Object registerableObject);

  IRegistryService register(Object... registerableObjects);
}
