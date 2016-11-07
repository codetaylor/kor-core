package com.sudoplay.mc.kor.spi.item.armor;

import com.sudoplay.mc.kor.spi.item.KorItemArmorTooltip;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;

/**
 * Created by sk3lls on 10/29/2016.
 */
public class KorItemArmorBoots extends
    KorItemArmorTooltip {

  public KorItemArmorBoots(String modId, String name, ItemArmor.ArmorMaterial armorMaterial) {
    super(modId, name, armorMaterial, 1, EntityEquipmentSlot.FEET);
  }
}
