package com.lothric.backend.shared.advice;

import com.lothric.backend.shared.dto.ApiException;
import com.lothric.backend.shared.exception.BaseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/** Global Exception handler. */
@RestControllerAdvice
public class ErrorControllerAdvice {

  /** Exception handler for {@Link BaseException}. */
  @ExceptionHandler(BaseException.class)
  public ResponseEntity<ApiException> handleBasicException(BaseException exception) {
    return ResponseEntity.status(exception.getHttpStatus()).body(ApiException.create(exception));
  }
}
