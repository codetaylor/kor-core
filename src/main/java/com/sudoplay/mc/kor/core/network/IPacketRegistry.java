package com.sudoplay.mc.kor.core.network;

import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.relauncher.Side;

/**
 * Created by codetaylor on 12/3/2016.
 */
public interface IPacketRegistry {
  <REQ extends IMessage, REPLY extends IMessage> IPacketRegistry register(
      Class<? extends IMessageHandler<REQ, REPLY>> messageHandler,
      Class<REQ> requestMessageType,
      Side side
  );
}
