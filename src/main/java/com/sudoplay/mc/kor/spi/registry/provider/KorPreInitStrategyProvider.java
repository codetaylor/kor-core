package com.sudoplay.mc.kor.spi.registry.provider;

import com.sudoplay.mc.kor.spi.registry.strategy.KorPreInitStrategy;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

/**
 * Created by sk3lls on 11/1/2016.
 */
public interface KorPreInitStrategyProvider {

  KorPreInitStrategy getPreInitStrategy();

  interface BasicItem extends KorPreInitStrategyProvider {

    @Override
    default KorPreInitStrategy getPreInitStrategy() {
      return KorPreInitStrategy.createBasicItemStrategy((Item) this);
    }
  }

  interface BasicBlock extends KorPreInitStrategyProvider {

    @Override
    default KorPreInitStrategy getPreInitStrategy() {
      return KorPreInitStrategy.createBasicBlockStrategy((Block) this);
    }
  }

  interface SubTypedItem extends KorPreInitStrategyProvider {

    @Override
    default KorPreInitStrategy getPreInitStrategy() {
      return KorPreInitStrategy.createSubTypedItemStrategy((Item) this);
    }
  }

  interface SubTypedBlock extends KorPreInitStrategyProvider {

    @Override
    default KorPreInitStrategy getPreInitStrategy() {
      return KorPreInitStrategy.createSubTypedBlockStrategy((Block) this);
    }
  }
}
