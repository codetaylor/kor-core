package com.sudoplay.mc;

import static net.minecraftforge.fml.common.Mod.Instance;

@net.minecraftforge.fml.common.Mod(
    modid = KorLibrary.MOD_ID,
    version = KorLibrary.VERSION,
    name = KorLibrary.NAME
)
public class KorLibrary {

  public static final String MOD_ID = "sk3lls-kor-library";
  public static final String VERSION = "0.1";
  public static final String NAME = "Sk3lls Kor Library";
  public static final double JSON_CONFIGS_VERSION = 1.0;

  @SuppressWarnings("unused")
  @Instance
  public static KorLibrary INSTANCE;
}
