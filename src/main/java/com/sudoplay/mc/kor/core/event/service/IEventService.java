package com.sudoplay.mc.kor.core.event.service;

/**
 * Created by codetaylor on 11/3/2016.
 */
public interface IEventService {
  void subscribe(Object object);

  void publish(Object object);
}
