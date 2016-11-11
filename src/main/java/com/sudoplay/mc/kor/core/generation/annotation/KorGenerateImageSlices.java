package com.sudoplay.mc.kor.core.generation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by sk3lls on 11/11/2016.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface KorGenerateImageSlices {

  KorImageSliceEntry[] slices();
}
