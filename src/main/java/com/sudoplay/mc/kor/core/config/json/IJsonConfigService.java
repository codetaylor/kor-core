package com.sudoplay.mc.kor.core.config.json;

import com.sudoplay.mc.kor.spi.config.json.KorConfigObject;

/**
 * Created by codetaylor on 11/1/2016.
 */
public interface IJsonConfigService {

  <T extends KorConfigObject> T get(
      String configurationPathname,
      String jsonFilename,
      Class<T> defaultValueClass
  );

  <T extends KorConfigObject> T write(
      String configurationPathname,
      String jsonFilename,
      Class<T> defaultValueClass
  );
}
