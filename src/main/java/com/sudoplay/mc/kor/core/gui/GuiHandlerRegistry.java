package com.sudoplay.mc.kor.core.gui;

import com.sudoplay.mc.kor.core.IntMap;
import com.sudoplay.mc.kor.core.log.LoggerService;
import com.sudoplay.mc.kor.core.registry.service.injection.RegistryObjectInjector;
import com.sudoplay.mc.kor.spi.gui.KorGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by codetaylor on 11/30/2016.
 */
public class GuiHandlerRegistry implements
    IGuiHandler {

  private IntMap<KorGuiHandler> guiHandlerIntMap;
  private LoggerService loggerService;
  private RegistryObjectInjector registryObjectInjector;

  public GuiHandlerRegistry(
      LoggerService loggerService,
      RegistryObjectInjector registryObjectInjector
  ) {
    this.loggerService = loggerService;
    this.registryObjectInjector = registryObjectInjector;
    this.guiHandlerIntMap = new IntMap<>();
  }

  public void register(Class... registerableClasses) {

    for (Class registerableClass : registerableClasses) {

      try {
        KorGuiHandler guiHandler = (KorGuiHandler) this.registryObjectInjector.createInjectedObject(registerableClass);
        int id = guiHandler.getId();
        KorGuiHandler existingHandlerForId = this.guiHandlerIntMap.get(id);

        if (existingHandlerForId != null) {
          this.loggerService.error("Skipped registration of guiHandler [%s], id [%d] is already registered to [%s]!", guiHandler.getClass(), id, existingHandlerForId.getClass());

        } else {
          this.guiHandlerIntMap.put(id, guiHandler);
        }

      } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
        throw new RuntimeException(e);
      }
    }
  }

  @Override
  public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
    KorGuiHandler handler = this.guiHandlerIntMap.get(ID);

    if (handler != null) {
      return handler.getServerGuiElement(ID, player, world, x, y, z);

    } else {
      return null;
    }
  }

  @Override
  public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
    KorGuiHandler handler = this.guiHandlerIntMap.get(ID);

    if (handler != null) {
      return handler.getClientGuiElement(ID, player, world, x, y, z);

    } else {
      return null;
    }
  }
}
