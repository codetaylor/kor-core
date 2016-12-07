package com.sudoplay.mc.kor.core.generation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by codetaylor on 11/7/2016.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface KorGenerateBlockSubTypedAssets {

  String property();

  String name();

  String modId();

  String[] subTypes();
}
