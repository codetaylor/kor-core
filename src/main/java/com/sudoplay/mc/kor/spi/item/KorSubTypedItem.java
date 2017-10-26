package com.sudoplay.mc.kor.spi.item;

import com.sudoplay.mc.kor.spi.registry.KorOreDictionaryEntryProvider;
import com.sudoplay.mc.kor.spi.registry.provider.KorClientInitStrategyProvider;
import com.sudoplay.mc.kor.spi.registry.provider.KorClientPreInitStrategyProvider;
import com.sudoplay.mc.kor.spi.registry.provider.KorPreInitStrategyProvider;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

import javax.annotation.Nonnull;

/**
 * Created by codetaylor on 11/3/2016.
 */
public abstract class KorSubTypedItem
    extends KorItemTooltip
    implements KorOreDictionaryEntryProvider,
    KorPreInitStrategyProvider.SubTypedItem,
    KorClientPreInitStrategyProvider.SubTypedItem,
    KorClientInitStrategyProvider.SubTypedItem {

  private ISubType[] subTypes;

  public KorSubTypedItem(String modId, String name, ISubType[] subTypes) {

    super(modId, name);
    this.subTypes = subTypes;
  }

  @Override
  public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> itemList) {

    if (this.subTypes.length > 0) {

      for (ISubType subType : this.subTypes) {
        itemList.add(new ItemStack(this, 1, subType.getMeta()));
      }

    } else {
      itemList.add(new ItemStack(this));
    }

  }

  @Nonnull
  @Override
  public String getUnlocalizedName(ItemStack stack) {

    for (ISubType subType : this.subTypes) {

      if (stack.getItemDamage() == subType.getMeta()) {
        return super.getUnlocalizedName(stack) + "_" + subType.getName();
      }
    }

    return super.getUnlocalizedName(stack);
  }

  public ISubType[] getSubTypes() {

    return this.subTypes;
  }

}
