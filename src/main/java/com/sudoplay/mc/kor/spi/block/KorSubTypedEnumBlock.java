package com.sudoplay.mc.kor.spi.block;

import com.sudoplay.mc.kor.core.IntMap;
import com.sudoplay.mc.kor.spi.item.ISubType;
import com.sudoplay.mc.kor.spi.registry.provider.KorClientInitStrategyProvider;
import com.sudoplay.mc.kor.spi.registry.provider.KorClientPreInitStrategyProvider;
import com.sudoplay.mc.kor.spi.registry.provider.KorPreInitStrategyProvider;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

/**
 * Inspired by:
 * https://github.com/SlimeKnights/Mantle/blob/master/src/main/java/slimeknights/mantle/block/EnumBlock.java
 * <p>
 * Created by sk3lls on 11/6/2016.
 */
public class KorSubTypedEnumBlock<E extends Enum<E> & ISubType & IStringSerializable> extends
    Block implements
    KorPreInitStrategyProvider.SubTypedBlock,
    KorClientPreInitStrategyProvider.SubTypedBlock,
    KorClientInitStrategyProvider.SubTypedBlock {

  private static PropertyEnum<?> TEMP_PROPERTY;

  private final PropertyEnum<E> property;

  /**
   * This list contains only the enum values allowed by the mask
   */
  private final List<ISubType> subTypeList;

  /**
   * This map contains all enum values
   */
  private final IntMap<E> subTypeIntMap;

  public KorSubTypedEnumBlock(
      String modId,
      String name,
      Material material,
      PropertyEnum<E> property,
      Class<E> enumClass
  ) {
    this(modId, name, material, property, enumClass, null);
  }

  public KorSubTypedEnumBlock(
      String modId,
      String name,
      Material material,
      PropertyEnum<E> property,
      Class<E> enumClass,
      boolean[] mask
  ) {
    super(KorSubTypedEnumBlock.hook(material, property), material.getMaterialMapColor());
    this.property = property;

    E[] enumConstants = enumClass.getEnumConstants();

    if (mask != null && enumConstants.length != mask.length) {
      throw new IllegalArgumentException(String.format("Mask length doesn't equal enum length for: %s", name));
    }

    this.subTypeList = new ArrayList<>();
    this.subTypeIntMap = new IntMap<>();

    for (int i = 0; i < enumConstants.length; i++) {
      this.subTypeIntMap.put(enumConstants[i].getMeta(), enumConstants[i]);

      if (mask == null || mask[i]) {
        this.subTypeList.add(enumConstants[i]);
      }
    }

    this.setUnlocalizedName(name);
    this.setRegistryName(modId, name);
  }

  private static Material hook(Material material, PropertyEnum<?> property) {
    TEMP_PROPERTY = property;
    return material;
  }

  @SideOnly(Side.CLIENT)
  @Override
  public void getSubBlocks(@Nonnull Item item, CreativeTabs tab, List<ItemStack> list) {

    for (ISubType subType : this.subTypeList) {

      list.add(new ItemStack(this, 1, subType.getMeta()));
    }
  }

  @Nonnull
  @Override
  protected BlockStateContainer createBlockState() {

    if (this.property == null) {
      return new BlockStateContainer(this, TEMP_PROPERTY);

    } else {
      return new BlockStateContainer(this, this.property);
    }
  }

  @Nonnull
  @Override
  public IBlockState getStateFromMeta(int meta) {
    return this.getDefaultState().withProperty(this.property, fromMeta(meta));
  }

  @Override
  public int getMetaFromState(IBlockState state) {
    return state.getValue(this.property).getMeta();
  }

  @Override
  public int damageDropped(IBlockState state) {
    return getMetaFromState(state);
  }

  @Nonnull
  @Override
  public ItemStack getPickBlock(
      @Nonnull IBlockState state,
      RayTraceResult target,
      @Nonnull World world,
      @Nonnull BlockPos pos,
      EntityPlayer player
  ) {
    Item itemFromBlock = Item.getItemFromBlock(this);
    assert itemFromBlock != null;
    return new ItemStack(itemFromBlock, 1, getMetaFromState(world.getBlockState(pos)));
  }

  /**
   * @return only valid subtypes
   */
  public List<ISubType> getSubTypes() {
    return this.subTypeList;
  }

  /**
   * @return IntMap of all possible ISubType mapped by meta
   */
  public IntMap<E> getSubTypeIntMap() {
    return this.subTypeIntMap;
  }

  private E fromMeta(int meta) {
    return this.subTypeIntMap.get(meta);
  }
}
