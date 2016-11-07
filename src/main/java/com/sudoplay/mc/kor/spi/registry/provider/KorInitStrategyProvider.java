package com.sudoplay.mc.kor.spi.registry.provider;

import com.sudoplay.mc.kor.spi.registry.strategy.KorInitStrategy;

/**
 * Created by sk3lls on 11/1/2016.
 */
public interface KorInitStrategyProvider {

  KorInitStrategy getInitStrategy();
}
