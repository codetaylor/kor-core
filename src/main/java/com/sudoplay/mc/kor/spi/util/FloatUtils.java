package com.sudoplay.mc.kor.spi.util;

/**
 * Created by sk3lls on 1/27/2017.
 */
public class FloatUtils {

  public static float HALF_PI = (float) (Math.PI / 2D);

  public static final float EPSILON = 1.1920928955078125E-7f;

  public static boolean compare(float a, float b) {
    return Math.abs(a - b) < EPSILON;
  }

}
