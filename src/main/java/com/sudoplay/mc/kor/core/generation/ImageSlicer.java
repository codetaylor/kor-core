package com.sudoplay.mc.kor.core.generation;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by codetaylor on 11/6/2016.
 */
public class ImageSlicer {

  public void sliceImage(
      BufferedImage sourceImage,
      String outputFilename,
      int sliceWidth,
      int sliceHeight,
      int col,
      int row
  ) throws IOException {

    BufferedImage bufferedImageSlice;

    bufferedImageSlice = getBufferedImageSlice(sourceImage, sliceWidth, sliceHeight, col, row);

    File output = new File(outputFilename + ".png");
    output.getParentFile().mkdirs();
    ImageIO.write(bufferedImageSlice, "png", output);
  }

  private BufferedImage getBufferedImageSlice(
      BufferedImage sourceImage,
      int sliceWidth,
      int sliceHeight,
      int x,
      int y
  ) {
    BufferedImage imageSlice = new BufferedImage(sliceWidth, sliceHeight, sourceImage.getType());
    Graphics2D gr = imageSlice.createGraphics();

    gr.drawImage(
        sourceImage,

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
    return imageSlice;
  }
}
