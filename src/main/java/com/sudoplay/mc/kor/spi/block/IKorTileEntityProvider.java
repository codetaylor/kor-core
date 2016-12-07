package com.sudoplay.mc.kor.spi.block;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.tileentity.TileEntity;

/**
 * Created by codetaylor on 11/29/2016.
 */
public interface IKorTileEntityProvider extends
    ITileEntityProvider {

  Class<? extends TileEntity> getTileEntityClass();

  String getTileEntityName();

}
