package com.sudoplay.mc.kor.spi.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface KorConfigurable {

  String file() default "";

  String categoryPrefix() default "";

  String category() default "";
}
