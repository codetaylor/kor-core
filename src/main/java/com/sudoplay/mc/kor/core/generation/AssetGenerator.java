package com.sudoplay.mc.kor.core.generation;

import com.sudoplay.mc.kor.core.event.service.EventService;
import com.sudoplay.mc.kor.core.event.service.LogErrorEventExceptionHandler;
import com.sudoplay.mc.kor.core.generation.annotation.*;
import com.sudoplay.mc.kor.core.generation.generator.*;
import com.sudoplay.mc.kor.core.log.LoggerService;
import com.sudoplay.mc.kor.core.registry.service.IRegistryService;
import com.sudoplay.mc.kor.spi.IKorModule;
import com.sudoplay.mc.kor.spi.event.OnRegisterBlocksEvent;
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

  private String assetPath;

  private LangEntriesGenerator langEntriesGenerator;

  public AssetGenerator(String assetPath) {
    this.assetPath = assetPath;
    this.langEntriesGenerator = new LangEntriesGenerator(assetPath);
  }

  public void generate(IKorModule... modules) {

    LoggerService loggerService;
    LogErrorEventExceptionHandler eventExceptionHandler;
    EventService eventService;

    loggerService = new LoggerService(LOG);
    eventExceptionHandler = new LogErrorEventExceptionHandler(loggerService);
    eventService = new EventService(eventExceptionHandler);

    for (IKorModule module : modules) {
      eventService.subscribe(module);
    }

    eventService.publish(new OnRegisterBlocksEvent(new Generator(this.assetPath)));
    this.langEntriesGenerator.flush();
  }

  private class Generator implements
      IRegistryService {

    private Map<Class<? extends Annotation>, AbstractAssetGenerator> map;

    Generator(String assetPath) {
      this.map = new LinkedHashMap<>();

      this.map.put(
          KorGenerateBlockSubTypedAssets.class,
          new BlockSubTypedAssetGenerator(assetPath)
      );

      this.map.put(
          KorGenerateBlockStatesSingleVariant.class,
          new BlockStatesSingleVariantAssetGenerator(assetPath)
      );

      this.map.put(
          KorGenerateModelBlockSingleTexture.class,
          new ModelBlockSingleTextureAssetGenerator(assetPath)
      );

      this.map.put(
          KorGenerateModelItemSingleTexture.class,
          new ModelItemSingleTextureAssetGenerator(assetPath)
      );

      this.map.put(
          KorGenerateLangEntries.class,
          AssetGenerator.this.langEntriesGenerator
      );
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


