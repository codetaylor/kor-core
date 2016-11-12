package com.sudoplay.mc.kor.spi.registry.dependency;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by sk3lls on 11/10/2016.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface KorCustomDependency {
  Class<?> target();

  String payload();
}
