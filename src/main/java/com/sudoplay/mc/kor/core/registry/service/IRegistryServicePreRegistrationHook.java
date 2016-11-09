package com.sudoplay.mc.kor.core.registry.service;

/**
 * Created by sk3lls on 11/1/2016.
 */
public interface IRegistryServicePreRegistrationHook {

  /**
   * Returns true if the object should be registered.
   *
   * @param object the object
   * @return true if the object should be registered
   */
  boolean onPreRegister(Object object);
}
