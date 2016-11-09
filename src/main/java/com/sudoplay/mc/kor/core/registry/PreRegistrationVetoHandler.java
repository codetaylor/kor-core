package com.sudoplay.mc.kor.core.registry;

import com.sudoplay.mc.kor.core.config.text.ITextConfigService;
import com.sudoplay.mc.kor.core.config.text.TextConfigData;
import com.sudoplay.mc.kor.core.log.LoggerService;
import com.sudoplay.mc.kor.core.registry.service.IRegistryServicePreRegistrationHook;
import com.sudoplay.mc.kor.spi.event.external.KorExternalEvent;
import com.sudoplay.mc.kor.spi.registry.KorRegistrationClassDependency;
import com.sudoplay.mc.kor.spi.registry.KorRegistrationTextConfigDependency;
import net.minecraftforge.common.MinecraftForge;

import java.util.HashSet;
import java.util.LinkedHashSet;

/**
 * Created by sk3lls on 11/8/2016.
 */
public class PreRegistrationVetoHandler implements
    IRegistryServicePreRegistrationHook {

  private ITextConfigService textConfigService;
  private LoggerService loggerService;

  public PreRegistrationVetoHandler(ITextConfigService textConfigService, LoggerService loggerService) {
    this.textConfigService = textConfigService;
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

    HashSet<Class<?>> trail = new LinkedHashSet<>();
    boolean potentialClassShouldBeRegistered = checkClassDependencies(potentialClass, trail);

    if (!potentialClassShouldBeRegistered) {
      this.loggerService.info("[preRegister] %s cancelled by dependency trail: %s", potentialClass.getSimpleName(), trail);
      return false;
    }

    potentialRegistrationEvent = new KorExternalEvent.OnPotentialRegistrationEvent(potentialClass);
    potentialClassShouldBeRegistered = !MinecraftForge.EVENT_BUS.post(potentialRegistrationEvent);

    if (!potentialClassShouldBeRegistered) {
      this.loggerService.info("[preRegister] %s cancelled by event", potentialClass.getSimpleName());
    }

    return potentialClassShouldBeRegistered;
  }

  private boolean checkClassDependencies(Class<?> potentialClass, HashSet<Class<?>> trail) {

    trail.add(potentialClass);

    { // check if the class has a text config dependency
      KorRegistrationTextConfigDependency annotation;
      annotation = potentialClass.getAnnotation(KorRegistrationTextConfigDependency.class);

      if (annotation != null) {
        // check boolean value
        String filename = annotation.filename();
        String category = annotation.category();
        String key = annotation.key();

        TextConfigData textConfigData = this.textConfigService.get(filename);

        if (textConfigData == null) {
          throw new IllegalStateException(String.format(
              "Class %s is annotated with @KorRegistrationTextConfigDependency, " +
                  "but the config file referenced in the annotation isn't loaded. Make sure " +
                  "the config file is loaded in the module's onLoadConfigurationsEvent.",
              potentialClass
          ));
        }

        Boolean configValue = textConfigData.getCategory(category).getBoolean(key);

        if (configValue == null) {
          throw new IllegalStateException(String.format(
              "Class %s is annotated with @KorRegistrationTextConfigDependency, " +
                  "but the config file referenced in the annotation doesn't have the " +
                  "specified key %s in category %s.",
              potentialClass,
              key,
              category
          ));
        }

        if (!configValue) {
          return false;
        }
      }
    }

    { // check if the class has a class dependency
      KorRegistrationClassDependency annotation;
      annotation = potentialClass.getAnnotation(KorRegistrationClassDependency.class);

      if (annotation != null) {
        Class<?>[] classes = annotation.dependsOn();

        for (Class<?> aClass : classes) {

          // check for cyclic dependencies
          if (trail.contains(aClass)) {
            throw new IllegalStateException(String.format(
                "Cyclic class dependency detected for class %s [trail: %s]",
                potentialClass,
                trail
            ));
          }

          if (!this.checkClassDependencies(aClass, trail)) {
            return false;
          }
        }
      }
    }

    return true;
  }

}
