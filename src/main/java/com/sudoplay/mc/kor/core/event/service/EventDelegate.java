package com.sudoplay.mc.kor.core.event.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by codetaylor on 11/3/2016.
 */
public class EventDelegate {

  private Method method;
  private Object object;

  public EventDelegate(Method method, Object object) {
    this.method = method;
    this.object = object;
  }

  public void call(Object event) throws InvocationTargetException, IllegalAccessException {
    boolean wasAccessible = this.method.isAccessible();
    this.method.setAccessible(true);
    this.method.invoke(this.object, event);
    this.method.setAccessible(wasAccessible);
  }

  public Method getMethod() {
    return method;
  }
}
