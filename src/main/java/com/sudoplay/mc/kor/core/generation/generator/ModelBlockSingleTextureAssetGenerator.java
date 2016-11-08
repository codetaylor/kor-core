package com.sudoplay.mc.kor.core.generation.generator;

import com.sudoplay.mc.kor.core.generation.AbstractAssetGenerator;
import com.sudoplay.mc.kor.core.generation.annotation.KorGenerateModelBlockSingleTexture;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

/**
 * Created by sk3lls on 11/7/2016.
 */
public class ModelBlockSingleTextureAssetGenerator extends
    AbstractAssetGenerator<KorGenerateModelBlockSingleTexture> {

  private static final Logger LOG = LogManager.getLogger(ModelBlockSingleTextureAssetGenerator.class.getSimpleName());

  private String outputFilePath;

  public ModelBlockSingleTextureAssetGenerator(String outputFilePath) {
    this.outputFilePath = outputFilePath;
  }

  @Override
  public void generate(KorGenerateModelBlockSingleTexture annotation) {

    String moduleId = annotation.moduleId();
    String name = annotation.name();
    String modId = annotation.modId();

    String content = "{\n" +
        "  \"parent\": \"block/cube_all\",\n" +
        "  \"textures\": {\n" +
        "    \"all\": \"" + modId + ":" + moduleId + "/blocks/" + name + "\"\n" +
        "  }\n" +
        "}";

    String filename = moduleId + "/" + name + ".json";
    File file = new File(this.outputFilePath, filename);

    this.writeFile(content, file);

    LOG.info("Generated Model: " + filename);
  }
}
