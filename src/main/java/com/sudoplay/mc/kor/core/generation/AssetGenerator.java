package com.sudoplay.mc.kor.core.generation;

import com.sudoplay.mc.kor.core.event.service.EventService;
import com.sudoplay.mc.kor.core.event.service.LogErrorEventExceptionHandler;
import com.sudoplay.mc.kor.core.generation.annotation.*;
import com.sudoplay.mc.kor.core.generation.generator.*;
import com.sudoplay.mc.kor.core.log.LoggerService;
import com.sudoplay.mc.kor.core.registry.service.IRegistryService;
import com.sudoplay.mc.kor.spi.IKorModule;
import com.sudoplay.mc.kor.spi.event.internal.OnRegisterBlocksEvent;
import com.sudoplay.mc.kor.spi.event.internal.OnRegisterCreativeTabsEvent;
import com.sudoplay.mc.kor.spi.event.internal.OnRegisterItemsEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.annotation.Annotation;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by sk3lls on 11/7/2016.
 */
public class AssetGenerator {

  private static final Logger LOG = LogManager.getLogger(AssetGenerator.class.getSimpleName());

  private String assetInputPath;
  private String assetOutputPath;

  private LangEntriesGenerator langEntriesGenerator;
  private LoggerService loggerService;

  public AssetGenerator(
      String assetInputPath,
      String assetOutputPath
  ) {
    this.assetInputPath = assetInputPath;
    this.assetOutputPath = assetOutputPath;
    this.loggerService = new LoggerService(LOG);
    this.langEntriesGenerator = new LangEntriesGenerator(assetOutputPath, this.loggerService);
  }

  public void generate(IKorModule... modules) {

    LogErrorEventExceptionHandler eventExceptionHandler;
    EventService eventService;

    eventExceptionHandler = new LogErrorEventExceptionHandler(loggerService);
    eventService = new EventService(eventExceptionHandler);

    for (IKorModule module : modules) {
      eventService.subscribe(module);
    }

    Generator generator = new Generator(this.assetInputPath, this.assetOutputPath, loggerService);
    eventService.publish(new OnRegisterCreativeTabsEvent(generator));
    eventService.publish(new OnRegisterBlocksEvent(generator));
    eventService.publish(new OnRegisterItemsEvent(generator));
    this.langEntriesGenerator.flush();
  }

  private class Generator implements
      IRegistryService {

    private Map<Class<? extends Annotation>, AbstractAssetGenerator> map;

    Generator(
        String assetInputPath,
        String assetOutputPath,
        LoggerService loggerService
    ) {
      this.map = new LinkedHashMap<>();

      this.map.put(
          KorGenerateBlockSubTypedAssets.class,
          new BlockSubTypedAssetGenerator(assetOutputPath, loggerService)
      );

      this.map.put(
          KorGenerateBlockAssets.class,
          new BlockAssetGenerator(assetOutputPath, loggerService)
      );

      this.map.put(
          KorGenerateModelItemSingleTexture.class,
          new ModelItemSingleTextureAssetGenerator(assetOutputPath, loggerService)
      );

      this.map.put(
          KorGenerateLangEntries.class,
          AssetGenerator.this.langEntriesGenerator
      );

      this.map.put(
          KorGenerateImageSlices.class,
          new ImageSliceGenerator(assetInputPath, assetOutputPath, new ImageSlicer(), loggerService)
      );

      this.map.put(
          KorGenerateItemSubTypedAssets.class,
          new ItemSubTypedAssetGenerator(assetOutputPath, loggerService)
      );
    }

    @Override
    public IRegistryService register(Object... registerableObjects) {

      for (Object registerableObject : registerableObjects) {
        this.register(registerableObject);
      }
      return this;
    }

    @Override
    public <R> R get(Class<R> registerableClass) {
      throw new UnsupportedOperationException();
    }

    @Override
    public IRegistryService register(Object registerableObject) {

      Class<?> aClass;

      if (registerableObject instanceof Class) {
        aClass = (Class<?>) registerableObject;

      } else {
        aClass = registerableObject.getClass();
      }

      for (Annotation annotation : aClass.getAnnotations()) {

        for (Class<? extends Annotation> annotationClass : this.map.keySet()) {

          if (annotationClass.isAssignableFrom(annotation.getClass())) {
            //noinspection unchecked
            this.map.get(annotationClass).generate(annotation);
          }
        }
      }

      return this;
    }
  }
}


