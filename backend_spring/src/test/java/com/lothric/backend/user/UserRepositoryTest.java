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
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/** {@link UserRepository}. */
public class UserRepositoryTest extends AbstractTestContainer {

  @Autowired private UserRepository userRepository;

  private final Long foundUserId = 1L;
  private final Long notFoundUserId = 9999L;

  @Test
  void getAll() {
    List<User> users = userRepository.findAll();
    assertFalse(users.isEmpty());
  }

  @Test
  void getById() {
    User user = userRepository.findById(foundUserId);
    assertEquals(user.getId(), foundUserId);
    assertNotNull(user.getCreatedAt());

    assertNull(user.getDeletedAt());
  }

  @Test
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
  void createUser() {
    User user = new User();
    user.setName("Tester");
    user.setUsername("Testeuserlr");
    user.setPassword("Tester");
    user.setRole(UserRole.ADMIN);
    user.setEnabled(true);
    user.setAccountNonLocked(true);
    User savedUser = userRepository.save(user);
    assertEquals(user.getName(), savedUser.getName());
    assertEquals(user.getUsername(), savedUser.getUsername());
    assertEquals(user.getPassword(), savedUser.getPassword());
    assertEquals(user.getRole(), savedUser.getRole());
    assertEquals(user.isEnabled(), savedUser.isEnabled());
    assertEquals(user.isAccountNonLocked(), savedUser.isAccountNonLocked());
    assertNotNull(user.getCreatedAt());
  }
}
