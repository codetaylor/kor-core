package com.sudoplay.mc.kor.core.config;

/**
 * Created by sk3lls on 11/19/2016.
 */
public class MalformedItemNameException extends
    Exception {

  public MalformedItemNameException() {
  }

  public MalformedItemNameException(String message) {
    super(message);
  }

  public MalformedItemNameException(String message, Throwable cause) {
    super(message, cause);
  }

  public MalformedItemNameException(Throwable cause) {
    super(cause);
  }

  public MalformedItemNameException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
