package com.lothric.backend.shared.dto;

import java.time.LocalDateTime;

/** Base response DTO. */
public record ApiResponse<T>(boolean success, T data, LocalDateTime timeStamp) {

  /** Static factory method for creating dto. */
  public static <T> ApiResponse<T> create(T data) {
    return new ApiResponse<>(true, data, LocalDateTime.now());
  }
}
