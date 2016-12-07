package com.sudoplay.mc.kor.core.registry;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by codetaylor on 10/30/2016.
 */
public class FilterBuckets {

  private Map<Class<?>, List<?>> bucketMap;

  public FilterBuckets(Class[] bucketClasses) {
    this.bucketMap = new LinkedHashMap<>();

    for (Class<?> bucketClass : bucketClasses) {
      this.createBucket(bucketClass);
    }
  }

  private void createBucket(Class<?> bucketClass) {

    if (this.bucketMap.containsKey(bucketClass)) {
      throw new RuntimeException("Attempted to register duplicate bucket class: " + bucketClass);
    }

    this.bucketMap.put(bucketClass, new ArrayList<>());
  }

  public <T> void add(T bucketItem) {
    Class<?> tClass = bucketItem.getClass();

    //noinspection unchecked
    this.bucketMap
        .keySet()
        .stream()
        .filter(aClass -> aClass.isAssignableFrom(tClass))
        .forEach(aClass -> {
          //noinspection unchecked
          List<? super T> objects = (List<? super T>) this.bucketMap.get(aClass);
          objects.add(bucketItem);
        });
  }

  public <T> List<T> getBucket(Class<T> tClass) {
    //noinspection unchecked
    return (List<T>) this.bucketMap.get(tClass);
  }
}
