package com.sudoplay.mc.kor.spi.config.json.component.smelting;

import com.sudoplay.mc.kor.spi.config.json.KorConfigObject;

/**
 * Created by sk3lls on 11/12/2016.
 */
public class ConfigSmeltingEntry extends
    KorConfigObject {

  private float xp;
  private int quantity;

  public ConfigSmeltingEntry(float xp, int quantity) {
    this.xp = xp;
    this.quantity = quantity;
  }

  public float getXp() {
    return xp;
  }

  public int getQuantity() {
    return quantity;
  }
}
