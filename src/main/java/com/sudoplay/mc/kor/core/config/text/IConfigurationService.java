package com.sudoplay.mc.kor.core.config.text;

import net.minecraftforge.common.config.Configuration;

import java.io.File;

/**
 * Created by codetaylor on 11/1/2016.
 */
public interface IConfigurationService {

  Configuration get(String name);

  Configuration loadConfiguration(
      File configurationFile
  );

}
