package com.sudoplay.mc.kor.core.generation.generator;

import com.sudoplay.mc.kor.core.generation.AbstractAssetGenerator;
import com.sudoplay.mc.kor.core.generation.annotation.KorGenerateBlockAssets;
import com.sudoplay.mc.kor.core.log.LoggerService;

import java.io.File;

/**
 * Created by sk3lls on 11/7/2016.
 */
public class BlockAssetGenerator extends
    AbstractAssetGenerator<KorGenerateBlockAssets> {

  private String assetsPath;
  private LoggerService loggerService;

  public BlockAssetGenerator(
      String assetsPath,
      LoggerService loggerService
  ) {
    this.assetsPath = assetsPath;
    this.loggerService = loggerService;
  }

  @Override
  public void generate(KorGenerateBlockAssets annotation) {
    writeBlockStatesFile(annotation);
    writeBlockModelFiles(annotation);
    writeItemModelFiles(annotation);
  }

  private void writeItemModelFiles(KorGenerateBlockAssets annotation) {

    String name = annotation.name();
    String modId = annotation.modId();

    String content = "{\n" +
        "  \"parent\": \"" + modId + ":block/" + name + "\"\n" +
        "}";

    String filename = "models/item/" + name + ".json";
    File file = new File(this.assetsPath, filename);

    this.writeFile(content, file);

    this.loggerService.info("Generated: " + filename);
  }

  private void writeBlockModelFiles(KorGenerateBlockAssets annotation) {

    String name = annotation.name();
    String modId = annotation.modId();

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

  private void writeBlockStatesFile(KorGenerateBlockAssets annotation) {
    String name = annotation.name();
    String modId = annotation.modId();

    String content = "{\n" +
        "  \"variants\": {\n" +
        "    \"normal\": { \"model\": \"" + modId + ":" + name + "\" }\n" +
        "  }\n" +
        "}";

    String filename = "blockstates/" + name + ".json";
    File file = new File(this.assetsPath, filename);

    this.writeFile(content, file);

    this.loggerService.info("Generated: " + filename);
  }

}
