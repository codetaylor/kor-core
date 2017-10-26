package com.sudoplay.mc.kor.spi.item.tool;

import com.sudoplay.mc.kor.spi.item.KorItemTooltip;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by codetaylor on 11/3/2016.
 */
public class KorItemToolAxeTooltip
    extends KorItemToolAxe {

  public KorItemToolAxeTooltip(
      String modId,
      String name,
      ToolMaterial material,
      float damageVsEntity,
      float attackSpeed
  ) {

    super(modId, name, material, damageVsEntity, attackSpeed);
  }

  @Override
  public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {

    KorItemTooltip.createTooltip(stack, tooltip);
    super.addInformation(stack, worldIn, tooltip, flagIn);
  }
}
