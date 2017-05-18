package com.sudoplay.mc.kor.spi.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.IItemHandler;

import java.util.Random;

/**
 * Created by sk3lls on 1/28/2017.
 */
public class ItemHandlerUtils {

  private static final Random RANDOM = new Random();

  public static void dropInventoryItems(World worldIn, BlockPos pos, IItemHandler itemHandler) {
    dropInventoryItems(worldIn, (double) pos.getX(), (double) pos.getY(), (double) pos.getZ(), itemHandler);
  }

  public static void dropInventoryItems(World worldIn, Entity entityAt, IItemHandler itemHandler) {
    dropInventoryItems(worldIn, entityAt.posX, entityAt.posY, entityAt.posZ, itemHandler);
  }

  private static void dropInventoryItems(World worldIn, double x, double y, double z, IItemHandler itemHandler) {

    for (int i = 0; i < itemHandler.getSlots(); ++i) {
      ItemStack itemstack = itemHandler.getStackInSlot(i);

      if (itemstack != null) {
        spawnItemStack(worldIn, x, y, z, itemstack);
      }
    }
  }

  public static void spawnItemStack(World worldIn, double x, double y, double z, ItemStack stack) {
    float f = RANDOM.nextFloat() * 0.8F + 0.1F;
    float f1 = RANDOM.nextFloat() * 0.8F + 0.1F;
    float f2 = RANDOM.nextFloat() * 0.8F + 0.1F;

    while (stack.stackSize > 0) {
      int i = RANDOM.nextInt(21) + 10;

      EntityItem entityitem = new EntityItem(worldIn, x + (double) f, y + (double) f1, z + (double) f2, stack.splitStack(i));

      float f3 = 0.05F;
      entityitem.motionX = RANDOM.nextGaussian() * 0.05000000074505806D;
      entityitem.motionY = RANDOM.nextGaussian() * 0.05000000074505806D + 0.20000000298023224D;
      entityitem.motionZ = RANDOM.nextGaussian() * 0.05000000074505806D;
      worldIn.spawnEntityInWorld(entityitem);
    }
  }

  private ItemHandlerUtils() {
    //
  }
}
