package com.sudoplay.mc.kor.spi.registry.provider;

import com.sudoplay.mc.kor.spi.fluid.KorFluidRegistrationContainer;
import com.sudoplay.mc.kor.spi.registry.strategy.KorClientPreInitStrategy;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

/**
 * Created by codetaylor on 11/1/2016.
 */
public interface KorClientPreInitStrategyProvider {

  KorClientPreInitStrategy getClientPreInitStrategy();

  interface BasicItem extends KorClientPreInitStrategyProvider {

    @Override
    default KorClientPreInitStrategy getClientPreInitStrategy() {
      return KorClientPreInitStrategy.createBasicItemStrategy((Item) this);
    }
  }

  interface BasicBlock extends KorClientPreInitStrategyProvider {

    @Override
    default KorClientPreInitStrategy getClientPreInitStrategy() {
      return KorClientPreInitStrategy.createBasicBlockStrategy((Block) this);
    }
  }

  interface SubTypedItem extends KorClientPreInitStrategyProvider {

    @Override
    default KorClientPreInitStrategy getClientPreInitStrategy() {
      return KorClientPreInitStrategy.createSubTypedItemStrategy((Item) this);
    }
  }

  interface SubTypedBlock extends KorClientPreInitStrategyProvider {

    @Override
    default KorClientPreInitStrategy getClientPreInitStrategy() {
      return KorClientPreInitStrategy.createSubTypedBlockStrategy((Block) this);
    }
  }

  interface BasicFluid extends KorClientPreInitStrategyProvider {

    @Override
    default KorClientPreInitStrategy getClientPreInitStrategy() {
      return KorClientPreInitStrategy.createBasicFluidStrategy((KorFluidRegistrationContainer) this);
    }
  }

}
