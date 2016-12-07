package com.sudoplay.mc.kor.spi.config.json.component.common;

/**
 * Created by codetaylor on 11/4/2016.
 */
public class MinMaxInt {

  private int min;
  private int max;

  public MinMaxInt(int min, int max) {
    this.min = min;
    this.max = max;
  }

  public int getMin() {
    return min;
  }

  public int getMax() {
    return max;
  }
}
