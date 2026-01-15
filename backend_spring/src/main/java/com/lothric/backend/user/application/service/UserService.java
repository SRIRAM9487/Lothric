package com.lothric.backend.user.application.service;

import com.lothric.backend.user.domain.entity.User;
import com.lothric.backend.user.infrastructure.dto.request.UserCreateRequest;
import java.util.List;

/** Service layer for {@link User}. */
public interface UserService {

  /**
   * @return
   */
  List<User> getAll();

  List<User> getAll(List<Long> ids);

  User get(Long id);

  User create(UserCreateRequest request);

  User update(Long id, UserCreateRequest request);

  User deleteById(Long id);
}
