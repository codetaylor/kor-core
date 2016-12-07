package com.sudoplay.mc.kor.core.generation.generator;

import com.sudoplay.mc.kor.core.generation.AbstractAssetGenerator;
import com.sudoplay.mc.kor.core.generation.annotation.KorFileCopyEntry;
import com.sudoplay.mc.kor.core.generation.annotation.KorGenerateFileCopy;
import com.sudoplay.mc.kor.core.log.LoggerService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * Created by codetaylor on 12/2/2016.
 */
public class FileCopyGenerator extends
    AbstractAssetGenerator<KorGenerateFileCopy> {

  private String assetInputPath;
  private String assetOutputPath;
  private LoggerService loggerService;

  public FileCopyGenerator(
      String assetInputPath,
      String assetOutputPath,
      LoggerService loggerService
  ) {
    this.assetInputPath = assetInputPath;
    this.assetOutputPath = assetOutputPath;
    this.loggerService = loggerService;
  }

  @Override
  public void generate(KorGenerateFileCopy annotation) {

    KorFileCopyEntry[] entries = annotation.toCopy();

    for (KorFileCopyEntry entry : entries) {
      File source = new File(this.assetInputPath, entry.source());
      File destination = new File(this.assetOutputPath, entry.destination());

      destination.getParentFile().mkdirs();

      try {
        Files.copy(source.toPath(), destination.toPath());
        this.loggerService.info(String.format("Copied: %s from %s", entry.destination(), entry.source()));

      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }
}
