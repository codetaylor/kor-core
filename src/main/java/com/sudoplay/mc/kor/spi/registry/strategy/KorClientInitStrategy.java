package com.sudoplay.mc.kor.spi.registry.strategy;

import com.sudoplay.mc.kor.spi.Kor;
import com.sudoplay.mc.kor.spi.block.KorSubTypedBlock;
import com.sudoplay.mc.kor.spi.item.ISubType;
import com.sudoplay.mc.kor.spi.item.KorSubTypedItem;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

/**
 * Created by sk3lls on 10/30/2016.
 */
public interface KorClientInitStrategy {

  void onClientInit(
      Kor mod
  );

  static SubTypedItem createSubTypedItemStrategy(Item item) {
    return new SubTypedItem(item);
  }

  class SubTypedItem implements
      KorClientInitStrategy {

    private Item item;
    private ISubType[] subTypes;

    SubTypedItem(Item item) {

      if (!(item instanceof KorSubTypedItem)) {
        throw new RuntimeException("SubTypedItem strategy requires item to extend KorSubTypedItem");
      }
      this.item = item;
      this.item.setHasSubtypes(true);
      this.subTypes = ((KorSubTypedItem) item).getSubTypes();
    }

    @Override
    public void onClientInit(Kor mod) {
      ResourceLocation[] resourceLocations;

      { // get resource locations for subtypes
        String name;
        int arrayLength;
        ISubType subType;

        arrayLength = this.subTypes.length;
        resourceLocations = new ResourceLocation[arrayLength];

        for (int i = 0; i < arrayLength; ++i) {
          subType = this.subTypes[i];
          name = this.item.getRegistryName().getResourcePath() + "_" + subType.getName();
          resourceLocations[i] = new ResourceLocation(mod.getModId(), name);
        }
      }

      ModelBakery.registerItemVariants(this.item, resourceLocations);
    }
  }

  static SubTypedBlock createSubTypedBlockStrategy(Block block) {
    return new SubTypedBlock(block);
  }

  class SubTypedBlock implements
      KorClientInitStrategy {

    private Block block;
    private ISubType[] subTypes;

    SubTypedBlock(Block block) {

      if (!(block instanceof KorSubTypedBlock)) {
        throw new RuntimeException("SubTypedBlock strategy requires item to extend KorSubTypedItem");
      }

      this.block = block;
      this.subTypes = ((KorSubTypedBlock) block).getSubTypes();
    }

    @Override
    public void onClientInit(Kor mod) {
      String name;
      ResourceLocation[] resourceLocations;
      int arrayLength;
      ISubType subType;
      String blockName;

      arrayLength = this.subTypes.length;
      resourceLocations = new ResourceLocation[arrayLength];
      blockName = this.block.getRegistryName().getResourcePath();

      for (int i = 0; i < arrayLength; ++i) {
        subType = this.subTypes[i];
        name = blockName + "_" + subType.getName();
        resourceLocations[i] = new ResourceLocation(mod.getModId(), name);
      }

      Item item = Item.getItemFromBlock(this.block);

      if (item == null) {
        throw new RuntimeException("Can't get item from block: " + blockName);
      }

      ModelBakery.registerItemVariants(item, resourceLocations);
    }
  }
}
