package com.sudoplay.mc.kor.core.recipe.shaped;

import com.sudoplay.mc.kor.core.recipe.ParseResult;
import com.sudoplay.mc.kor.core.recipe.RecipeItemNotFoundInRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RecipeShapedRegistrationDelegate {

  public void registerShapedRecipe(RecipeShapedParseResults results) throws RecipeItemNotFoundInRegistry {
    ParseResult outputParseResult = results.getOutputParseResult();
    ResourceLocation resourceLocation = new ResourceLocation(outputParseResult.getDomain(), outputParseResult.getPath());
    Item item = Item.REGISTRY.getObject(resourceLocation);

    if (item == null) {
      throw new RecipeItemNotFoundInRegistry(String.format("Unable to get item %s from Item.REGISTRY, aborting recipe registration", resourceLocation));
    }

    ItemStack outputItemStack = new ItemStack(item, outputParseResult.getQuantity(), outputParseResult.getMeta());

    List<ParseResult> seenList = new ArrayList<>();

    List<List<ParseResult>> inputParseResultList = results.getInputParseResultList();

    boolean[] rowsUsed = new boolean[3];

    for (int i = 0; i < inputParseResultList.size(); i++) {
      rowsUsed[i] = true;
    }

    String topRowPattern;
    String midRowPattern = null;
    String botRowPattern = null;

    List<ParseResult> topRow = inputParseResultList.get(0);
    topRowPattern = processRow(topRow, seenList);

    if (rowsUsed[1]) {
      List<ParseResult> midRow = inputParseResultList.get(1);
      midRowPattern = processRow(midRow, seenList);
    }

    if (rowsUsed[2]) {
      List<ParseResult> botRow = inputParseResultList.get(2);
      botRowPattern = processRow(botRow, seenList);
    }

    List<Object> parameterList = new ArrayList<>();

    for (ParseResult result : seenList) {

      if ("ore".equals(result.getDomain())) {
        parameterList.add(result.getPath());

      } else {
        resourceLocation = new ResourceLocation(result.getDomain(), result.getPath());
        item = Item.REGISTRY.getObject(resourceLocation);

        if (item == null) {
          throw new RecipeItemNotFoundInRegistry(String.format("Unable to get item [%s] from Item.REGISTRY, aborting recipe registration", resourceLocation));
        }

        parameterList.add(new ItemStack(item, result.getQuantity(), result.getMeta()));
      }
    }

    List<Object> parameters = new ArrayList<>();

    parameters.add(results.isMirrored());
    parameters.add(topRowPattern);

    if (rowsUsed[1]) {
      parameters.add(midRowPattern);
    }

    if (rowsUsed[2]) {
      parameters.add(botRowPattern);
    }

    for (int i = 0; i < parameterList.size(); i++) {
      parameters.add((char) (65 + i));
      parameters.add(parameterList.get(i));
    }

    // register
    GameRegistry.addRecipe(new ShapedOreRecipe(outputItemStack, parameters.toArray()));
  }

  private String processRow(List<ParseResult> topRow, List<ParseResult> seen) {
    String rowPattern = "";

    for (ParseResult result : topRow) {

      if (result == ParseResult.NULL) {
        rowPattern += " ";
        continue;
      }

      int indexOf = this.indexOf(seen, result);
      boolean hasBeenSeen = indexOf > -1;

      if (hasBeenSeen) {
        rowPattern += (char) (65 + indexOf);

      } else {
        seen.add(result);
        rowPattern += (char) (65 + seen.size() - 1);
      }
    }

    return rowPattern;
  }

  private int indexOf(List<ParseResult> list, ParseResult parseResult) {

    for (int i = 0; i < list.size(); i++) {
      ParseResult listItem = list.get(i);

      if (listItem == parseResult) {
        return i;
      }

      if (Objects.equals(listItem.getDomain(), parseResult.getDomain())
          && Objects.equals(listItem.getPath(), parseResult.getPath())
          && Objects.equals(listItem.getMeta(), parseResult.getMeta())) {
        return i;
      }
    }

    return -1;
  }
}