package com.sudoplay.mc.kor.spi.registry;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * Created by sk3lls on 11/11/2016.
 */
public interface KorOreDictionaryEntryProvider {

  /**
   * Provides the system with a list of ore dictionary entries.
   * <p>
   * All ore dictionary entries for sub-typed items and blocks should be
   * added to the incoming list. The entries will be filtered against valid
   * subtypes before registration.
   *
   * @param store the list to add to
   * @return the list added to
   */
  @Nonnull
  List<KorOreDictionaryEntry> getKorOreDictionaryEntries(@Nonnull List<KorOreDictionaryEntry> store);
}
