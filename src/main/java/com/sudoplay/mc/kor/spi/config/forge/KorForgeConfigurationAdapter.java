package com.sudoplay.mc.kor.spi.config.forge;

import net.minecraftforge.common.config.Configuration;

/**
 * Created by codetaylor on 10/30/2016.
 */
public interface KorForgeConfigurationAdapter<T> {

  void adapt(Configuration configuration, T modConfiguration);
}
