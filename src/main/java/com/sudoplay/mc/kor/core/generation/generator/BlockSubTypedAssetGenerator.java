package com.sudoplay.mc.kor.core.generation.generator;

import com.sudoplay.mc.kor.core.generation.AbstractAssetGenerator;
import com.sudoplay.mc.kor.core.generation.annotation.KorGenerateBlockSubTypedAssets;
import com.sudoplay.mc.kor.core.log.LoggerService;

import java.io.File;

/**
 * Created by sk3lls on 11/7/2016.
 */
public class BlockSubTypedAssetGenerator extends
    AbstractAssetGenerator<KorGenerateBlockSubTypedAssets> {

  private String assetsPath;
  private LoggerService loggerService;

  public BlockSubTypedAssetGenerator(
      String assetsPath,
      LoggerService loggerService
  ) {
    this.assetsPath = assetsPath;
    this.loggerService = loggerService;
  }

  @Override
  public void generate(KorGenerateBlockSubTypedAssets annotation) {
    writeBlockStatesFile(annotation);
    writeBlockModelFiles(annotation);
    writeItemModelFiles(annotation);
  }

  private void writeItemModelFiles(KorGenerateBlockSubTypedAssets annotation) {

    for (String subType : annotation.subTypes()) {
      String name = annotation.name();
      String modId = annotation.modId();

      name += "_" + subType;

      String resourceName = "block/" + name;

      String content = "{\n" +
          "  \"parent\": \"" + modId + ":" + resourceName + "\"\n" +
          "}";

      String filename = "models/item/" + name + ".json";
      File file = new File(this.assetsPath, filename);

      this.writeFile(content, file);

      this.loggerService.info("Generated: " + filename);
    }
  }

  private void writeBlockModelFiles(KorGenerateBlockSubTypedAssets annotation) {

    for (String subType : annotation.subTypes()) {
      String name = annotation.name();
      String modId = annotation.modId();

      name += "_" + subType;

      String content = "{\n" +
          "  \"parent\": \"block/cube_all\",\n" +
          "  \"textures\": {\n" +
          "    \"all\": \"" + modId + ":blocks/" + name + "\"\n" +
          "  }\n" +
          "}";

      String filename = "models/block/" + name + ".json";
      File file = new File(this.assetsPath, filename);

      this.writeFile(content, file);

      this.loggerService.info("Generated: " + filename);
    }
  }

  private void writeBlockStatesFile(KorGenerateBlockSubTypedAssets annotation) {
    String name = annotation.name();
    String modId = annotation.modId();
    String property = annotation.property();
    String[] subTypes = annotation.subTypes();

    String content = "{\n" +
        "  \"variants\": {\n" +
        getVariantEntries(property, modId, name, subTypes) +
        "  }\n" +
        "}";

    String filename = "blockstates/" + name + ".json";
    File file = new File(this.assetsPath, filename);

    this.writeFile(content, file);

    this.loggerService.info("Generated: " + filename);
  }

  private String getVariantEntries(String property, String modId, String prefix, String[] metalTypes) {
    StringBuilder sb = new StringBuilder();
    int index = 0;
    int lastIndex = metalTypes.length - 1;

    for (String metalType : metalTypes) {
      boolean isLast = index == lastIndex;
      sb.append(getVariantEntry(property, metalType, modId, prefix + "_" + metalType, isLast));
      index += 1;
    }
    return sb.toString();
  }

  private String getVariantEntry(String property, String type, String modId, String name, boolean isLast) {
    return "    \"" + property + "=" + type + "\": {\n" +
        "      \"model\": \"" + modId + ":" + name + "\"\n" +
        "    }" + ((!isLast) ? "," : "") + "\n";
  }
}
