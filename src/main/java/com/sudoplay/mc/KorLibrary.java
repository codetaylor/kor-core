package com.sudoplay.mc;

import static net.minecraftforge.fml.common.Mod.Instance;

@net.minecraftforge.fml.common.Mod(
    modid = KorLibrary.MOD_ID,
    version = KorLibrary.VERSION,
    name = KorLibrary.NAME
)
public class KorLibrary {

  public static final String MOD_ID = "ctkorlib";
  public static final String VERSION = "0.1";
  public static final String NAME = "CT Kor Library";
  public static final double JSON_CONFIGS_VERSION = 1.0;

  @SuppressWarnings("unused")
  @Instance
  public static KorLibrary INSTANCE;
}
