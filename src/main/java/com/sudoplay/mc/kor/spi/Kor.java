package com.sudoplay.mc.kor.spi;

import com.sudoplay.mc.kor.core.config.json.ConfigService;
import com.sudoplay.mc.kor.core.config.json.IConfigService;
import com.sudoplay.mc.kor.core.config.text.ITextConfigService;
import com.sudoplay.mc.kor.core.config.text.TextConfigLoader;
import com.sudoplay.mc.kor.core.config.text.TextConfigService;
import com.sudoplay.mc.kor.core.event.service.EventService;
import com.sudoplay.mc.kor.core.event.service.IEventService;
import com.sudoplay.mc.kor.core.event.service.LogErrorEventExceptionHandler;
import com.sudoplay.mc.kor.core.log.LoggerService;
import com.sudoplay.mc.kor.core.registry.FilterBuckets;
import com.sudoplay.mc.kor.core.registry.service.RegistryService;
import com.sudoplay.mc.kor.core.registry.service.injection.RegistryObjectInjector;
import com.sudoplay.mc.kor.core.registry.service.injection.strategy.constructor.ArgConstructorStrategy;
import com.sudoplay.mc.kor.core.registry.service.injection.strategy.constructor.IConstructorStrategy;
import com.sudoplay.mc.kor.core.registry.service.injection.strategy.constructor.NoArgConstructorStrategy;
import com.sudoplay.mc.kor.core.registry.service.injection.strategy.parameter.ConfigParameterStrategy;
import com.sudoplay.mc.kor.core.registry.service.injection.strategy.parameter.IParameterStrategy;
import com.sudoplay.mc.kor.core.registry.service.injection.strategy.parameter.KorParameterStrategy;
import com.sudoplay.mc.kor.spi.config.json.KorConfigObject;
import com.sudoplay.mc.kor.spi.event.*;
import com.sudoplay.mc.kor.spi.material.KorArmorMaterial;
import com.sudoplay.mc.kor.spi.material.KorToolMaterial;
import com.sudoplay.mc.kor.spi.recipe.KorRecipeCraftingShaped;
import com.sudoplay.mc.kor.spi.registry.ForgeEventListener;
import com.sudoplay.mc.kor.spi.registry.provider.*;
import com.sudoplay.mc.kor.spi.registry.strategy.*;
import com.sudoplay.mc.kor.spi.world.KorWorldGen;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sk3lls on 11/1/2016.
 */
public abstract class Kor {

  private static final Map<String, Kor> INSTANCES = new HashMap<>();

  private Map<String, IKorModule> moduleMap;
  private LoggerService loggerService;
  private IEventService eventService;
  private ITextConfigService textConfigService;
  private IConfigService configService;
  private RegistryService registryService;
  private FilterBuckets registrationStrategyProviderBuckets;

  protected Kor() {
    String modId = this.getModId();
    INSTANCES.put(modId, this);
    this.moduleMap = new HashMap<>();

    { // Init Logging
      Logger modLog = LogManager.getLogger(modId);
      this.loggerService = new LoggerService(modLog);
      this.loggerService.info("Initialized LoggerService [%s] [%s]", modLog, modId);
    }

    { // Registration Event Service
      this.eventService = new EventService(new LogErrorEventExceptionHandler(this.loggerService));
    }
  }

  public static <T extends Kor> T getMod(String modId) {
    //noinspection unchecked
    return (T) INSTANCES.get(modId);
  }

  // --------------------------------------------------------------------------

  protected void registerModules(IKorModule... modules) {

    for (IKorModule module : modules) {
      this.eventService.subscribe(module);
      this.moduleMap.put(module.getKorModuleId(), module);
    }
  }

  // --------------------------------------------------------------------------

  /**
   * Access to all objects registered with this instance.
   *
   * @param registerableClass class of registered object
   * @param <R>               registered object
   * @return registered instance of class parameter
   */
  public <R> R get(Class<R> registerableClass) {
    return this.registryService.get(registerableClass);
  }

  public LoggerService getLoggerService() {
    return this.loggerService;
  }

  // --------------------------------------------------------------------------

  protected void onPreInitialization(FMLPreInitializationEvent event) {
    String modId = this.getModId();

    korInit(event, modId);

    { // Configuration
      this.loggerService.info("Kor Configuration Phase...");

      this.eventService.publish(new OnLoadConfigurationsEvent(
          this.textConfigService,
          this.configService
      ));
    }

    { // Registration (init)
      this.loggerService.info("Kor Registration Phase...");

      this.eventService.publish(new OnRegisterCreativeTabsEvent(this.registryService, this.textConfigService, this.configService));
      this.eventService.publish(new OnRegisterMaterialsEvent(this.registryService, this.textConfigService, this.configService));
      this.eventService.publish(new OnRegisterItemsEvent(this.registryService, this.textConfigService, this.configService));
      this.eventService.publish(new OnRegisterBlocksEvent(this.registryService, this.textConfigService, this.configService));
      this.eventService.publish(new OnRegisterRecipesEvent(this.registryService, this.textConfigService, this.configService));
      this.eventService.publish(new OnRegisterWorldGenEvent(this.registryService, this.textConfigService, this.configService));

      this.executePreInitializationStrategies(this);
    }

    { // Register Renders
      boolean isClient;

      isClient = event.getSide().isClient();

      if (isClient) {
        this.loggerService.info("Kor Client Registration Phase...");
        this.executeClientPreInitializationStrategies(this);
      }
    }
  }

  protected void onInitialization(FMLInitializationEvent event) {
    //registryServiceCallbackProvider.callRegisterEventListeners(mod);

    this.loggerService.info("Kor Register Recipes Phase...");
    this.executeRegisterRecipesStrategies(this);

    if (event.getSide().isClient()) {
      this.loggerService.info("Kor Register Model Bakery Variants Phase...");
      this.executeRegisterModelBakeryVariantStrategies(this);
    }
  }

  protected void onPostInitialization(FMLPostInitializationEvent event) {
    this.registrationStrategyProviderBuckets = null;
  }

  private void executePreInitializationStrategies(Kor kor) {
    List<KorPreInitStrategyProvider> bucket = this.registrationStrategyProviderBuckets
        .getBucket(KorPreInitStrategyProvider.class);

    for (KorPreInitStrategyProvider strategyProvider : bucket) {
      KorPreInitStrategy strategy = strategyProvider.getPreInitStrategy();
      //noinspection unchecked
      strategy.onPreInit(kor);
    }
  }

  private void executeClientPreInitializationStrategies(Kor kor) {
    List<KorClientPreInitStrategyProvider> bucket = this.registrationStrategyProviderBuckets
        .getBucket(KorClientPreInitStrategyProvider.class);

    for (KorClientPreInitStrategyProvider strategyProvider : bucket) {
      KorClientPreInitStrategy strategy = strategyProvider.getClientPreInitStrategy();
      //noinspection unchecked
      strategy.onClientPreInit(kor);
    }
  }

  private void executeRegisterModelBakeryVariantStrategies(Kor kor) {
    List<KorClientInitStrategyProvider> bucket = this.registrationStrategyProviderBuckets
        .getBucket(KorClientInitStrategyProvider.class);

    for (KorClientInitStrategyProvider strategyProvider : bucket) {
      KorClientInitStrategy strategy = strategyProvider.getClientInitStrategy();
      //noinspection unchecked
      strategy.onClientInit(kor);
    }
  }

  private void executeRegisterRecipesStrategies(Kor kor) {
    List<KorInitStrategyProvider> bucket = this.registrationStrategyProviderBuckets
        .getBucket(KorInitStrategyProvider.class);

    for (KorInitStrategyProvider strategyProvider : bucket) {
      KorInitStrategy strategy = strategyProvider.getInitStrategy();
      //noinspection unchecked
      strategy.onInit(kor);
    }
  }

  // --------------------------------------------------------------------------

  private void korInit(FMLPreInitializationEvent event, String modId) {
    File modConfigurationDirectory = new File(event.getModConfigurationDirectory(), modId);

    { // Init Configuration Services
      this.textConfigService = new TextConfigService(new TextConfigLoader(), modConfigurationDirectory);
      this.configService = new ConfigService(loggerService, modConfigurationDirectory, this.getJsonConfigsVersion());
    }

    { // Init strategy provider buckets
      this.registrationStrategyProviderBuckets = new FilterBuckets(new Class[]{
          KorRegistrationStrategyProvider.class,
          KorPreInitStrategyProvider.class,
          KorClientPreInitStrategyProvider.class,
          KorClientInitStrategyProvider.class,
          KorInitStrategyProvider.class
      });
    }

    { // Init registry services
      RegistryObjectInjector registryObjectInjector = new RegistryObjectInjector(new IConstructorStrategy[]{
          new NoArgConstructorStrategy(),
          new ArgConstructorStrategy(new LinkedHashMap<Class<?>, IParameterStrategy>() {{
            put(KorConfigObject.class, new ConfigParameterStrategy(Kor.this.configService, modConfigurationDirectory));
            put(Kor.class, new KorParameterStrategy(Kor.this));
          }})
      });

      this.registryService = new RegistryService(
          new Class[]{
              Block.class,
              CreativeTabs.class,
              Item.class,
              KorArmorMaterial.class,
              KorToolMaterial.class,
              KorRecipeCraftingShaped.class,
              KorWorldGen.class
          },
          registryObjectInjector
      );

      this.registryService.addCallback(registeredObject -> {
        this.registrationStrategyProviderBuckets.add(registeredObject);

        ForgeEventListener annotation = registeredObject.getClass().getAnnotation(ForgeEventListener.class);

        if (annotation != null) {
          MinecraftForge.EVENT_BUS.register(registeredObject);
        }

        if (registeredObject instanceof KorRegistrationStrategyProvider) {
          KorRegistrationStrategy initializationStrategy = ((KorRegistrationStrategyProvider) registeredObject).getRegistrationStrategy();
          initializationStrategy.onRegistration(this);
        }
      });
    }
  }

  // --------------------------------------------------------------------------

  public abstract String getModId();

  public abstract String getModVersion();

  public abstract String getModName();

  public abstract double getJsonConfigsVersion();

}
