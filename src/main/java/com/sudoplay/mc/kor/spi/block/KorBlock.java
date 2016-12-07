package com.sudoplay.mc.kor.spi.block;

import com.sudoplay.mc.kor.spi.registry.provider.KorClientPreInitStrategyProvider;
import com.sudoplay.mc.kor.spi.registry.provider.KorPreInitStrategyProvider;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

/**
 * Created by codetaylor on 11/3/2016.
 */
public class KorBlock extends
    Block implements
    KorPreInitStrategyProvider.BasicBlock,
    KorClientPreInitStrategyProvider.BasicBlock {

  public KorBlock(String modId, String name, Material material) {
    this(modId, name, material, material.getMaterialMapColor());
  }

  public KorBlock(String modId, String name, Material blockMaterial, MapColor blockMapColor) {
    super(blockMaterial, blockMapColor);
    this.setUnlocalizedName(name);
    this.setRegistryName(modId, name);
  }
}
