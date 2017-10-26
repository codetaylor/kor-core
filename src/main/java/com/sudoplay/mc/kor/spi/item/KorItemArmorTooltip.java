package com.sudoplay.mc.kor.spi.item;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by codetaylor on 11/3/2016.
 */
public abstract class KorItemArmorTooltip
    extends ItemArmor {

  public KorItemArmorTooltip(
      String modId,
      String name,
      ItemArmor.ArmorMaterial armorMaterial,
      int renderIndex,
      EntityEquipmentSlot feet
  ) {

    super(armorMaterial, renderIndex, feet);
    this.setUnlocalizedName(name);
    this.setRegistryName(modId, name);
  }

  @Override
  public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {

    KorItemTooltip.createTooltip(stack, tooltip);
    super.addInformation(stack, worldIn, tooltip, flagIn);
  }
}
