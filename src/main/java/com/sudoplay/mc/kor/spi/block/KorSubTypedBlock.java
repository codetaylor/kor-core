package com.sudoplay.mc.kor.spi.block;

import com.sudoplay.mc.kor.spi.item.ISubType;
import com.sudoplay.mc.kor.spi.registry.provider.KorClientInitStrategyProvider;
import com.sudoplay.mc.kor.spi.registry.provider.KorClientPreInitStrategyProvider;
import com.sudoplay.mc.kor.spi.registry.provider.KorPreInitStrategyProvider;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
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
import java.util.List;

/**
 * https://github.com/SlimeKnights/Mantle/blob/master/src/main/java/slimeknights/mantle/block/EnumBlock.java
 * <p>
 * Created by sk3lls on 11/6/2016.
 */
public class KorSubTypedBlock<E extends Enum<E> & ISubType & IStringSerializable> extends
    Block implements
    KorPreInitStrategyProvider.SubTypedBlock,
    KorClientPreInitStrategyProvider.SubTypedBlock,
    KorClientInitStrategyProvider.SubTypedBlock {

  private static PropertyEnum<?> TEMP_PROPERTY;

  private final PropertyEnum<E> property;
  private final E[] subTypes;

  public KorSubTypedBlock(String modId, String name, Material material, PropertyEnum<E> property, Class<E> enumClass) {
    this(modId, name, material, material.getMaterialMapColor(), property, enumClass);
  }

  public KorSubTypedBlock(String modId, String name, Material material, MapColor blockMapColor, PropertyEnum<E> property, Class<E> enumClass) {
    super(superHook(material, property), blockMapColor);
    this.property = property;
    this.subTypes = enumClass.getEnumConstants();
    this.setUnlocalizedName(name);
    this.setRegistryName(modId, name);
  }

  private static Material superHook(Material material, PropertyEnum<?> property) {
    TEMP_PROPERTY = property;
    return material;
  }

  @SideOnly(Side.CLIENT)
  @Override
  public void getSubBlocks(@Nonnull Item item, CreativeTabs tab, List<ItemStack> list) {

    for (ISubType type : this.subTypes) {
      list.add(new ItemStack(this, 1, type.getMeta()));
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

  public ISubType[] getSubTypes() {
    return this.subTypes;
  }

  private E fromMeta(int meta) {

    if (meta < 0 || meta >= this.subTypes.length) {
      meta = 0;
    }
    return this.subTypes[meta];
  }
}
