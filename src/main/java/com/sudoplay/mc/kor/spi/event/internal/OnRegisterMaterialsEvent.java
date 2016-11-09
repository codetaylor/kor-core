package com.sudoplay.mc.kor.spi.event.internal;

import com.sudoplay.mc.kor.core.registry.service.IRegistryService;

/**
 * Created by sk3lls on 11/7/2016.
 */
public class OnRegisterMaterialsEvent extends
    KorRegistrationEvent {

  public OnRegisterMaterialsEvent(IRegistryService registryService) {
    super(registryService);
  }
}
