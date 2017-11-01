package com.sudoplay.mc.kor.core.config.text;

import net.minecraftforge.common.config.Configuration;

import java.io.File;

/**
 * Created by codetaylor on 10/30/2016.
 */
public interface IConfigurationLoader {

  Configuration loadConfiguration(
      File file
  );
}
