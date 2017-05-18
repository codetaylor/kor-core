package com.sudoplay.mc.kor.spi.event.internal;

import com.sudoplay.mc.kor.core.gui.GuiHandlerRegistry;

/**
 * Created by codetaylor on 12/1/2016.
 */
public class OnRegisterGuiHandlersEvent {

  private GuiHandlerRegistry guiHandlerRegistry;

  public OnRegisterGuiHandlersEvent(GuiHandlerRegistry guiHandlerRegistry) {
    this.guiHandlerRegistry = guiHandlerRegistry;
  }

  public GuiHandlerRegistry getGuiHandlerRegistry() {
    return this.guiHandlerRegistry;
  }

}
