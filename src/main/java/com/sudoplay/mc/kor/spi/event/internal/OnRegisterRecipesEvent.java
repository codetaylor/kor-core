package com.sudoplay.mc.kor.spi.event.internal;

import com.sudoplay.mc.kor.core.registry.service.IRegistryService;

/**
 * Created by codetaylor on 11/7/2016.
 */
public class OnRegisterRecipesEvent extends
    KorRegistrationEvent {

  public OnRegisterRecipesEvent(IRegistryService registryService) {
    super(registryService);
  }
}
