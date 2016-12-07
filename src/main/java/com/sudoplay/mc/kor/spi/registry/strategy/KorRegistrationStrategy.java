package com.sudoplay.mc.kor.spi.registry.strategy;

import com.sudoplay.mc.kor.core.registry.service.IRegistryService;
import com.sudoplay.mc.kor.spi.Kor;

/**
 * Created by codetaylor on 10/30/2016.
 */
public interface KorRegistrationStrategy {

  void onRegistration(
      Kor kor,
      IRegistryService registryService
  );
}
