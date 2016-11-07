package com.sudoplay.mc.kor.spi.registry.strategy;

import com.sudoplay.mc.kor.spi.Kor;
import com.sudoplay.mc.kor.spi.block.KorSubTypedBlock;
import com.sudoplay.mc.kor.spi.item.ISubType;
import com.sudoplay.mc.kor.spi.item.KorSubTypedItem;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

/**
 * Created by sk3lls on 10/30/2016.
 */
public interface KorClientPreInitStrategy {

  void onClientPreInit(
      Kor mod
  );

  static BasicItem createBasicItemStrategy(Item item) {
    return new BasicItem(item);
  }

  class BasicItem implements
      KorClientPreInitStrategy {

    private Item item;

    BasicItem(Item item) {
      this.item = item;
    }

    @Override
    public void onClientPreInit(Kor mod) {
      ResourceLocation resourceLocation = this.item.getRegistryName();
      ModelResourceLocation modelResourceLocation = new ModelResourceLocation(resourceLocation, "inventory");
      ModelLoader.setCustomModelResourceLocation(this.item, 0, modelResourceLocation);
    }
  }

  static BasicBlock createBasicBlockStrategy(Block block) {
    return new BasicBlock(block);
  }

  class BasicBlock implements
      KorClientPreInitStrategy {

    private Block block;

    BasicBlock(Block block) {
      this.block = block;
    }

    @Override
    public void onClientPreInit(Kor mod) {
      Item item = Item.getItemFromBlock(this.block);
      createBasicItemStrategy(item).onClientPreInit(mod);
    }
  }

  static SubTypedBlock createSubTypedBlockStrategy(Block block) {
    return new SubTypedBlock(block);
  }

  class SubTypedBlock implements
      KorClientPreInitStrategy {

    private Block block;

    public SubTypedBlock(Block block) {

      if (!(block instanceof KorSubTypedBlock)) {
        throw new IllegalArgumentException("EnumBlock strategy requires block to implement KorSubTypedBlock");
      }
      this.block = block;
    }

    @Override
    public void onClientPreInit(Kor mod) {
      String modId;
      int meta;
      String subBlockName;
      String blockName;

      modId = mod.getModId();

      for (ISubType subType : ((KorSubTypedBlock) this.block).getSubTypes()) {
        meta = subType.getMeta();
        blockName = this.block.getRegistryName().getResourcePath();
        subBlockName = blockName + "_" + subType.getName();

        ResourceLocation resourceLocation = new ResourceLocation(modId, subBlockName);
        ModelResourceLocation modelResourceLocation = new ModelResourceLocation(resourceLocation, "inventory");
        Item itemFromBlock = Item.getItemFromBlock(this.block);

        if (itemFromBlock != null) {
          ModelLoader.setCustomModelResourceLocation(itemFromBlock, meta, modelResourceLocation);

        } else {
          throw new RuntimeException("Can't get item from block: " + subBlockName);
        }
      }
    }
  }

  static SubTypedItem createSubTypedItemStrategy(Item item) {
    return new SubTypedItem(item);
  }

  class SubTypedItem implements
      KorClientPreInitStrategy {

    private Item item;
    private ISubType[] subTypes;

    SubTypedItem(Item item) {

      if (!(item instanceof KorSubTypedItem)) {
        throw new RuntimeException("SubTypedItemRegisterRendersStrategy requires item to extend KorSubTypedItem");
      }
      this.item = item;
      this.subTypes = ((KorSubTypedItem) item).getSubTypes();
    }

    @Override
    public void onClientPreInit(Kor mod) {
      String modId;
      int meta;
      String subItemName;
      String itemName;

      modId = mod.getModId();

      for (ISubType subType : this.subTypes) {
        meta = subType.getMeta();
        itemName = this.item.getRegistryName().getResourcePath();
        subItemName = itemName + "_" + subType.getName();

        ResourceLocation resourceLocation = new ResourceLocation(modId, subItemName);
        ModelResourceLocation modelResourceLocation = new ModelResourceLocation(resourceLocation, "inventory");
        ModelLoader.setCustomModelResourceLocation(this.item, meta, modelResourceLocation);
      }
    }
  }

}
