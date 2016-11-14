package com.sudoplay.mc.kor.spi.material;

import com.sudoplay.mc.kor.spi.registry.strategy.KorRegistrationStrategy;
import com.sudoplay.mc.kor.spi.registry.provider.KorRegistrationStrategyProvider;
import net.minecraft.item.ItemArmor;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.common.util.EnumHelper;

/**
 * Created by sk3lls on 10/29/2016.
 */
public class KorArmorMaterial implements
    KorRegistrationStrategyProvider {

  private String name;
  private String textureName;
  private int durability;
  private int[] reductionAmounts;
  private int enchantability;
  private SoundEvent soundOnEquip;
  private float toughness;

  private ItemArmor.ArmorMaterial armorMaterial;

  public KorArmorMaterial(
      String name,
      String textureName,
      int durability,
      int[] reductionAmounts,
      int enchantability,
      SoundEvent soundOnEquip,
      float toughness
  ) {
    this.name = name;
    this.textureName = textureName;
    this.durability = durability;
    this.reductionAmounts = reductionAmounts;
    this.enchantability = enchantability;
    this.soundOnEquip = soundOnEquip;
    this.toughness = toughness;
  }

  public ItemArmor.ArmorMaterial asArmorMaterial() {
    return this.armorMaterial;
  }

  @Override
  public KorRegistrationStrategy getRegistrationStrategy() {
    return (mod, registryService) -> this.armorMaterial = EnumHelper.addArmorMaterial(
        this.name,
        this.textureName,
        this.durability,
        this.reductionAmounts,
        this.enchantability,
        this.soundOnEquip,
        this.toughness
    );
  }
}
