package com.sudoplay.mc.kor.spi.registry.provider;

import com.sudoplay.mc.kor.spi.registry.strategy.KorRegistrationStrategy;

/**
 * Created by codetaylor on 11/1/2016.
 */
public interface KorRegistrationStrategyProvider {

  KorRegistrationStrategy getRegistrationStrategy();
}
