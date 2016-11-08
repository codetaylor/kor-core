package com.sudoplay.mc.kor.spi.event;

import com.sudoplay.mc.kor.core.config.json.IConfigService;
import com.sudoplay.mc.kor.core.config.text.ITextConfigService;
import com.sudoplay.mc.kor.core.registry.service.IRegistryService;

/**
 * Created by sk3lls on 11/7/2016.
 */
public class OnRegisterRecipesEvent extends
    KorRegistrationEvent {

  public OnRegisterRecipesEvent(IRegistryService registryService, ITextConfigService textConfigService, IConfigService configService) {
    super(registryService, textConfigService, configService);
  }
}
