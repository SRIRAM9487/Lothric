package com.lothric.backend.shared.dto;

import com.lothric.backend.shared.exception.BaseException;
import java.time.LocalDateTime;

/** Error response dto. */
public record ApiException(boolean success, String timeStamp, String message, String code) {

  /** Api Excpetion creation with BaseException. */
  public static ApiException create(BaseException exception) {
    return new ApiException(
        false, LocalDateTime.now().toString(), exception.getMessage(), exception.getCode());
  }
}
