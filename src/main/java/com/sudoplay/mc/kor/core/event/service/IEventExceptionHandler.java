package com.sudoplay.mc.kor.core.event.service;

import java.lang.reflect.Method;

/**
 * Created by sk3lls on 11/3/2016.
 */
public interface IEventExceptionHandler {

  void onException(Exception e, Method method, Object event);
}
