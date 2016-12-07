package com.sudoplay.mc.kor.spi.registry;

import com.sudoplay.mc.kor.spi.registry.provider.KorInitStrategyProvider;
import com.sudoplay.mc.kor.spi.registry.provider.KorPreInitStrategyProvider;
import com.sudoplay.mc.kor.spi.registry.provider.KorRegistrationStrategyProvider;
import com.sudoplay.mc.kor.spi.registry.strategy.KorInitStrategy;
import com.sudoplay.mc.kor.spi.registry.strategy.KorPreInitStrategy;
import com.sudoplay.mc.kor.spi.registry.strategy.KorRegistrationStrategy;

/**
 * Created by codetaylor on 11/13/2016.
 */
public abstract class KorRegistrationDelegate implements
    KorRegistrationStrategyProvider,
    KorPreInitStrategyProvider,
    KorInitStrategyProvider {

  @Override
  public KorRegistrationStrategy getRegistrationStrategy() {
    return (kor, registryService) -> {
      //
    };
  }

  @Override
  public KorPreInitStrategy getPreInitStrategy() {
    return kor -> {
      //
    };
  }

  @Override
  public KorInitStrategy getInitStrategy() {
    return kor -> {
      //
    };
  }
}
