package com.sudoplay.mc.kor.spi.item.tool;

import com.google.common.collect.Sets;
import com.sudoplay.mc.kor.spi.registry.provider.KorClientPreInitStrategyProvider;
import com.sudoplay.mc.kor.spi.registry.provider.KorPreInitStrategyProvider;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;

import java.util.Set;

/**
 * This class extends {@link ItemTool} instead of {@link net.minecraft.item.ItemAxe} to fix
 * an {@link IndexOutOfBoundsException}. The ItemAxe class looks up the damage and speed parameters
 * from an array with indexes for vanilla tool materials only. Adding a new material for your axe
 * causes the lookup to fail. This class works around that by passing the damage and speed parameters
 * to the ItemTool class, bypassing the array lookup.
 * <p>
 * Created by sk3lls on 10/29/2016.
 */
public abstract class KorItemToolAxe extends
    ItemTool implements
    KorPreInitStrategyProvider.BasicItem,
    KorClientPreInitStrategyProvider.BasicItem {

  private static final Set<Block> EFFECTIVE_ON = Sets.newHashSet(Blocks.PLANKS, Blocks.BOOKSHELF, Blocks.LOG, Blocks.LOG2, Blocks.CHEST, Blocks.PUMPKIN, Blocks.LIT_PUMPKIN, Blocks.MELON_BLOCK, Blocks.LADDER, Blocks.WOODEN_BUTTON, Blocks.WOODEN_PRESSURE_PLATE);

  protected KorItemToolAxe(String modId, String name, ToolMaterial material, float damageVsEntity, float attackSpeed) {
    super(damageVsEntity, attackSpeed, material, EFFECTIVE_ON);
    this.setUnlocalizedName(name);
    this.setRegistryName(modId, name);
  }

  @Override
  public float getStrVsBlock(ItemStack stack, IBlockState state) {
    Material material = state.getMaterial();
    return material != Material.WOOD && material != Material.PLANTS && material != Material.VINE ? super.getStrVsBlock(stack, state) : this.efficiencyOnProperMaterial;
  }
}

