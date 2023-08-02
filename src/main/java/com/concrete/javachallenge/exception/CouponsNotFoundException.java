package com.concrete.javachallenge.exception;

public class CouponsNotFoundException extends RuntimeException {
  public CouponsNotFoundException(String message) {
    super(message);
  }

  public CouponsNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
