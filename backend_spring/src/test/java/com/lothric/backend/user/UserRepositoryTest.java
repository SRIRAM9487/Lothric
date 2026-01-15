package com.lothric.backend.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import com.lothric.backend.AbstractTestContainer;
import com.lothric.backend.user.domain.entity.User;
import com.lothric.backend.user.domain.entity.UserRole;
import com.lothric.backend.user.domain.exception.UserException;
import com.lothric.backend.user.domain.exception.UserExceptionType;
import com.lothric.backend.user.infrastructure.persistence.UserRepository;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/** {@link UserRepository}. */
public class UserRepositoryTest extends AbstractTestContainer {

  @Autowired private UserRepository userRepository;

  private final Long foundUserId = 1L;
  private final Long notFoundUserId = 9999L;
  private final String uniqueUserName = "UserName3";
  private final String uniqueEmail = "user4@gmail.com";
  private User newUser;

  @BeforeEach
  void setup() {
    newUser = new User();
    newUser.setName("Tester");
    newUser.setUsername("Tester-1");
    newUser.setPassword("Tester");
    newUser.setRole(UserRole.ADMIN);
    newUser.setEmail("tester@gmail.com");
    newUser.setEnabled(true);
    newUser.setAccountNonLocked(true);
  }

  @Test
  @Transactional
  void getAll() {
    List<User> users = userRepository.findAll();
    assertFalse(users.isEmpty());
  }

  @Test
  @Transactional
  void getById() {
    User user = userRepository.findById(foundUserId);
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
              userRepository.findById(notFoundUserId);
            });
    assertEquals(UserExceptionType.USER_NOT_FOUND.name(), ex.getCode());
  }

  @Test
  @Transactional
  void createUser() {
    User savedUser = userRepository.save(newUser);
    assertEquals(newUser.getName(), savedUser.getName());
    assertEquals(newUser.getUsername(), savedUser.getUsername());
    assertEquals(newUser.getRole(), savedUser.getRole());
    assertEquals(newUser.isEnabled(), savedUser.isEnabled());
    assertEquals(newUser.isAccountNonLocked(), savedUser.isAccountNonLocked());
  }

  @Test
  @Transactional
  void createUser_unique_username() {
    newUser.setUsername(uniqueUserName);
    var ex =
        assertThrowsExactly(
            UserException.class,
            () -> {
              userRepository.save(newUser);
            });
    assertEquals(UserExceptionType.USER_NAME_MUST_BE_UNIQUE.name(), ex.getCode());
  }

  @Test
  @Transactional
  void createUser_unique_email() {
    newUser.setEmail(uniqueEmail);
    var ex =
        assertThrowsExactly(
            UserException.class,
            () -> {
              userRepository.save(newUser);
            });
    assertEquals(UserExceptionType.USER_EMAIL_MUST_BE_UNIQUE.name(), ex.getCode());
  }

  @Test
  @Transactional
  void updateUser() {
    newUser.setId(foundUserId);
    User savedUser = userRepository.update(newUser);
    assertEquals(newUser.getId(), savedUser.getId());
    assertEquals(newUser.getName(), savedUser.getName());
    assertEquals(newUser.getUsername(), savedUser.getUsername());
    assertEquals(newUser.getRole(), savedUser.getRole());
    assertEquals(newUser.isEnabled(), savedUser.isEnabled());
    assertEquals(newUser.isAccountNonLocked(), savedUser.isAccountNonLocked());
  }

  @Test
  @Transactional
  void updateUser_unique_username() {
    newUser.setId(foundUserId);
    newUser.setUsername(uniqueUserName);
    var ex =
        assertThrowsExactly(
            UserException.class,
            () -> {
              userRepository.update(newUser);
            });
    assertEquals(UserExceptionType.USER_NAME_MUST_BE_UNIQUE.name(), ex.getCode());
  }

  @Test
  @Transactional
  void updateUser_unique_email() {
    newUser.setId(foundUserId);
    newUser.setEmail(uniqueEmail);
    var ex =
        assertThrowsExactly(
            UserException.class,
            () -> {
              userRepository.update(newUser);
            });
    assertEquals(UserExceptionType.USER_EMAIL_MUST_BE_UNIQUE.name(), ex.getCode());
  }

  @Test
  @Transactional
  void updateUser_not_found() {
    newUser.setId(notFoundUserId);
    var ex =
        assertThrowsExactly(
            UserException.class,
            () -> {
              userRepository.update(newUser);
            });
    assertEquals(UserExceptionType.USER_NOT_FOUND.name(), ex.getCode());
  }

  @Test
  @Transactional
  void deleteById() {
    Long before = userRepository.count();
    User user = userRepository.deleteById(foundUserId);
    assertEquals(user.getId(), foundUserId);
    assertEquals(before - 1, userRepository.count());
  }

  @Test
  @Transactional
  void deleteById_notFound() {
    var ex =
        assertThrowsExactly(
            UserException.class,
            () -> {
              userRepository.deleteById(notFoundUserId);
            });
    assertEquals(UserExceptionType.USER_NOT_FOUND.name(), ex.getCode());
  }

  @Test
  @Transactional
  void getAllByIds() {
    var ids = List.of(1L, 23L, 4L);
    List<User> users = userRepository.findAllByIds(ids);
    assertEquals(users.size(), ids.size());
  }
}
