package com.sudoplay.mc.kor.core.gui;

import com.sudoplay.mc.kor.core.IntMap;
import com.sudoplay.mc.kor.core.log.LoggerService;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

/**
 * Created by codetaylor on 11/30/2016.
 */
public class GuiHandlerRegistry
    implements IGuiHandler {

  private IntMap<IGuiHandler> guiHandlerIntMap;
  private LoggerService loggerService;

  private int nextId;

  public GuiHandlerRegistry(
      LoggerService loggerService
  ) {

    this.loggerService = loggerService;
    this.guiHandlerIntMap = new IntMap<>();

    this.nextId = 0;
  }

  public int register(IGuiHandler handler) {

    int id = this.nextId;
    IGuiHandler existingHandlerForId = this.guiHandlerIntMap.get(id);

    if (existingHandlerForId != null) {
      this.loggerService.error(
          "Skipped registration of guiHandler [%s], id [%d] is already registered to [%s]!",
          handler.getClass(),
          id,
          existingHandlerForId.getClass()
      );
      return -1;

    } else {
      this.guiHandlerIntMap.put(id, handler);
      this.nextId += 1;
      return id;
    }

  }

  @Override
  public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

    IGuiHandler handler = this.guiHandlerIntMap.get(ID);

    if (handler != null) {
      return handler.getServerGuiElement(ID, player, world, x, y, z);

    } else {
      return null;
    }
  }

  @Override
  public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

    IGuiHandler handler = this.guiHandlerIntMap.get(ID);

    if (handler != null) {
      return handler.getClientGuiElement(ID, player, world, x, y, z);

    } else {
      return null;
    }
  }
}
