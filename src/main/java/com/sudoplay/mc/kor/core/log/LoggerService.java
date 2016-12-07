package com.sudoplay.mc.kor.core.log;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Marker;

/**
 * Based on the following code:
 * https://github.com/Choonster/TestMod3/blob/13547b1247ee43f4c1e547fa72013580415e7c7d/src/main/java/choonster/testmod3/Logger.java
 * <p>
 * Created by codetaylor on 11/1/2016.
 */
public class LoggerService {

  private org.apache.logging.log4j.Logger logger;

  public LoggerService(org.apache.logging.log4j.Logger logger) {
    this.logger = logger;
  }

  public void log(Level level, String format, Object... data) {
    logger.printf(level, format, data);
  }

  public void log(Level level, Throwable throwable, String format, Object... data) {
    logger.log(level, String.format(format, data), throwable);
  }

  public void log(Level level, Marker marker, String format, Object... data) {
    logger.printf(level, marker, format, data);
  }

  public void log(Level level, Marker marker, Throwable throwable, String format, Object... data) {
    logger.log(level, marker, String.format(format, data), throwable);
  }

  public void fatal(String format, Object... data) {
    log(Level.FATAL, format, data);
  }

  public void fatal(Marker marker, String format, Object... data) {
    log(Level.FATAL, marker, format, data);
  }

  public void fatal(Throwable throwable, String format, Object... data) {
    log(Level.FATAL, throwable, format, data);
  }

  public void fatal(Marker marker, Throwable throwable, String format, Object... data) {
    log(Level.FATAL, marker, throwable, format, data);
  }

  public void error(String format, Object... data) {
    log(Level.ERROR, format, data);
  }

  public void error(Marker marker, String format, Object... data) {
    log(Level.ERROR, marker, format, data);
  }

  public void error(Throwable throwable, String format, Object... data) {
    log(Level.ERROR, throwable, format, data);
  }

  public void error(Marker marker, Throwable throwable, String format, Object... data) {
    log(Level.ERROR, marker, throwable, format, data);
  }

  public void warn(String format, Object... data) {
    log(Level.WARN, format, data);
  }

  public void warn(Marker marker, String format, Object... data) {
    log(Level.WARN, marker, format, data);
  }

  public void info(String format, Object... data) {
    log(Level.INFO, format, data);
  }

  public void info(Marker marker, String format, Object... data) {
    log(Level.INFO, marker, format, data);
  }

  public void info(Throwable throwable, String format,
                   Object... data) {
    log(Level.INFO, throwable, format, data);
  }

  public void info(Marker marker, Throwable throwable, String format, Object... data) {
    log(Level.INFO, marker, throwable, format, data);
  }

  public void debug(String format, Object... data) {
    log(Level.DEBUG, format, data);
  }

  public void debug(Marker marker, String format, Object... data) {
    log(Level.DEBUG, marker, format, data);
  }

  public void debug(Marker marker, Throwable throwable, String format, Object... data) {
    log(Level.DEBUG, marker, throwable, format, data);
  }
}
