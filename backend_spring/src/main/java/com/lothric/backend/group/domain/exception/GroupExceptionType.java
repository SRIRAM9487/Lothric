package com.lothric.backend.group.domain.exception;

import com.lothric.backend.shared.exception.BaseExceptionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum GroupExceptionType implements BaseExceptionType {
  GROUP_NOT_FOUND("Group not found", HttpStatus.NOT_FOUND);
  private final String message;
  private final HttpStatus httpStatus;
}
