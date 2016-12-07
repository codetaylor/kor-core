package com.sudoplay.mc.kor.spi.network;

import com.google.common.reflect.TypeToken;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * https://github.com/SleepyTrousers/EnderCore/blob/1.10/src/main/java/com/enderio/core/common/network/MessageTileEntity.java
 * <p>
 * Created by codetaylor on 12/3/2016.
 */
public abstract class KorMessageTileEntity<T extends TileEntity> implements
    IMessage {

  private long pos;

  protected KorMessageTileEntity() {
    // serialization
  }

  protected KorMessageTileEntity(T tile) {
    this.pos = tile.getPos().toLong();
  }

  @Override
  public void toBytes(ByteBuf buf) {
    buf.writeLong(this.pos);
  }

  @Override
  public void fromBytes(ByteBuf buf) {
    this.pos = buf.readLong();
  }

  public BlockPos getPos() {
    return BlockPos.fromLong(this.pos);
  }

  protected T getTileEntity(World world) {

    if (world == null) {
      return null;
    }

    TileEntity tileEntity = world.getTileEntity(this.getPos());

    if (tileEntity == null) {
      return null;
    }

    TypeToken<?> typeToken = TypeToken.of(this.getClass()).resolveType(KorMessageTileEntity.class.getTypeParameters()[0]);

    if (typeToken.isAssignableFrom(tileEntity.getClass())) {
      //noinspection unchecked
      return (T) tileEntity;
    }

    return null;
  }

  protected World getWorld(MessageContext context) {
    return context.getServerHandler().playerEntity.worldObj;
  }
}
