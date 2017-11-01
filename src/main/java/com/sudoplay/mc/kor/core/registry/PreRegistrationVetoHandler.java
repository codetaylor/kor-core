package com.sudoplay.mc.kor.core.registry;

import com.sudoplay.mc.kor.core.log.LoggerService;
import com.sudoplay.mc.kor.core.registry.service.IRegistryServicePreRegistrationHook;
import com.sudoplay.mc.kor.spi.event.external.KorExternalEvent;
import net.minecraftforge.common.MinecraftForge;

/**
 * Created by codetaylor on 11/8/2016.
 */
public class PreRegistrationVetoHandler
    implements IRegistryServicePreRegistrationHook {

  private LoggerService loggerService;

  public PreRegistrationVetoHandler(
      LoggerService loggerService
  ) {

    this.loggerService = loggerService;
  }

  @Override
  public boolean onPreRegister(Object object) {

    KorExternalEvent.OnPotentialRegistrationEvent potentialRegistrationEvent;
    Class<?> potentialClass;

    if (object instanceof Class) {
      potentialClass = (Class<?>) object;

    } else {
      potentialClass = object.getClass();
    }

    // TODO: hook this here to veto registrations
    boolean potentialClassShouldBeRegistered = true;

    if (!potentialClassShouldBeRegistered) {
      return false;
    }

    potentialRegistrationEvent = new KorExternalEvent.OnPotentialRegistrationEvent(potentialClass);
    potentialClassShouldBeRegistered = !MinecraftForge.EVENT_BUS.post(potentialRegistrationEvent);

    if (!potentialClassShouldBeRegistered) {
      this.loggerService.info("Registration of [%s] cancelled by event", potentialClass.getSimpleName());
    }

    return potentialClassShouldBeRegistered;
  }

}
