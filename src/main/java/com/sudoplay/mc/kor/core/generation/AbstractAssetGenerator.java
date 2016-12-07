package com.sudoplay.mc.kor.core.generation;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.annotation.Annotation;

/**
 * Created by codetaylor on 11/7/2016.
 */
public abstract class AbstractAssetGenerator<A extends Annotation> implements
    IAssetGenerator<A> {

  protected void writeFile(String content, File file) {
    FileWriter fileWriter = null;

    try {

      file.getParentFile().mkdirs();

      fileWriter = new FileWriter(file);
      fileWriter.write(content);
      fileWriter.close();

    } catch (IOException e) {
      e.printStackTrace();

    } finally {
      if (fileWriter != null) {
        try {
          fileWriter.close();
        } catch (IOException e) {
          //
        }
      }
    }
  }
}
