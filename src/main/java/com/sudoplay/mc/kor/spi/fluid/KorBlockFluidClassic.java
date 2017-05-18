package com.sudoplay.mc.kor.spi.fluid;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

/**
 * Created by codetaylor on 5/17/2017.
 */
public abstract class KorBlockFluidClassic extends
    BlockFluidClassic {

  public KorBlockFluidClassic(
      String modId,
      String name,
      Fluid fluid,
      Material material
  ) {
    super(fluid, material);
    this.setUnlocalizedName(name);
    this.setRegistryName(modId, name);
  }

  @Override
  public boolean canDisplace(IBlockAccess world, BlockPos pos) {
    IBlockState blockState = world.getBlockState(pos);

    if (blockState.getBlock().getMaterial(blockState).isLiquid()) {
      return false;
    }

    return super.canDisplace(world, pos);
  }

  @Override
  public boolean displaceIfPossible(World world, BlockPos pos) {
    IBlockState blockState = world.getBlockState(pos);

    if (blockState.getBlock().getMaterial(blockState).isLiquid()) {
      return false;
    }

    return super.displaceIfPossible(world, pos);
  }

}
