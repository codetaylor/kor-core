package com.sudoplay.mc.kor.spi.item.tool;

import com.sudoplay.mc.kor.spi.item.KorItemTooltip;
import com.sudoplay.mc.kor.spi.registry.provider.KorClientPreInitStrategyProvider;
import com.sudoplay.mc.kor.spi.registry.provider.KorPreInitStrategyProvider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;

import java.util.List;

/**
 * Created by codetaylor on 11/3/2016.
 */
public class KorItemToolHoeTooltip extends
    ItemHoe implements
    KorPreInitStrategyProvider.BasicItem,
    KorClientPreInitStrategyProvider.BasicItem {

  public KorItemToolHoeTooltip(String modId, String name, ToolMaterial material) {
    super(material);
    this.setUnlocalizedName(name);
    this.setRegistryName(modId, name);
  }

  @Override
  public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
    KorItemTooltip.createTooltip(stack, tooltip);
    super.addInformation(stack, playerIn, tooltip, advanced);
  }
}
