package com.sudoplay.mc.kor.spi.registry.strategy;

import com.sudoplay.mc.kor.spi.Kor;
import com.sudoplay.mc.kor.spi.block.IKorTileEntityProvider;
import com.sudoplay.mc.kor.spi.block.KorSubTypedEnumBlock;
import com.sudoplay.mc.kor.spi.item.ISubType;
import com.sudoplay.mc.kor.spi.item.KorSubTypedItem;
import com.sudoplay.mc.kor.spi.item.KorSubTypedItemBlock;
import com.sudoplay.mc.kor.spi.registry.KorOreDictionaryEntry;
import com.sudoplay.mc.kor.spi.registry.KorOreDictionaryEntryProvider;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by codetaylor on 10/30/2016.
 */
public interface KorPreInitStrategy {

  void onPreInit(
      Kor kor
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
      ResourceLocation registryName;

      registryName = this.block.getRegistryName();
      itemBlock = new ItemBlock(this.block);
      itemBlock.setRegistryName(registryName);
      GameRegistry.register(this.block);
      GameRegistry.register(itemBlock);

      if (this.block instanceof KorOreDictionaryEntryProvider) {
        List<KorOreDictionaryEntry> korOreDictionaryEntries;

        korOreDictionaryEntries = new ArrayList<>();
        ((KorOreDictionaryEntryProvider) this.block).getKorOreDictionaryEntries(korOreDictionaryEntries);

        for (KorOreDictionaryEntry entry : korOreDictionaryEntries) {
          int meta = entry.getMeta();
          String name = entry.getName();
          OreDictionary.registerOre(name, new ItemStack(this.block, 1, meta));
        }
      }

      if (this.block instanceof IKorTileEntityProvider) {
        IKorTileEntityProvider tileEntityProvider = (IKorTileEntityProvider) this.block;
        GameRegistry.registerTileEntity(
            tileEntityProvider.getTileEntityClass(),
            tileEntityProvider.getTileEntityName()
        );
      }
    }
  }

  static SubTypedBlock createSubTypedBlockStrategy(Block block) {
    return new SubTypedBlock(block);
  }

  class SubTypedBlock implements
      KorPreInitStrategy {

    private KorSubTypedEnumBlock block;

    SubTypedBlock(Block block) {

      if (!(block instanceof KorSubTypedEnumBlock)) {
        throw new IllegalArgumentException("SubTypedBlock strategy requires block to extend KorSubTypedBlock");
      }
      this.block = (KorSubTypedEnumBlock) block;
    }

    @Override
    public void onPreInit(Kor mod) {
      KorSubTypedItemBlock korSubTypedItemBlock;
      Collection<ISubType> validSubTypes;

      korSubTypedItemBlock = new KorSubTypedItemBlock(this.block);
      korSubTypedItemBlock.setRegistryName(this.block.getRegistryName());
      GameRegistry.register(this.block);
      GameRegistry.register(korSubTypedItemBlock);

      //noinspection unchecked
      validSubTypes = this.block.getSubTypes();

      if (this.block instanceof KorOreDictionaryEntryProvider) {

        List<KorOreDictionaryEntry> korOreDictionaryEntries;

        korOreDictionaryEntries = new ArrayList<>();
        this.block.getKorOreDictionaryEntries(korOreDictionaryEntries);

        for (KorOreDictionaryEntry entry : korOreDictionaryEntries) {
          boolean isEnabledOreDictionaryEntry;

          isEnabledOreDictionaryEntry = false;

          int meta = entry.getMeta();

          for (ISubType subType : validSubTypes) {

            if (meta == subType.getMeta()) {
              // found a match, is enabled ore dict entry
              isEnabledOreDictionaryEntry = true;
              break;
            }
          }

          if (isEnabledOreDictionaryEntry) {
            OreDictionary.registerOre(entry.getName(), new ItemStack(this.block, 1, meta));
            //System.out.println(String.format("Registered OreDict: [%s:%d]", entry.getName(), meta));
          }
        }
      }

      if (this.block instanceof IKorTileEntityProvider) {
        IKorTileEntityProvider tileEntityProvider = (IKorTileEntityProvider) this.block;
        GameRegistry.registerTileEntity(
            tileEntityProvider.getTileEntityClass(),
            tileEntityProvider.getTileEntityName()
        );
      }
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

      if (this.item instanceof KorOreDictionaryEntryProvider) {
        List<KorOreDictionaryEntry> korOreDictionaryEntries;

        korOreDictionaryEntries = new ArrayList<>();
        ((KorOreDictionaryEntryProvider) this.item).getKorOreDictionaryEntries(korOreDictionaryEntries);

        for (KorOreDictionaryEntry entry : korOreDictionaryEntries) {
          int meta = entry.getMeta();
          String name = entry.getName();
          OreDictionary.registerOre(name, new ItemStack(this.item, 1, meta));
        }
      }
    }
  }

  static SubTypedItem createSubTypedItemStrategy(Item item) {
    return new SubTypedItem(item);
  }

  class SubTypedItem implements
      KorPreInitStrategy {

    private KorSubTypedItem item;

    SubTypedItem(Item item) {

      if (!(item instanceof KorSubTypedItem)) {
        throw new RuntimeException("SubTypedItemRegisterStrategy requires item to implement ISubTypedItem");
      }
      this.item = (KorSubTypedItem) item;
    }

    @Override
    public void onPreInit(Kor mod) {
      GameRegistry.register(this.item);

      if (this.item instanceof KorOreDictionaryEntryProvider) {

        List<KorOreDictionaryEntry> korOreDictionaryEntries;
        ISubType[] validSubTypes;

        korOreDictionaryEntries = new ArrayList<>();
        this.item.getKorOreDictionaryEntries(korOreDictionaryEntries);
        validSubTypes = this.item.getSubTypes();

        for (KorOreDictionaryEntry entry : korOreDictionaryEntries) {
          boolean isEnabledOreDictionaryEntry;

          isEnabledOreDictionaryEntry = false;

          int meta = entry.getMeta();

          for (ISubType subType : validSubTypes) {

            if (meta == subType.getMeta()) {
              // found a match, is enabled ore dict entry
              isEnabledOreDictionaryEntry = true;
              break;
            }
          }

          if (isEnabledOreDictionaryEntry) {
            OreDictionary.registerOre(entry.getName(), new ItemStack(this.item, 1, meta));
          }
        }
      }

    }
  }
}
