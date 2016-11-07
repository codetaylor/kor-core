package com.sudoplay.mc.kor.spi.material;

import com.sudoplay.mc.kor.spi.registry.strategy.KorRegistrationStrategy;
import com.sudoplay.mc.kor.spi.registry.provider.KorRegistrationStrategyProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemTool;
import net.minecraftforge.common.util.EnumHelper;

/**
 * Created by sk3lls on 10/29/2016.
 */
public class KorToolMaterial implements
    KorRegistrationStrategyProvider {

  private String name;
  private int harvestLevel;
  private int maxUses;
  private float efficiency;
  private float damage;
  private int enchantability;

  private ItemTool.ToolMaterial toolMaterial;

  public KorToolMaterial(
      String name,
      int harvestLevel,
      int maxUses,
      float efficiency,
      float damage,
      int enchantability
  ) {
    this.name = name;
    this.harvestLevel = harvestLevel;
    this.maxUses = maxUses;
    this.efficiency = efficiency;
    this.damage = damage;
    this.enchantability = enchantability;
  }

  public Item.ToolMaterial asToolMaterial() {
    return this.toolMaterial;
  }

  @Override
  public KorRegistrationStrategy getRegistrationStrategy() {
    return (mod) -> this.toolMaterial = EnumHelper.addToolMaterial(
        this.name,
        this.harvestLevel,
        this.maxUses,
        this.efficiency,
        this.damage,
        this.enchantability
    );
  }
}
