package com.sudoplay.mc.kor.spi.block;

import com.sudoplay.mc.kor.spi.item.ISubType;
import net.minecraft.util.IStringSerializable;

import java.util.Collection;

/**
 * Created by sk3lls on 1/25/2017.
 */
public interface IKorSubTypedEnumBlock<E extends Enum<E> & ISubType & IStringSerializable> {
  Collection<E> getSubTypes();

  ISubType getSubType(int meta);
}
