package com.sudoplay.mc.kor.core.recipe.shapeless;

import com.sudoplay.mc.kor.core.recipe.ParseResult;
import com.sudoplay.mc.kor.core.recipe.RecipeItemNotFoundInRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import java.util.ArrayList;
import java.util.List;

public class RecipeShapelessRegistrationDelegate {

  public void registerShapelessRecipe(RecipeShapelessParseResults results) throws RecipeItemNotFoundInRegistry {

    ParseResult outputParseResult = results.getOutputParseResult();
    ResourceLocation resourceLocation = new ResourceLocation(outputParseResult.getDomain(), outputParseResult.getPath());
    Item item = Item.REGISTRY.getObject(resourceLocation);

    if (item == null) {
      throw new RecipeItemNotFoundInRegistry(String.format("Unable to get item [%s] from Item.REGISTRY, aborting recipe registration", resourceLocation));
    }

    ItemStack outputItemStack = new ItemStack(item, outputParseResult.getQuantity(), outputParseResult.getMeta());

    List<Object> parameterList = new ArrayList<>();

    for (ParseResult result : results.getInputParseResultList()) {

      if ("ore".equals(result.getDomain())) {
        parameterList.add(result.getPath());

      } else {

        item = Item.REGISTRY.getObject(new ResourceLocation(result.getDomain(), result.getPath()));

        if (item == null) {
          throw new RecipeItemNotFoundInRegistry(String.format("Unable to get item [%s] from Item.REGISTRY, aborting recipe registration", resourceLocation));
        }

        parameterList.add(new ItemStack(item, result.getQuantity(), result.getMeta()));
      }
    }

    // register
    GameRegistry.addRecipe(new ShapelessOreRecipe(outputItemStack, parameterList.toArray()));
  }
}