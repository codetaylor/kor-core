package com.sudoplay.mc.kor.spi.registry.provider;

import com.sudoplay.mc.kor.spi.registry.strategy.KorInitStrategy;

/**
 * Created by codetaylor on 11/1/2016.
 */
public interface KorInitStrategyProvider {

  KorInitStrategy getInitStrategy();
}
