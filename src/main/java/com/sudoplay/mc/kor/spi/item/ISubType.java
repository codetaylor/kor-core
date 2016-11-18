package com.sudoplay.mc.kor.spi.item;

import net.minecraft.util.IStringSerializable;

/**
 * Created by sk3lls on 10/31/2016.
 */
public interface ISubType extends
    IStringSerializable {

  String getName();

  int getMeta();
}
