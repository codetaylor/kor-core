package com.sudoplay.mc.kor.spi.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by sk3lls on 11/23/2016.
 */
@SuppressWarnings("unused")
public class FileUtils {

  public interface ILogger {

    ILogger NULL = message -> {
      //
    };

    void log(String message);
  }

  /**
   * Recursively deletes
   *
   * @param file
   * @throws IOException
   */
  public static void delete(File file) throws IOException {
    FileUtils.delete(file, ILogger.NULL);
  }

  /**
   * Recursively deletes
   *
   * @param file
   * @throws IOException
   */
  public static void delete(File file, ILogger logger) throws IOException {

    if (file.isDirectory()) {

      File[] children = file.listFiles();

      if (children == null) {
        return;
      }

      for (File child : children) {
        logger.log(String.format("Entering directory [%s]", child));
        FileUtils.delete(child, logger);
      }
    }

    if (!file.delete()) {
      throw new FileNotFoundException("Failed to delete file: " + file);
    }

    logger.log(String.format("Deleted [%s]", file.getName()));
  }

  private FileUtils() {
    //
  }
}
