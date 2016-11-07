package com.sudoplay.mc.kor.core.registry.container;

/**
 * Created by sk3lls on 10/30/2016.
 */
public interface IRegistryContainer<R> {

  void register(R registerable);

  <E extends R> E get(Class<E> registerableClass);
}

