package com.sudoplay.mc.kor.spi.event.internal;

import com.sudoplay.mc.kor.core.config.json.IJsonConfigService;
import com.sudoplay.mc.kor.core.config.text.IConfigurationService;
import com.sudoplay.mc.kor.core.registry.service.IRegistryService;

/**
 * Created by codetaylor on 11/7/2016.
 */
public class OnLoadConfigurationsEvent {

  private IRegistryService registryService;
  private IConfigurationService configurationService;
  private IJsonConfigService jsonService;

  public OnLoadConfigurationsEvent(
      IRegistryService registryService,
      IConfigurationService configurationService,
      IJsonConfigService jsonService
  ) {
    this.registryService = registryService;
    this.configurationService = configurationService;
    this.jsonService = jsonService;
  }

  public IConfigurationService getConfigurationService() {
    return this.configurationService;
  }

  public IJsonConfigService getJsonService() {
    return this.jsonService;
  }

  public IRegistryService getRegistryService() {
    return registryService;
  }
}
