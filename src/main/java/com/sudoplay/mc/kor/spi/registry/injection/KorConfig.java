package com.sudoplay.mc.kor.spi.registry.injection;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by sk3lls on 11/3/2016.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER})
public @interface KorConfig {

  String path();

  String file();
}