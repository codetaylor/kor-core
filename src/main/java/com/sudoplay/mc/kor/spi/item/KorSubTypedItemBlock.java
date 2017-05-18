package com.sudoplay.mc.kor.spi.item;

import com.sudoplay.mc.kor.spi.block.IKorSubTypedEnumBlock;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

/**
 * Created by codetaylor on 11/6/2016.
 */
public class KorSubTypedItemBlock extends
    ItemBlock {

  public KorSubTypedItemBlock(Block block) {
    super(block);
    this.setHasSubtypes(true);
    this.setMaxDamage(0);
  }

  @Nonnull
  @Override
  public String getUnlocalizedName(ItemStack stack) {
    IKorSubTypedEnumBlock block = (IKorSubTypedEnumBlock) this.block;
    int itemDamage = stack.getItemDamage();
    ISubType subType = block.getSubType(itemDamage);
    return super.getUnlocalizedName() + "_" + subType.getName();
  }

  @Override
  public int getMetadata(int damage) {
    return damage;
  }
}
