package com.sudoplay.mc.kor.spi.fluid;

import com.sudoplay.mc.kor.spi.registry.provider.KorClientPreInitStrategyProvider;
import com.sudoplay.mc.kor.spi.registry.provider.KorPreInitStrategyProvider;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fluids.Fluid;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Fluid wrapper.
 */
public abstract class KorFluidRegistrationContainer implements
    KorPreInitStrategyProvider.BasicFluid,
    KorClientPreInitStrategyProvider.BasicFluid {

  private final Supplier<Fluid> fluidSupplier;
  private final Function<Fluid, BlockFluidBase> fluidBlockProvider;

  private Fluid fluid;
  private BlockFluidBase blockFluid;

  private Fluid registeredFluid;

  public KorFluidRegistrationContainer(
      Supplier<Fluid> fluidSupplier,
      Function<Fluid, BlockFluidBase> fluidBlockProvider
  ) {
    this.fluidSupplier = fluidSupplier;
    this.fluidBlockProvider = fluidBlockProvider;
  }

  public Fluid getFluid() {

    if (this.fluid == null) {
      this.fluid = this.fluidSupplier.get();
    }

    return this.fluid;
  }

  public BlockFluidBase getBlockFluid() {

    if (this.blockFluid == null) {
      this.blockFluid = this.fluidBlockProvider.apply(getFluid());
    }

    return this.blockFluid;
  }

  public void setRegisteredFluid(Fluid fluid) {
    this.registeredFluid = fluid;
  }

  public Fluid getRegisteredFluid() {
    return this.registeredFluid;
  }

  /**
   * Returns true if the fluid registered was from this mod, otherwise, if a fluid was already
   * registered with this fluid's name, it returns false.
   *
   * @return true if the fluid registered was from this mod
   */
  public boolean isFluidRegistered() {
    return this.fluid == this.registeredFluid;
  }
}
