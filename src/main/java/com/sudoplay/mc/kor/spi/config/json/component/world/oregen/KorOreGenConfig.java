package com.sudoplay.mc.kor.spi.config.json.component.world.oregen;

import com.sudoplay.mc.kor.spi.config.json.KorConfigObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by sk3lls on 11/4/2016.
 */
public abstract class KorOreGenConfig extends
    KorConfigObject {

  private int modGenerationWeight = 0;
  private List<DimensionProfile> dimensionProfileList = new ArrayList<>();

  public KorOreGenConfig(
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
