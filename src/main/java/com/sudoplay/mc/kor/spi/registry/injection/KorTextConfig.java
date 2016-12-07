package com.sudoplay.mc.kor.spi.registry.injection;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by codetaylor on 11/10/2016.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER})
public @interface KorTextConfig {

  String path() default "";

  String file();
}
