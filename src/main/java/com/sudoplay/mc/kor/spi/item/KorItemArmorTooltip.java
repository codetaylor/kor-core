package com.sudoplay.mc.kor.spi.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

import java.util.List;

/**
 * Created by sk3lls on 11/3/2016.
 */
public abstract class KorItemArmorTooltip extends
    ItemArmor {

  public KorItemArmorTooltip(String modId, String name, ItemArmor.ArmorMaterial armorMaterial, int renderIndex, EntityEquipmentSlot feet) {
    super(armorMaterial, renderIndex, feet);
    this.setUnlocalizedName(name);
    this.setRegistryName(modId, name);
  }

  @Override
  public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
    KorItemTooltip.createTooltip(stack, tooltip);
    super.addInformation(stack, playerIn, tooltip, advanced);
  }
}
