package com.sudoplay.mc.kor.spi.registry.strategy;

import com.sudoplay.mc.kor.spi.Kor;
import com.sudoplay.mc.kor.spi.block.IKorSubTypedEnumBlock;
import com.sudoplay.mc.kor.spi.fluid.KorFluidRegistrationContainer;
import com.sudoplay.mc.kor.spi.item.ISubType;
import com.sudoplay.mc.kor.spi.item.KorSubTypedItem;
import com.sudoplay.mc.kor.spi.util.StringUtils;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.DefaultStateMapper;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.BlockFluidBase;

import java.util.Collection;
import java.util.Map;

/**
 * Created by codetaylor on 10/30/2016.
 */
public interface KorClientPreInitStrategy {

  void onClientPreInit(
      Kor kor
  );

  static BasicItem createBasicItemStrategy(Item item) {

    return new BasicItem(item);
  }

  class BasicItem
      implements
      KorClientPreInitStrategy {

    private Item item;

    BasicItem(Item item) {

      this.item = item;
    }

    @Override
    public void onClientPreInit(Kor mod) {

      String resourcePath = this.item.getRegistryName().getResourcePath();
      String resourceSubfolder = StringUtils.getResourceSubfolder(this.item);
      ResourceLocation resourceLocation = new ResourceLocation(mod.getModId(), resourceSubfolder + resourcePath);
      ModelResourceLocation modelResourceLocation = new ModelResourceLocation(resourceLocation, "inventory");
      ModelLoader.setCustomModelResourceLocation(this.item, 0, modelResourceLocation);
    }
  }

  static BasicBlock createBasicBlockStrategy(Block block) {

    return new BasicBlock(block);
  }

  class BasicBlock
      implements KorClientPreInitStrategy {

    private Block block;

    BasicBlock(Block block) {

      this.block = block;
    }

    @Override
    public void onClientPreInit(Kor mod) {

      String resourceSubfolder = StringUtils.getResourceSubfolder(this.block);
      ModelLoader.setCustomStateMapper(
          this.block,
          new KorClientPreInitStrategy.ResourceSubfolderStateMapper(resourceSubfolder)
      );

      Item item = Item.getItemFromBlock(this.block);
      String resourcePath = item.getRegistryName().getResourcePath();
      ResourceLocation resourceLocation = new ResourceLocation(mod.getModId(), resourceSubfolder + resourcePath);
      ModelResourceLocation modelResourceLocation = new ModelResourceLocation(resourceLocation, "inventory");
      ModelLoader.setCustomModelResourceLocation(item, 0, modelResourceLocation);
    }
  }

  static SubTypedBlock createSubTypedBlockStrategy(Block block) {

    return new SubTypedBlock(block);
  }

  class SubTypedBlock
      implements KorClientPreInitStrategy {

    private Block block;

    public SubTypedBlock(Block block) {

      if (!(block instanceof IKorSubTypedEnumBlock)) {
        throw new IllegalArgumentException("EnumBlock strategy requires block to implement IKorSubTypedBlock");
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

      IKorSubTypedEnumBlock block = (IKorSubTypedEnumBlock) this.block;
      String resourceSubfolder = StringUtils.getResourceSubfolder(this.block);
      ModelLoader.setCustomStateMapper(
          this.block,
          new KorClientPreInitStrategy.ResourceSubfolderStateMapper(resourceSubfolder)
      );

      //noinspection unchecked
      Collection<ISubType> subTypes = block.getSubTypes();

      for (ISubType subType : subTypes) {
        meta = subType.getMeta();
        blockName = this.block.getRegistryName().getResourcePath();
        subBlockName = blockName + "_" + subType.getName();

        ResourceLocation resourceLocation = new ResourceLocation(modId, resourceSubfolder + subBlockName);
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

  class SubTypedItem
      implements KorClientPreInitStrategy {

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

        String resourceSubfolder = StringUtils.getResourceSubfolder(this.item);
        ResourceLocation resourceLocation = new ResourceLocation(modId, resourceSubfolder + subItemName);
        ModelResourceLocation modelResourceLocation = new ModelResourceLocation(resourceLocation, "inventory");
        ModelLoader.setCustomModelResourceLocation(this.item, meta, modelResourceLocation);
      }
    }
  }

  static BasicFluid createBasicFluidStrategy(KorFluidRegistrationContainer container) {

    return new BasicFluid(container);
  }

  class BasicFluid
      implements KorClientPreInitStrategy {

    private KorFluidRegistrationContainer container;

    BasicFluid(KorFluidRegistrationContainer container) {

      this.container = container;
    }

    @Override
    public void onClientPreInit(Kor kor) {

      // if our fluid wasn't registered, skip registering a model for it
      // a fluid might not register if a fluid with the same name is already registered
      if (!this.container.isFluidRegistered()) {
        return;
      }

      String modId;
      String fluidName;
      BlockFluidBase fluidBlock;
      Item item;
      FluidStateMapper stateMapper;

      modId = kor.getModId();
      fluidName = this.container.getFluid().getName();
      fluidBlock = this.container.getBlockFluid();
      stateMapper = new FluidStateMapper(modId, fluidName, StringUtils.getResourceSubfolder(this.container));
      item = Item.getItemFromBlock(fluidBlock);

      ModelBakery.registerItemVariants(item);
      ModelLoader.setCustomMeshDefinition(item, stateMapper);
      ModelLoader.setCustomStateMapper(fluidBlock, stateMapper);

    }

  }

  class FluidStateMapper
      extends StateMapperBase
      implements ItemMeshDefinition {

    private ModelResourceLocation location;

    public FluidStateMapper(String modId, String name, String resourceSubfolder) {

      this.location = new ModelResourceLocation(modId + ":" + resourceSubfolder + "fluids", name);
    }

    @Override
    protected ModelResourceLocation getModelResourceLocation(IBlockState state) {

      return this.location;
    }

    @Override
    public ModelResourceLocation getModelLocation(ItemStack stack) {

      return this.location;
    }

  }

  class ResourceSubfolderStateMapper
      extends DefaultStateMapper {

    private String subfolder;

    public ResourceSubfolderStateMapper(String subfolder) {

      this.subfolder = subfolder;
    }

    @Override
    public Map<IBlockState, ModelResourceLocation> putStateModelLocations(Block blockIn) {

      for (IBlockState state : blockIn.getBlockState().getValidStates()) {
        ResourceLocation resourceLocation = Block.REGISTRY.getNameForObject(state.getBlock());
        String resourcePath = resourceLocation.getResourcePath();
        String resourceDomain = resourceLocation.getResourceDomain();
        ModelResourceLocation modelResourceLocation = new ModelResourceLocation(
            resourceDomain  + ":" + this.subfolder + resourcePath,
            this.getPropertyString(state.getProperties())
        );
        this.mapStateModelLocations.put(state, modelResourceLocation);
      }

      return this.mapStateModelLocations;
    }
  }
}
