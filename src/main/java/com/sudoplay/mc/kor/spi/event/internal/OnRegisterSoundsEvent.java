package com.sudoplay.mc.kor.spi.event.internal;

import com.sudoplay.mc.kor.core.registry.service.IRegistryService;

/**
 * Created by codetaylor on 1/29/2017.
 */
public class OnRegisterSoundsEvent extends
    KorRegistrationEvent {

  public OnRegisterSoundsEvent(IRegistryService registryService) {
    super(registryService);
  }
}
