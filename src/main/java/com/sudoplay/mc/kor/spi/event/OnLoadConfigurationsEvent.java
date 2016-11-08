package com.sudoplay.mc.kor.spi.event;

import com.sudoplay.mc.kor.core.config.json.IConfigService;
import com.sudoplay.mc.kor.core.config.text.ITextConfigService;

/**
 * Created by sk3lls on 11/7/2016.
 */
public class OnLoadConfigurationsEvent {
  private ITextConfigService configurationService;
  private IConfigService jsonService;

  public OnLoadConfigurationsEvent(
      ITextConfigService configurationService,
      IConfigService jsonService
  ) {
    this.configurationService = configurationService;
    this.jsonService = jsonService;
  }

  public ITextConfigService getConfigurationService() {
    return this.configurationService;
  }

  public IConfigService getJsonService() {
    return this.jsonService;
  }
}
