package com.sudoplay.mc.kor.core.recipe;

import com.sudoplay.mc.kor.core.recipe.exception.MalformedRecipeItemException;
import com.sudoplay.mc.kor.spi.block.KorSubTypedEnumBlock;
import com.sudoplay.mc.kor.spi.item.ISubType;
import com.sudoplay.mc.kor.spi.item.KorSubTypedItem;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.IForgeRegistryEntry;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by sk3lls on 11/16/2016.
 */
public class RecipeItemWhiteList {

  private List<String> whiteList;
  private String modId;
  private RecipeItemParser recipeItemParser;

  public RecipeItemWhiteList(
      String modId
  ) {
    this.modId = modId;
    this.whiteList = new ArrayList<>();
    this.recipeItemParser = new RecipeItemParser();
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

    try {
      ParseResult parse = this.recipeItemParser.parse(string);

      if (parse.getMeta() == OreDictionary.WILDCARD_VALUE) {
        // match with wildcard
        String pattern = "^" + parse.getDomain() + ":" + parse.getPath() + ":\\d+$";

        for (String whiteListedItem : this.whiteList) {

          if (whiteListedItem.matches(pattern)) {
            return true;
          }
        }
        return false;

      } else {
        return this.whiteList.contains(string);
      }

    } catch (MalformedRecipeItemException e) {
      throw new RuntimeException(e);
    }
  }
}
