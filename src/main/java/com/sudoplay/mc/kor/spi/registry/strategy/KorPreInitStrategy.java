package com.sudoplay.mc.kor.spi.registry.strategy;

import com.sudoplay.mc.kor.spi.Kor;
import com.sudoplay.mc.kor.spi.item.KorSubTypedItemBlock;
import com.sudoplay.mc.kor.spi.block.KorSubTypedBlock;
import com.sudoplay.mc.kor.spi.item.KorSubTypedItem;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by sk3lls on 10/30/2016.
 */
public interface KorPreInitStrategy {

  void onPreInit(
      Kor mod
  );

  static BasicBlock createBasicBlockStrategy(Block block) {
    return new BasicBlock(block);
  }

  class BasicBlock implements
      KorPreInitStrategy {

    private Block block;

    BasicBlock(Block block) {
      this.block = block;
    }

    @Override
    public void onPreInit(Kor mod) {
      ItemBlock itemBlock;

      itemBlock = new ItemBlock(this.block);
      itemBlock.setRegistryName(this.block.getRegistryName());
      GameRegistry.register(this.block);
      GameRegistry.register(itemBlock);
    }
  }

  static SubTypedBlock createSubTypedBlockStrategy(Block block) {
    return new SubTypedBlock(block);
  }

  class SubTypedBlock implements
      KorPreInitStrategy {

    private Block block;

    SubTypedBlock(Block block) {

      if (!(block instanceof KorSubTypedBlock)) {
        throw new IllegalArgumentException("EnumBlock strategy requires block to extend KorSubTypedBlock");
      }
      this.block = block;
    }

    @Override
    public void onPreInit(Kor mod) {
      KorSubTypedItemBlock korSubTypedItemBlock;

      korSubTypedItemBlock = new KorSubTypedItemBlock(this.block);
      korSubTypedItemBlock.setRegistryName(this.block.getRegistryName());
      GameRegistry.register(this.block);
      GameRegistry.register(korSubTypedItemBlock);
    }
  }

  static BasicItem createBasicItemStrategy(Item item) {
    return new BasicItem(item);
  }

  class BasicItem implements
      KorPreInitStrategy {

    private Item item;

    BasicItem(Item item) {
      this.item = item;
    }

    @Override
    public void onPreInit(Kor mod) {
      GameRegistry.register(this.item);
    }
  }

  static SubTypedItem createSubTypedItemStrategy(Item item) {
    return new SubTypedItem(item);
  }

  class SubTypedItem implements
      KorPreInitStrategy {

    private Item item;

    SubTypedItem(Item item) {

      if (!(item instanceof KorSubTypedItem)) {
        throw new RuntimeException("SubTypedItemRegisterStrategy requires item to implement ISubTypedItem");
      }
      this.item = item;
    }

    @Override
    public void onPreInit(Kor mod) {
      GameRegistry.register(this.item);
    }
  }
}
