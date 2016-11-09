package com.sudoplay.mc.kor.core;

import javax.annotation.Nullable;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by sk3lls on 11/6/2016.
 */
public class ImageSlicer {

  public void sliceImage(
      InputStream inputStream,
      String[] filenames,
      @Nullable int[] crop, // int[4]{left, top, right, bottom}
      int sliceWidth,
      int sliceHeight,
      File outputDir
  ) throws IOException {
    BufferedImage image;
    Slice[] imageSlices;

    image = ImageIO.read(inputStream);

    int width = image.getWidth();
    int height = image.getHeight();

    int cols = width / 16;
    int rows = height / 16;

    System.out.println("rows " + rows + ", cols " + cols);

    if (crop == null) {
      crop = new int[]{0, 0, cols, rows};
    }

    int expectedLength = (crop[2] - crop[0]) * (crop[3] - crop[1]);

    if (filenames.length < expectedLength) {
      throw new IllegalArgumentException("The filename array must be at least " + expectedLength + " elements long, was " + filenames.length);
    }

    imageSlices = getImageSlices(image, sliceWidth, sliceHeight, crop, cols, filenames);

    for (int i = 0; i < imageSlices.length; i++) {
      File output = new File(outputDir, imageSlices[i].getFilename() + ".png");
      System.out.println("ImageIO.write(images[" + i + "], \"png\", " + output + ");");
      ImageIO.write(imageSlices[i].getImage(), "png", output);
    }
  }

  private Slice[] getImageSlices(
      BufferedImage image,
      int sliceWidth,
      int sliceHeight,
      int[] crop,
      int cols,
      String[] filenames
  ) {
    Slice[] imageSlices;

    int sliceCountX = crop[2] - crop[0];
    int sliceCountY = crop[3] - crop[1];

    imageSlices = new Slice[sliceCountX * sliceCountY];
    int index = 0;

    for (int x = crop[0]; x < crop[2]; x++) {

      for (int y = crop[1]; y < crop[3]; y++) {
        BufferedImage imageSlice = new BufferedImage(sliceWidth, sliceHeight, image.getType());
        Graphics2D gr = imageSlice.createGraphics();

        gr.drawImage(
            image,

            0,
            0,
            sliceWidth,
            sliceHeight,

            sliceWidth * x,
            sliceHeight * y,
            sliceWidth * x + sliceWidth,
            sliceHeight * y + sliceHeight,

            null
        );
        gr.dispose();

        String filename = filenames[x + cols * y];

        imageSlices[index] = new Slice(imageSlice, filename);

        index += 1;
      }
    }

    return imageSlices;
  }

  private class Slice {
    private BufferedImage image;
    private String filename;

    private Slice(BufferedImage image, String filename) {
      this.image = image;
      this.filename = filename;
    }

    BufferedImage getImage() {
      return image;
    }

    String getFilename() {
      return filename;
    }
  }

}
