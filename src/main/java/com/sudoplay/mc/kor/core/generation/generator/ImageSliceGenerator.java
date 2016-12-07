package com.sudoplay.mc.kor.core.generation.generator;

import com.sudoplay.mc.kor.core.generation.AbstractAssetGenerator;
import com.sudoplay.mc.kor.core.generation.ImageSlicer;
import com.sudoplay.mc.kor.core.generation.annotation.KorGenerateImageSlices;
import com.sudoplay.mc.kor.core.generation.annotation.KorImageSliceEntry;
import com.sudoplay.mc.kor.core.log.LoggerService;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by codetaylor on 11/8/2016.
 */
public class ImageSliceGenerator extends
    AbstractAssetGenerator<KorGenerateImageSlices> {

  private String assetInputPath;
  private String assetOutputPath;
  private Map<String, BufferedImage> imageMap;
  private ImageSlicer imageSlicer;
  private LoggerService loggerService;

  public ImageSliceGenerator(
      String assetInputPath,
      String assetOutputPath,
      ImageSlicer imageSlicer,
      LoggerService loggerService
  ) {
    this.assetInputPath = assetInputPath;
    this.assetOutputPath = assetOutputPath;
    this.imageSlicer = imageSlicer;
    this.loggerService = loggerService;
    this.imageMap = new HashMap<>();
  }

  @Override
  public void generate(KorGenerateImageSlices annotation) {

    KorImageSliceEntry[] slices = annotation.slices();

    for (KorImageSliceEntry entry : slices) {
      int col = entry.col();
      int row = entry.row();
      String source = entry.source();
      String target = entry.target();

      BufferedImage bufferedImage = this.imageMap.get(source);

      if (bufferedImage == null) {
        File filename = new File(this.assetInputPath, source);
        FileInputStream inputStream = null;

        try {
          inputStream = new FileInputStream(filename);
          bufferedImage = ImageIO.read(inputStream);
          this.imageMap.put(source, bufferedImage);
          this.loggerService.info(String.format("Cached: %s", filename));

        } catch (IOException e) {
          throw new RuntimeException(e);

        } finally {

          if (inputStream != null) {
            try {
              inputStream.close();
            } catch (IOException e) {
              //
            }
          }
        }
      }

      try {
        File file;
        String path;

        file = new File(this.assetOutputPath, "textures");
        file = new File(file, target);
        path = file.getPath();
        this.imageSlicer.sliceImage(bufferedImage, path, 16, 16, col, row);
        this.loggerService.info(String.format("Generated: textures/%s.png", target));

      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }
}
