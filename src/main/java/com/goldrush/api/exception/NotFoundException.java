package com.goldrush.api.exception;

import java.util.UUID;

public class NotFoundException extends RuntimeException {

  public NotFoundException(Class<?> entityClass, UUID entityId) {
      super(String.format("%s with ID %s not found", entityClass.getSimpleName(), entityId));
  }

  public NotFoundException(String message) {
      super(message);
  }
}
