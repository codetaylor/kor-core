package com.sudoplay.mc.kor.spi.event.internal;

import com.sudoplay.mc.kor.core.registry.service.IRegistryService;

/**
 * Created by codetaylor on 12/8/2016.
 */
public class OnRegisterEventHandlersEvent extends
    KorRegistrationEvent {

  public OnRegisterEventHandlersEvent(IRegistryService registryService) {
    super(registryService);
  }
}
