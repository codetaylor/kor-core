package com.sudoplay.mc.kor.core.event.service;

import com.google.common.eventbus.Subscribe;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sk3lls on 11/3/2016.
 */
public class EventService implements IEventService {

  private Map<Class<?>, List<EventDelegate>> eventDelegateMap;
  private IEventExceptionHandler eventExceptionHandler;

  public EventService(
      IEventExceptionHandler eventExceptionHandler
  ) {
    this.eventDelegateMap = new HashMap<>();
    this.eventExceptionHandler = eventExceptionHandler;
  }

  @Override
  public void subscribe(Object object) {

    Method[] methods = object.getClass().getMethods();

    for (Method method : methods) {
      Subscribe annotation = method.getAnnotation(Subscribe.class);
      boolean moduleHasAnnotation = annotation != null;

      if (moduleHasAnnotation) {
        Class<?>[] parameterTypes = method.getParameterTypes();

        if (parameterTypes.length != 1) {
          // used exception text from Forge here
          throw new IllegalArgumentException(
              "Method " + method + " has @SubscribeEvent annotation, but requires " + parameterTypes.length +
                  " arguments.  Event handler methods must require a single argument."
          );
        }

        Class<?> parameterType = parameterTypes[0];
        List<EventDelegate> eventDelegateList = this.eventDelegateMap.get(parameterType);

        if (eventDelegateList == null) {
          eventDelegateList = new ArrayList<>();
          this.eventDelegateMap.put(parameterType, eventDelegateList);
        }

        eventDelegateList.add(new EventDelegate(method, object));
      }
    }
  }

  @Override
  public void publish(Object object) {
    List<EventDelegate> eventDelegateList = this.eventDelegateMap.get(object.getClass());

    if (eventDelegateList != null) {

      for (EventDelegate eventDelegate : eventDelegateList) {

        try {
          eventDelegate.call(object);
        } catch (InvocationTargetException | IllegalAccessException e) {
          this.eventExceptionHandler.onException(e, eventDelegate.getMethod(), object);
        }
      }
    }
  }

}
