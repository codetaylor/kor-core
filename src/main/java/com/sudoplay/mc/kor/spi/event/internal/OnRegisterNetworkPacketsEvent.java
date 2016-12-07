package com.sudoplay.mc.kor.spi.event.internal;

import com.sudoplay.mc.kor.core.network.IPacketRegistry;

/**
 * Created by codetaylor on 12/1/2016.
 */
public class OnRegisterNetworkPacketsEvent {

  private IPacketRegistry packetRegistry;

  public OnRegisterNetworkPacketsEvent(IPacketRegistry packetRegistry) {
    this.packetRegistry = packetRegistry;
  }

  public IPacketRegistry getPacketHandlerRegistry() {
    return packetRegistry;
  }
}
