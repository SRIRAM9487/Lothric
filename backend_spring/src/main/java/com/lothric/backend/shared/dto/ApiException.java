package com.lothric.backend.shared.dto;

import com.lothric.backend.shared.exception.BaseException;
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;

/** Error response dto. */
public record ApiException(
    boolean success,
    String method,
    HttpStatus status,
    String timeStamp,
    String path,
    String message,
    String code) {

  /** Api Excpetion creation with BaseException. */
  public static ApiException create(BaseException exception, HttpServletRequest httpRequest) {
    return new ApiException(
        false,
        httpRequest.getMethod(),
        exception.getHttpStatus(),
        LocalDateTime.now().toString(),
        httpRequest.getPathInfo().toString(),
        exception.getMessage(),
        exception.getCode());
  }
}
