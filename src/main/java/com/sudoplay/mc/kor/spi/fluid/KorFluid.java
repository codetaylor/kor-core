package com.sudoplay.mc.kor.spi.fluid;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

/**
 * Fluid.
 */
public abstract class KorFluid
    extends Fluid {

  public KorFluid(
      String modId,
      String name,
      boolean still
  ) {

    super(
        name,
        new ResourceLocation(modId, "blocks/" + name + (still ? "_still" : "")),
        new ResourceLocation(modId, "blocks/" + name + (still ? "_flow" : ""))
    );
  }

}
