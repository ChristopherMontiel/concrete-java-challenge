package com.concrete.javachallenge.exception;

public class CategoriesNotFoundException extends RuntimeException {
  public CategoriesNotFoundException(String message) {
    super(message);
  }

  public CategoriesNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
