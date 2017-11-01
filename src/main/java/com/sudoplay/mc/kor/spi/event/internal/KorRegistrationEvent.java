package com.sudoplay.mc.kor.spi.event.internal;

import com.sudoplay.mc.kor.core.registry.service.IRegistryService;

/**
 * Kor registration events occur on an internal, ordered event bus
 * exclusively for passing registration events to modules.
 * <p>
 * Created by codetaylor on 11/3/2016.
 */
public abstract class KorRegistrationEvent {

  private IRegistryService registryService;

  public KorRegistrationEvent(
      IRegistryService registryService
  ) {

    this.registryService = registryService;
  }

  public IRegistryService getRegistryService() {

    return registryService;
  }
}
