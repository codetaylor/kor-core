package com.sudoplay.mc.kor.core.event.service;

import com.sudoplay.mc.kor.core.log.LoggerService;

import java.lang.reflect.Method;

/**
 * Created by codetaylor on 11/3/2016.
 */
public class LogErrorEventExceptionHandler implements
    IEventExceptionHandler {

  private LoggerService loggerService;

  public LogErrorEventExceptionHandler(LoggerService loggerService) {
    this.loggerService = loggerService;
  }

  @Override
  public void onException(Exception e, Method method, Object event) {
    this.loggerService.error(
        "Unable to invoke method [%s] with event object [%s]", method, event, e
    );
  }
}
