package com.sudoplay.mc.kor.spi.event.internal;

import com.sudoplay.mc.kor.core.config.json.IJsonConfigService;
import com.sudoplay.mc.kor.core.registry.service.IRegistryService;

/**
 * Created by codetaylor on 11/7/2016.
 */
public class OnRegisterRecipesEvent
    extends KorRegistrationEvent {

  private IJsonConfigService configService;

  public OnRegisterRecipesEvent(
      IRegistryService registryService,
      IJsonConfigService configService
  ) {

    super(registryService);
    this.configService = configService;
  }

  public IJsonConfigService getConfigService() {

    return configService;
  }
}
