package com.sudoplay.mc.kor.spi.item;

import com.sudoplay.mc.kor.spi.registry.KorOreDictionaryEntry;
import com.sudoplay.mc.kor.spi.registry.KorOreDictionaryEntryProvider;
import com.sudoplay.mc.kor.spi.registry.provider.KorClientPreInitStrategyProvider;
import com.sudoplay.mc.kor.spi.registry.provider.KorPreInitStrategyProvider;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * Created by codetaylor on 11/3/2016.
 */
public abstract class KorBasicItem extends
    KorItemTooltip implements
    KorOreDictionaryEntryProvider,
    KorPreInitStrategyProvider.BasicItem,
    KorClientPreInitStrategyProvider.BasicItem {

  public KorBasicItem(String modId, String name) {
    super(modId, name);
  }

  @Nonnull
  @Override
  public List<KorOreDictionaryEntry> getKorOreDictionaryEntries(@Nonnull List<KorOreDictionaryEntry> store) {
    return store;
  }
}
