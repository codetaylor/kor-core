package com.sudoplay.mc.kor.spi.item.tool;

import com.sudoplay.mc.kor.spi.item.KorItemTooltip;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import java.util.List;

/**
 * Created by sk3lls on 11/3/2016.
 */
public class KorItemToolAxeTooltip extends
    KorItemToolAxe {

  public KorItemToolAxeTooltip(String modId, String name, ToolMaterial material, float damageVsEntity, float attackSpeed) {
    super(modId, name, material, damageVsEntity, attackSpeed);
  }

  @Override
  public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
    KorItemTooltip.createTooltip(stack, tooltip);
    super.addInformation(stack, playerIn, tooltip, advanced);
  }
}
