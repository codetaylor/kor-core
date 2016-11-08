package com.sudoplay.mc.kor.core.generation.generator;

import com.sudoplay.mc.kor.core.generation.AbstractAssetGenerator;
import com.sudoplay.mc.kor.core.generation.annotation.KorGenerateBlockStatesSingleVariant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

/**
 * Created by sk3lls on 11/7/2016.
 */
public class BlockStatesSingleVariantAssetGenerator extends
    AbstractAssetGenerator<KorGenerateBlockStatesSingleVariant> {

  private static final Logger LOG = LogManager.getLogger(BlockStatesSingleVariantAssetGenerator.class.getSimpleName());

  private String outputFilePath;

  public BlockStatesSingleVariantAssetGenerator(String outputFilePath) {
    this.outputFilePath = outputFilePath;
  }

  @Override
  public void generate(KorGenerateBlockStatesSingleVariant annotation) {

    String moduleId = annotation.moduleId();
    String name = annotation.name();
    String modId = annotation.modId();

    String content = "{\n" +
        "  \"variants\": {\n" +
        "    \"normal\": {\n" +
        "      \"model\": \"" + modId + ":" + moduleId + "/" + name + "\"\n" +
        "    }\n" +
        "  }\n" +
        "}";

    //this.blockStatesPath = "subprojects/kor-metals/src/main/resources/assets/ctkormetals/blockstates";
    String filename = moduleId + "/" + name + ".json";
    File file = new File(this.outputFilePath, filename);

    this.writeFile(content, file);

    LOG.info("Generated BlockStates: " + filename);
  }
}
