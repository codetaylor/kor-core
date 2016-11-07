package com.sudoplay.mc.kor.core.config.json;

import com.sudoplay.mc.kor.spi.config.json.KorConfigObject;

/**
 * Created by sk3lls on 11/1/2016.
 */
public interface IConfigService {

  <T extends KorConfigObject> T get(
      String configurationPathname,
      String jsonFilename,
      Class<T> defaultValueClass
  );
}
