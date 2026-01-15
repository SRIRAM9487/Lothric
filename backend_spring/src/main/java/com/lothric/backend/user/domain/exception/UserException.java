package com.lothric.backend.user.domain.exception;

import com.lothric.backend.shared.exception.BaseException;
import com.lothric.backend.user.domain.entity.User;

/**
 * Exception to handle {@link User}.
 *
 * @author SRIRAM
 */
public class UserException extends BaseException {

  public UserException(UserExceptionType type) {
    super(type);
  }

  public static UserException notFound() {
    return new UserException(UserExceptionType.USER_NOT_FOUND);
  }

  public static UserException authenticationFailed() {
    return new UserException(UserExceptionType.AUTHENTICATION_FAILED);
  }

  public static UserException emailNotVerified() {
    return new UserException(UserExceptionType.EMAIL_NOT_VERIFIED);
  }

  public static UserException emailAlreadyVerified() {
    return new UserException(UserExceptionType.EMAIL_NOT_VERIFIED);
  }

  public static UserException invalidOtp() {
    return new UserException(UserExceptionType.INVALID_OTP);
  }

  public static UserException invalidPasswordVerificationToken() {
    return new UserException(UserExceptionType.INVALID_PASSWORD_VERIFICATION_TOKEN);
  }

  public static UserException emailNotUniqueViolation() {
    return new UserException(UserExceptionType.USER_EMAIL_MUST_BE_UNIQUE);
  }

  public static UserException userNameNotUniqueViolation() {
    return new UserException(UserExceptionType.USER_NAME_MUST_BE_UNIQUE);
  }
}
