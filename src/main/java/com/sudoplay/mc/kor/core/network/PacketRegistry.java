package com.sudoplay.mc.kor.core.network;

import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.relauncher.Side;

/**
 * Created by codetaylor on 12/3/2016.
 */
public class PacketRegistry implements
    IPacketRegistry {

  private ThreadedNetworkWrapper threadedNetworkWrapper;

  private int id = 0;

  public PacketRegistry(ThreadedNetworkWrapper threadedNetworkWrapper) {
    this.threadedNetworkWrapper = threadedNetworkWrapper;
  }

  @Override
  public <REQ extends IMessage, REPLY extends IMessage> IPacketRegistry register(
      Class<? extends IMessageHandler<REQ, REPLY>> messageHandler,
      Class<REQ> requestMessageType,
      Side side
  ) {
    this.threadedNetworkWrapper.registerMessage(messageHandler, requestMessageType, this.nextId(), side);
    return this;
  }

  private int nextId() {
    return this.id++;
  }

}
