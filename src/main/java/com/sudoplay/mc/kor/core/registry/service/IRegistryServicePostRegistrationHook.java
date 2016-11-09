package com.sudoplay.mc.kor.core.registry.service;

/**
 * Created by sk3lls on 11/1/2016.
 */
public interface IRegistryServicePostRegistrationHook {

  void onPostRegister(Object registeredObject);
}
