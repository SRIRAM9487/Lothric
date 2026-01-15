package com.lothric.backend.user.infrastructure.persistence;

import com.lothric.backend.user.domain.entity.User;
import java.util.List;

/** Repository for {@link User}. */
public interface UserRepository {

  /** Retrive all users. */
  List<User> findAll();

  /** Retrive user by Id. */
  User findById(Long id);

  /** Create new user. */
  User save(User user);

  /** update user. */
  User update(User user);

  /** Delete user by Id. */
  User deleteById(User user);
}
