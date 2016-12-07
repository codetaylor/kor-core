package com.sudoplay.mc.kor.spi.registry.strategy;

import com.sudoplay.mc.kor.spi.Kor;

/**
 * Created by codetaylor on 10/30/2016.
 */
public interface KorInitStrategy {

  void onInit(
      Kor kor
  );
}
