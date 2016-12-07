package com.sudoplay.mc;

import net.minecraftforge.fml.common.Mod;

import static net.minecraftforge.fml.common.Mod.Instance;

@Mod(
    modid = KorLibrary.MOD_ID,
    version = KorLibrary.VERSION,
    name = KorLibrary.NAME
)
public class KorLibrary {

  public static final String MOD_ID = "ctkorlib";
  public static final String VERSION = "@@VERSION@@";
  public static final String NAME = "CTKor Library";

  @SuppressWarnings("unused")
  @Instance
  public static KorLibrary INSTANCE;
}
