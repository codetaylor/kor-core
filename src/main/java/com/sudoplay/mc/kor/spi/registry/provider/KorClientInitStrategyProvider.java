package com.sudoplay.mc.kor.spi.registry.provider;

import com.sudoplay.mc.kor.spi.registry.strategy.KorClientInitStrategy;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

/**
 * Created by codetaylor on 11/1/2016.
 */
public interface KorClientInitStrategyProvider {

  KorClientInitStrategy getClientInitStrategy();

  interface SubTypedItem extends
      KorClientInitStrategyProvider {

    @Override
    default KorClientInitStrategy getClientInitStrategy() {
      return KorClientInitStrategy.createSubTypedItemStrategy((Item) this);
    }
  }

  interface SubTypedBlock extends
      KorClientInitStrategyProvider {

    @Override
    default KorClientInitStrategy getClientInitStrategy() {
      return KorClientInitStrategy.createSubTypedBlockStrategy((Block) this);
    }
  }
}
