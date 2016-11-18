package com.sudoplay.mc.kor.core.recipe.furnace;

import com.sudoplay.mc.kor.core.recipe.ParseResult;
import com.sudoplay.mc.kor.core.recipe.RecipeItemNotFoundInRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class RecipeFurnaceRegistrationDelegate {

  public void registerFurnaceRecipe(RecipeFurnaceParseResults results) throws RecipeItemNotFoundInRegistry {

    ParseResult outputParseResult = results.getOutputParseResult();
    ResourceLocation resourceLocation = new ResourceLocation(outputParseResult.getDomain(), outputParseResult.getPath());
    Item item = Item.REGISTRY.getObject(resourceLocation);

    if (item == null) {
      throw new RecipeItemNotFoundInRegistry(String.format("Unable to get item [%s] from Item.REGISTRY, aborting recipe registration", resourceLocation));
    }

    ItemStack outputItemStack = new ItemStack(item, outputParseResult.getQuantity(), outputParseResult.getMeta());

    ParseResult inputParseResult = results.getInputParseResult();
    resourceLocation = new ResourceLocation(inputParseResult.getDomain(), inputParseResult.getPath());
    item = Item.REGISTRY.getObject(resourceLocation);

    if (item == null) {
      throw new RecipeItemNotFoundInRegistry(String.format("Unable to get item [%s] from Item.REGISTRY, aborting recipe registration", resourceLocation));
    }

    ItemStack inputItemStack = new ItemStack(item, inputParseResult.getQuantity(), inputParseResult.getMeta());

    // register
    GameRegistry.addSmelting(inputItemStack, outputItemStack, results.getXp());
  }
}