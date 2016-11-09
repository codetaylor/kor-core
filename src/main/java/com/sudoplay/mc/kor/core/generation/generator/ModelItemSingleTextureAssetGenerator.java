package com.sudoplay.mc.kor.core.generation.generator;

import com.sudoplay.mc.kor.core.generation.AbstractAssetGenerator;
import com.sudoplay.mc.kor.core.generation.annotation.KorGenerateModelItemSingleTexture;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

/**
 * Created by sk3lls on 11/7/2016.
 */
public class ModelItemSingleTextureAssetGenerator extends
    AbstractAssetGenerator<KorGenerateModelItemSingleTexture> {

  private static final Logger LOG = LogManager.getLogger(ModelItemSingleTextureAssetGenerator.class.getSimpleName());

  private String outputFilePath;

  public ModelItemSingleTextureAssetGenerator(String outputFilePath) {
    this.outputFilePath = outputFilePath;
  }

  @Override
  public void generate(KorGenerateModelItemSingleTexture annotation) {

    String moduleId = annotation.moduleId();
    String name = annotation.name();
    String modId = annotation.modId();

    String content = "{\n" +
        "  \"parent\": \"" + modId + ":block/" + name + "\"\n" +
        "}";

    String filename = name + ".json";
    File file = new File(this.outputFilePath, filename);

    this.writeFile(content, file);

    LOG.info("Generated Model: " + filename);
  }
}
