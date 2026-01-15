package com.lothric.backend.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import com.lothric.backend.AbstractTestContainer;
import com.lothric.backend.user.application.service.UserService;
import com.lothric.backend.user.domain.entity.User;
import com.lothric.backend.user.domain.entity.UserRole;
import com.lothric.backend.user.domain.exception.UserException;
import com.lothric.backend.user.domain.exception.UserExceptionType;
import com.lothric.backend.user.infrastructure.dto.request.UserCreateRequest;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/** {@link UserService}. */
public class UserServiceTest extends AbstractTestContainer {

  @Autowired private UserService userService;

  private final Long foundUserId = 1L;
  private final Long notFoundUserId = 9999L;
  private final String uniqueUserName = "UserName3";
  private final String uniqueEmail = "user4@gmail.com";

  private UserCreateRequest newUserDto;

  @BeforeEach
  void setup() {
    newUserDto =
        new UserCreateRequest(
            "Tester1", "Tester-1", "tester100@gmail.com", UserRole.ADMIN.name(), false, true);
  }

  @Test
  void getAll() {
    List<User> users = userService.getAll();
    assertFalse(users.isEmpty());
  }

  @Test
  @Transactional
  void getById() {
    User user = userService.get(foundUserId);
    assertEquals(user.getId(), foundUserId);
    assertNotNull(user.getCreatedAt());

    assertNull(user.getDeletedAt());
  }

  @Test
  @Transactional
  void getById_notFound() {
    var ex =
        assertThrowsExactly(
            UserException.class,
            () -> {
              userService.get(notFoundUserId);
            });
    assertEquals(UserExceptionType.USER_NOT_FOUND.name(), ex.getCode());
  }

  @Test
  @Transactional
  void create() {
    User user = userService.create(newUserDto);
    assertNotNull(user.getId());
    assertEquals(newUserDto.name(), user.getName());
    assertEquals(newUserDto.username(), user.getUsername());
    assertEquals(newUserDto.email(), user.getEmail());
    assertEquals(newUserDto.role(), user.getRole().name());
    assertEquals(newUserDto.isAccountNonLocked(), user.isAccountNonLocked());
    assertEquals(newUserDto.isEnabled(), user.isEnabled());
  }

  @Test
  @Transactional
  void createUser_unique_username() {
    newUserDto =
        new UserCreateRequest(
            newUserDto.name(),
            uniqueUserName,
            newUserDto.email(),
            newUserDto.role(),
            newUserDto.isAccountNonLocked(),
            newUserDto.isEnabled());

    var ex =
        assertThrowsExactly(
            UserException.class,
            () -> {
              userService.create(newUserDto);
            });
    assertEquals(UserExceptionType.USER_NAME_MUST_BE_UNIQUE.name(), ex.getCode());
  }

  @Test
  @Transactional
  void createUser_unique_email() {
    newUserDto =
        new UserCreateRequest(
            newUserDto.name(),
            newUserDto.username(),
            uniqueEmail,
            newUserDto.role(),
            newUserDto.isAccountNonLocked(),
            newUserDto.isEnabled());
    var ex =
        assertThrowsExactly(
            UserException.class,
            () -> {
              userService.create(newUserDto);
            });
    assertEquals(UserExceptionType.USER_EMAIL_MUST_BE_UNIQUE.name(), ex.getCode());
  }

  @Test
  @Transactional
  void updateUser() {
    User savedUser = userService.update(foundUserId, newUserDto);
    assertEquals(foundUserId, savedUser.getId());
    assertEquals(newUserDto.name(), savedUser.getName());
    assertEquals(newUserDto.username(), savedUser.getUsername());
    assertEquals(newUserDto.role(), savedUser.getRole().name());
    assertEquals(newUserDto.isEnabled(), savedUser.isEnabled());
    assertEquals(newUserDto.isAccountNonLocked(), savedUser.isAccountNonLocked());
  }

  @Test
  @Transactional
  void updateUser_unique_username() {
    newUserDto =
        new UserCreateRequest(
            newUserDto.name(),
            uniqueUserName,
            newUserDto.email(),
            newUserDto.role(),
            newUserDto.isAccountNonLocked(),
            newUserDto.isEnabled());
    var ex =
        assertThrowsExactly(
            UserException.class,
            () -> {
              userService.update(foundUserId, newUserDto);
            });
    assertEquals(UserExceptionType.USER_NAME_MUST_BE_UNIQUE.name(), ex.getCode());
  }

  @Test
  @Transactional
  void updateUser_unique_email() {
    newUserDto =
        new UserCreateRequest(
            newUserDto.name(),
            newUserDto.username(),
            uniqueEmail,
            newUserDto.role(),
            newUserDto.isAccountNonLocked(),
            newUserDto.isEnabled());
    var ex =
        assertThrowsExactly(
            UserException.class,
            () -> {
              userService.update(foundUserId, newUserDto);
            });
    assertEquals(UserExceptionType.USER_EMAIL_MUST_BE_UNIQUE.name(), ex.getCode());
  }

  @Test
  @Transactional
  void updateUser_not_found() {
    var ex =
        assertThrowsExactly(
            UserException.class,
            () -> {
              userService.update(notFoundUserId, newUserDto);
            });
    assertEquals(UserExceptionType.USER_NOT_FOUND.name(), ex.getCode());
  }

  @Test
  @Transactional
  void deleteById() {
    User user = userService.deleteById(foundUserId);
    assertEquals(user.getId(), foundUserId);
  }

  @Test
  @Transactional
  void deleteById_notFound() {
    var ex =
        assertThrowsExactly(
            UserException.class,
            () -> {
              userService.deleteById(notFoundUserId);
            });
    assertEquals(UserExceptionType.USER_NOT_FOUND.name(), ex.getCode());
  }
}
