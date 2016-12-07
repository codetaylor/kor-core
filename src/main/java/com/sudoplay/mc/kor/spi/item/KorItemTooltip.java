package com.sudoplay.mc.kor.spi.item;

import com.google.common.collect.Lists;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;

import java.util.List;

/**
 * Created by codetaylor on 11/3/2016.
 */
public abstract class KorItemTooltip extends
    Item {

  public KorItemTooltip(String modId, String name) {
    this.setUnlocalizedName(name);
    this.setRegistryName(modId, name);
  }

  @Override
  public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
    KorItemTooltip.createTooltip(stack, tooltip);
    super.addInformation(stack, playerIn, tooltip, advanced);
  }

  public static void createTooltip(ItemStack stack, List<String> tooltip) {
    String key = stack.getUnlocalizedName() + ".tooltip";
    if (I18n.hasKey(key)) {
      tooltip.addAll(KorItemTooltip.getTooltips(
          TextFormatting.GRAY.toString() + I18n.format(key)
      ));
    }
  }

  /**
   * From https://github.com/SlimeKnights/Mantle/blob/master/src/main/java/slimeknights/mantle/util/LocUtils.java
   *
   * @param text
   * @return
   */
  private static List<String> getTooltips(String text) {
    List<String> list = Lists.newLinkedList();
    if (text == null)
      return list;
    int j = 0;
    int k;
    while ((k = text.indexOf("\\n", j)) >= 0) {
      list.add(text.substring(j, k));
      j = k + 2;
    }

    list.add(text.substring(j, text.length()));

    return list;
  }
}
