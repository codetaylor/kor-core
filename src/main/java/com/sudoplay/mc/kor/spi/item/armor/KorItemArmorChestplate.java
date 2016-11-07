package com.sudoplay.mc.kor.spi.item.armor;

import com.sudoplay.mc.kor.spi.item.KorItemArmorTooltip;
import net.minecraft.inventory.EntityEquipmentSlot;

/**
 * Created by sk3lls on 10/29/2016.
 */
public class KorItemArmorChestplate extends
    KorItemArmorTooltip {

  public KorItemArmorChestplate(String modId, String name, ArmorMaterial armorMaterial) {
    super(modId, name, armorMaterial, 1, EntityEquipmentSlot.CHEST);
  }
}
