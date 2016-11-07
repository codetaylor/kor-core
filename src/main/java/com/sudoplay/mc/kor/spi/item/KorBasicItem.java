package com.sudoplay.mc.kor.spi.item;

import com.sudoplay.mc.kor.spi.registry.provider.KorClientPreInitStrategyProvider;
import com.sudoplay.mc.kor.spi.registry.provider.KorPreInitStrategyProvider;

/**
 * Created by sk3lls on 11/3/2016.
 */
public abstract class KorBasicItem extends
    KorItemTooltip implements
    KorPreInitStrategyProvider.BasicItem,
    KorClientPreInitStrategyProvider.BasicItem {

  public KorBasicItem(String modId, String name) {
    super(modId, name);
  }
}
