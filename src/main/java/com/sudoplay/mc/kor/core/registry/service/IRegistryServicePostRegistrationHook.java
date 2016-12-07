package com.sudoplay.mc.kor.core.registry.service;

/**
 * Created by codetaylor on 11/1/2016.
 */
public interface IRegistryServicePostRegistrationHook {

  void onPostRegister(Object registeredObject);
}
