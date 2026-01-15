package com.lothric.backend.shared.exception;

import org.springframework.http.HttpStatus;

/** Implemented by all Exception Types. */
public interface BaseExceptionType {

  /**
   * .
   *
   * @return the code exception message
   */
  String getMessage();

  /**
   * .
   *
   * @return the Http status
   */
  HttpStatus getHttpStatus();

  /**
   * .
   *
   * @return the name of the exception
   */
  String name();
}
