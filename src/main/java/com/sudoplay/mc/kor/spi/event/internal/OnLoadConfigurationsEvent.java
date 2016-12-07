package com.sudoplay.mc.kor.spi.event.internal;

import com.sudoplay.mc.kor.core.config.json.IConfigService;
import com.sudoplay.mc.kor.core.config.text.ITextConfigService;
import com.sudoplay.mc.kor.core.registry.service.IRegistryService;

/**
 * Created by codetaylor on 11/7/2016.
 */
public class OnLoadConfigurationsEvent {

  private IRegistryService registryService;
  private ITextConfigService configurationService;
  private IConfigService jsonService;

  public OnLoadConfigurationsEvent(
      IRegistryService registryService,
      ITextConfigService configurationService,
      IConfigService jsonService
  ) {
    this.registryService = registryService;
    this.configurationService = configurationService;
    this.jsonService = jsonService;
  }

  public ITextConfigService getConfigurationService() {
    return this.configurationService;
  }

  public IConfigService getJsonService() {
    return this.jsonService;
  }

  public IRegistryService getRegistryService() {
    return registryService;
  }
}
