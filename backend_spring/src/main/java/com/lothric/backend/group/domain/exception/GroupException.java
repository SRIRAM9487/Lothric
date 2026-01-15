package com.lothric.backend.group.domain.exception;

import com.lothric.backend.shared.exception.BaseException;
import com.lothric.backend.shared.exception.BaseExceptionType;

public class GroupException extends BaseException {

  public GroupException(BaseExceptionType type) {
    super(type);
  }

  public static GroupException notFound() {
    return new GroupException(GroupExceptionType.GROUP_NOT_FOUND);
  }
}
