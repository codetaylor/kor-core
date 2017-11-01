package com.sudoplay.mc.kor.spi.block;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.tileentity.TileEntity;

import java.util.Map;

/**
 * Created by codetaylor on 11/29/2016.
 */
public interface IKorTileEntityProvider
    extends ITileEntityProvider {

  Map<String, Class<? extends TileEntity>> getTileEntityMap(Map<String, Class<? extends TileEntity>> result);

}
