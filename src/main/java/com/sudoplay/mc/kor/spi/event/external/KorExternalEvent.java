package com.sudoplay.mc.kor.spi.event.external;

import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraftforge.fml.common.eventhandler.Event;

/**
 * Created by sk3lls on 11/8/2016.
 */
public abstract class KorExternalEvent extends
    Event {

  @Cancelable
  public static class OnPotentialRegistrationEvent extends
      KorExternalEvent {

    private final Class<?> potentialClass;

    public OnPotentialRegistrationEvent(Class<?> potentialClass) {
      this.potentialClass = potentialClass;
    }

    public Class<?> getPotentialClass() {
      return potentialClass;
    }
  }

}
