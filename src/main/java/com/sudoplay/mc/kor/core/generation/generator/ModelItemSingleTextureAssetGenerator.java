package com.sudoplay.mc.kor.core.generation.generator;

import com.sudoplay.mc.kor.core.generation.AbstractAssetGenerator;
import com.sudoplay.mc.kor.core.generation.annotation.KorGenerateModelItemSingleTexture;
import com.sudoplay.mc.kor.core.log.LoggerService;

import java.io.File;

/**
 * Created by sk3lls on 11/7/2016.
 */
public class ModelItemSingleTextureAssetGenerator extends
    AbstractAssetGenerator<KorGenerateModelItemSingleTexture> {

  private String outputFilePath;
  private LoggerService loggerService;

  public ModelItemSingleTextureAssetGenerator(
      String outputFilePath,
      LoggerService loggerService
  ) {
    this.outputFilePath = outputFilePath;
    this.loggerService = loggerService;
  }

  @Override
  public void generate(KorGenerateModelItemSingleTexture annotation) {
    String name = annotation.name();
    String modId = annotation.modId();

    String content = "{\n" +
        "  \"parent\": \"item/generated\",\n" +
        "  \"textures\": {\n" +
        "    \"layer0\": \"" + modId + ":items/" + name + "\"\n" +
        "  }\n" +
        "}";

    String filename = "models/item/" + name + ".json";
    File file = new File(this.outputFilePath, filename);

    this.writeFile(content, file);

    this.loggerService.info("Generated: " + filename);
  }
}
