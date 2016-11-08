package com.sudoplay.mc.kor.spi.item;

import com.sudoplay.mc.kor.spi.block.KorSubTypedBlock;
import com.sudoplay.mc.kor.spi.item.ISubType;
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
    ISubType[] subTypes = ((KorSubTypedBlock) this.block).getSubTypes();
    int itemDamage = stack.getItemDamage();

    if (itemDamage < 0 || itemDamage >= subTypes.length) {
      itemDamage = 0;
    }

    return super.getUnlocalizedName() + "_" + subTypes[itemDamage].getName();
  }

  @Override
  public int getMetadata(int damage) {
    return damage;
  }
}
