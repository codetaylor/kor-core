package com.sudoplay.mc.kor.spi.item;

import com.sudoplay.mc.kor.core.IntMap;
import com.sudoplay.mc.kor.spi.block.KorSubTypedEnumBlock;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

/**
 * Created by sk3lls on 11/6/2016.
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
    KorSubTypedEnumBlock block = (KorSubTypedEnumBlock) this.block;

    //noinspection unchecked
    IntMap<ISubType> subTypeIntMap = block.getSubTypeIntMap();
    int itemDamage = stack.getItemDamage();

    return super.getUnlocalizedName() + "_" + subTypeIntMap.get(itemDamage).getName();
  }

  @Override
  public int getMetadata(int damage) {
    return damage;
  }
}
