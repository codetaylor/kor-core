package com.sudoplay.mc.kor.core.recipe;

import com.sudoplay.mc.kor.spi.block.KorSubTypedEnumBlock;
import com.sudoplay.mc.kor.spi.item.ISubType;
import com.sudoplay.mc.kor.spi.item.KorSubTypedItem;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.IForgeRegistryEntry;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by sk3lls on 11/16/2016.
 */
public class RecipeItemWhiteList {

  private Set<String> whiteList;
  private String modId;

  public RecipeItemWhiteList(
      String modId
  ) {
    this.modId = modId;
    this.whiteList = new HashSet<>();
  }

  public void offer(Object object) {

    if (object instanceof KorSubTypedEnumBlock) {
      //noinspection unchecked
      Collection<ISubType> subTypes = ((KorSubTypedEnumBlock) object).getSubTypes();
      String resourcePath = ((KorSubTypedEnumBlock) object).getRegistryName().getResourcePath();

      for (ISubType subType : subTypes) {
        this.whiteList.add(this.modId + ":" + resourcePath + ":" + subType.getMeta());
      }

    } else if (object instanceof KorSubTypedItem) {
      ISubType[] subTypes = ((KorSubTypedItem) object).getSubTypes();
      String resourcePath = ((KorSubTypedItem) object).getRegistryName().getResourcePath();

      for (ISubType subType : subTypes) {
        this.whiteList.add(this.modId + ":" + resourcePath + ":" + subType.getMeta());
      }

    } else if (object instanceof IForgeRegistryEntry) {
      ResourceLocation registryName = ((IForgeRegistryEntry) object).getRegistryName();
      String resourcePath = registryName.getResourcePath();
      this.whiteList.add(this.modId + ":" + resourcePath + ":0");
    }
  }

  public boolean contains(String string) {
    return this.whiteList.contains(string);
  }
}
