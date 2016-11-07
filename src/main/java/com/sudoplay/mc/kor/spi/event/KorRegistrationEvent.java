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

  public KorRegistrationEvent(IRegistryService registryService) {
    this.registryService = registryService;
  }

  public IRegistryService getRegistryService() {
    return registryService;
  }

  public static class OnRegisterWorldGenEvent extends
      KorRegistrationEvent {

    public OnRegisterWorldGenEvent(IRegistryService registryService) {
      super(registryService);
    }
  }

  public static class OnRegisterRecipesEvent extends
      KorRegistrationEvent {

    public OnRegisterRecipesEvent(IRegistryService registryService) {
      super(registryService);
    }
  }

  public static class OnRegisterMaterialsEvent extends
      KorRegistrationEvent {

    public OnRegisterMaterialsEvent(IRegistryService registryService) {
      super(registryService);
    }
  }

  public static class OnRegisterItemsEvent extends
      KorRegistrationEvent {

    public OnRegisterItemsEvent(IRegistryService registryService) {
      super(registryService);
    }
  }

  public static class OnRegisterCreativeTabsEvent extends
      KorRegistrationEvent {

    public OnRegisterCreativeTabsEvent(IRegistryService registryService) {
      super(registryService);
    }
  }

  public static class OnRegisterBlocksEvent extends
      KorRegistrationEvent {

    public OnRegisterBlocksEvent(IRegistryService registryService) {
      super(registryService);
    }
  }

  public static class OnLoadConfigurationsEvent {
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
}
