package com.sudoplay.mc.kor.core.generation.generator;

import com.sudoplay.mc.kor.core.generation.AbstractAssetGenerator;
import com.sudoplay.mc.kor.core.generation.annotation.KorGenerateBlockSubTypedAssets;
import com.sudoplay.mc.kor.core.generation.annotation.KorGenerateItemSubTypedAssets;
import com.sudoplay.mc.kor.core.log.LoggerService;

import java.io.File;

/**
 * Created by sk3lls on 11/7/2016.
 */
public class ItemSubTypedAssetGenerator extends
    AbstractAssetGenerator<KorGenerateItemSubTypedAssets> {

  private String assetsPath;
  private LoggerService loggerService;

  public ItemSubTypedAssetGenerator(
      String assetsPath,
      LoggerService loggerService
  ) {
    this.assetsPath = assetsPath;
    this.loggerService = loggerService;
  }

  @Override
  public void generate(KorGenerateItemSubTypedAssets annotation) {
    writeItemModelFiles(annotation);
  }

  private void writeItemModelFiles(KorGenerateItemSubTypedAssets annotation) {

    for (String subType : annotation.subTypes()) {
      String name = annotation.name();
      String modId = annotation.modId();

      name += "_" + subType;

      String content = "{\n" +
          "  \"parent\": \"item/generated\",\n" +
          "  \"textures\": {\n" +
          "    \"layer0\": \"" + modId + ":items/" + name + "\"\n" +
          "  }\n" +
          "}";

      String filename = "models/item/" + name + ".json";
      File file = new File(this.assetsPath, filename);

      this.writeFile(content, file);

      this.loggerService.info("Generated: " + filename);
    }
  }
}
