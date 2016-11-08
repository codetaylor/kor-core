package com.sudoplay.mc.kor.spi.event;

import com.sudoplay.mc.kor.core.config.json.IConfigService;
import com.sudoplay.mc.kor.core.config.text.ITextConfigService;
import com.sudoplay.mc.kor.core.registry.service.IRegistryService;

/**
 * Kor registration events occur on an internal, ordered event bus
 * exclusively for passing registration events to modules.
 * <p>
 * Created by sk3lls on 11/3/2016.
 */
public abstract class KorRegistrationEvent {

  private IRegistryService registryService;
  private ITextConfigService configurationService;
  private IConfigService jsonService;

  public KorRegistrationEvent(
      IRegistryService registryService,
      ITextConfigService configurationService,
      IConfigService jsonService
  ) {
    this.registryService = registryService;
    this.configurationService = configurationService;
    this.jsonService = jsonService;
  }

  public IRegistryService getRegistryService() {
    return registryService;
  }

  public IConfigService getJsonService() {
    return jsonService;
  }

  public ITextConfigService getConfigurationService() {
    return configurationService;
  }
}
