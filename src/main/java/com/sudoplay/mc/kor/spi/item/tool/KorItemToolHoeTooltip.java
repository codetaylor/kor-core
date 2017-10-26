package com.sudoplay.mc.kor.spi.item.tool;

import com.sudoplay.mc.kor.spi.item.KorItemTooltip;
import com.sudoplay.mc.kor.spi.registry.provider.KorClientPreInitStrategyProvider;
import com.sudoplay.mc.kor.spi.registry.provider.KorPreInitStrategyProvider;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by codetaylor on 11/3/2016.
 */
public class KorItemToolHoeTooltip
    extends ItemHoe
    implements KorPreInitStrategyProvider.BasicItem,
    KorClientPreInitStrategyProvider.BasicItem {

  public KorItemToolHoeTooltip(String modId, String name, ToolMaterial material) {

    super(material);
    this.setUnlocalizedName(name);
    this.setRegistryName(modId, name);
  }

  @Override
  public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {

    KorItemTooltip.createTooltip(stack, tooltip);
    super.addInformation(stack, worldIn, tooltip, flagIn);
  }
}
