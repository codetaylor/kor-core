package com.sudoplay.mc.kor.spi.registry.strategy;

import com.sudoplay.mc.kor.spi.Kor;
import com.sudoplay.mc.kor.spi.block.IKorSubTypedEnumBlock;
import com.sudoplay.mc.kor.spi.block.IKorTileEntityProvider;
import com.sudoplay.mc.kor.spi.fluid.KorFluidRegistrationContainer;
import com.sudoplay.mc.kor.spi.item.ISubType;
import com.sudoplay.mc.kor.spi.item.KorSubTypedItem;
import com.sudoplay.mc.kor.spi.item.KorSubTypedItemBlock;
import com.sudoplay.mc.kor.spi.registry.KorOreDictionaryEntry;
import com.sudoplay.mc.kor.spi.registry.KorOreDictionaryEntryProvider;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

import java.util.*;

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

  class BasicBlock
      implements KorPreInitStrategy {

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
      ForgeRegistries.BLOCKS.register(this.block);
      ForgeRegistries.ITEMS.register(itemBlock);

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
        Map<String, Class<? extends TileEntity>> tileEntityMap = tileEntityProvider.getTileEntityMap(new HashMap<>());
        tileEntityMap.forEach((name, tileEntityClass) -> GameRegistry.registerTileEntity(tileEntityClass, name));
      }
    }
  }

  static SubTypedBlock createSubTypedBlockStrategy(Block block) {

    return new SubTypedBlock(block);
  }

  class SubTypedBlock
      implements KorPreInitStrategy {

    private Block block;

    SubTypedBlock(Block block) {

      if (!(block instanceof IKorSubTypedEnumBlock)) {
        throw new IllegalArgumentException("SubTypedBlock strategy requires block to implement IKorSubTypedBlock");
      }
      this.block = block;
    }

    @Override
    public void onPreInit(Kor mod) {

      KorSubTypedItemBlock korSubTypedItemBlock;
      Collection<ISubType> validSubTypes;

      korSubTypedItemBlock = new KorSubTypedItemBlock(this.block);
      korSubTypedItemBlock.setRegistryName(this.block.getRegistryName());
      ForgeRegistries.BLOCKS.register(this.block);
      ForgeRegistries.ITEMS.register(korSubTypedItemBlock);

      //noinspection unchecked
      validSubTypes = ((IKorSubTypedEnumBlock) this.block).getSubTypes();

      if (this.block instanceof KorOreDictionaryEntryProvider) {

        List<KorOreDictionaryEntry> korOreDictionaryEntries;

        korOreDictionaryEntries = new ArrayList<>();
        ((KorOreDictionaryEntryProvider) this.block).getKorOreDictionaryEntries(korOreDictionaryEntries);

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
        Map<String, Class<? extends TileEntity>> tileEntityMap = tileEntityProvider.getTileEntityMap(new HashMap<>());
        tileEntityMap.forEach((name, tileEntityClass) -> GameRegistry.registerTileEntity(tileEntityClass, name));
      }
    }
  }

  static BasicItem createBasicItemStrategy(Item item) {

    return new BasicItem(item);
  }

  class BasicItem
      implements KorPreInitStrategy {

    private Item item;

    BasicItem(Item item) {

      this.item = item;
    }

    @Override
    public void onPreInit(Kor mod) {

      ForgeRegistries.ITEMS.register(this.item);

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

  class SubTypedItem
      implements KorPreInitStrategy {

    private KorSubTypedItem item;

    SubTypedItem(Item item) {

      if (!(item instanceof KorSubTypedItem)) {
        throw new RuntimeException("SubTypedItemRegisterStrategy requires item to implement ISubTypedItem");
      }
      this.item = (KorSubTypedItem) item;
    }

    @Override
    public void onPreInit(Kor mod) {

      ForgeRegistries.ITEMS.register(this.item);

      if (this.item != null) {

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

  static BasicFluid createFluidPreInitRegistrationStrategy(KorFluidRegistrationContainer fluid) {

    return new BasicFluid(fluid);
  }

  class BasicFluid
      implements KorPreInitStrategy {

    private KorFluidRegistrationContainer container;

    BasicFluid(KorFluidRegistrationContainer container) {

      this.container = container;
    }

    @Override
    public void onPreInit(Kor kor) {

      Fluid fluid = this.container.getFluid();

      // this is false if a fluid has already been registered with this fluid's name
      boolean useLocalFluid = FluidRegistry.registerFluid(fluid);

      if (useLocalFluid) {

        if (this.container instanceof KorFluidRegistrationContainer) {

          // register block
          KorPreInitStrategy
              .createBasicBlockStrategy(this.container.getBlockFluid())
              .onPreInit(kor);
        }

      } else {
        fluid = FluidRegistry.getFluid(fluid.getName());
      }

      // this holds a reference to either our own fluid or the fluid that was already
      // registered with the same name
      this.container.setRegisteredFluid(fluid);

      FluidRegistry.addBucketForFluid(fluid);

    }

  }

}
