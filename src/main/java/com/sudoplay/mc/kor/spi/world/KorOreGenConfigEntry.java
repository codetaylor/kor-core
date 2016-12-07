package com.sudoplay.mc.kor.spi.world;

import com.sudoplay.mc.kor.spi.config.json.KorConfigObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by codetaylor on 11/4/2016.
 */
public class KorOreGenConfigEntry extends
    KorConfigObject {

  private int modGenerationWeight = 0;
  private List<DimensionProfile> dimensionProfileList = new ArrayList<>();

  @SuppressWarnings("unused")
  private KorOreGenConfigEntry() {
    // serialization
  }

  public KorOreGenConfigEntry(
      int modGenerationWeight,
      DimensionProfile... dimensionProfiles
  ) {
    this.modGenerationWeight = modGenerationWeight;

    Collections.addAll(this.dimensionProfileList, dimensionProfiles);
  }

  public int getModGenerationWeight() {
    return modGenerationWeight;
  }

  public List<DimensionProfile> getDimensionProfileList() {
    return dimensionProfileList;
  }
}
