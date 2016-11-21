package com.sudoplay.mc.kor.spi.world;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.Random;

/**
 * Created by sk3lls on 11/21/2016.
 */
public interface KorOreGenReplacementStrategy {

  void execute(@Nonnull World worldIn, @Nonnull BlockPos blockpos, @Nonnull Random random);
}
