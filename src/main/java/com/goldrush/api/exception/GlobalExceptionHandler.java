package com.goldrush.api.exception;

import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<Object> handleNotFoundException(NotFoundException ex) {
    Map<String, Object> body = new LinkedHashMap<>();
    body.put("error", "Not Found");
    body.put("message", ex.getMessage());

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
  }
}
