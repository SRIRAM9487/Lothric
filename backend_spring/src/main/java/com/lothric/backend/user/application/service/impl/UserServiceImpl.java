package com.lothric.backend.user.application.service.impl;

import com.lothric.backend.user.application.service.UserService;
import com.lothric.backend.user.domain.entity.User;
import com.lothric.backend.user.domain.entity.UserRole;
import com.lothric.backend.user.infrastructure.dto.request.UserCreateRequest;
import com.lothric.backend.user.infrastructure.persistence.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/** Implementation of {@link UserService }. */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Override
  public List<User> getAll() {
    return userRepository.findAll();
  }

  @Override
  public List<User> getAll(List<Long> ids) {
    return userRepository.findAllByIds(ids);
  }

  @Override
  public User get(Long id) {
    return userRepository.findById(id);
  }

  @Override
  public User create(UserCreateRequest request) {
    User user = new User();
    user.setName(request.name());
    user.setUsername(request.username());
    user.setRole(UserRole.valueOf(request.role()));
    user.setEmail(request.email());
    user.setAccountNonLocked(request.isAccountNonLocked());
    user.setEnabled(request.isEnabled());
    return userRepository.save(user);
  }

  @Override
  public User update(Long id, UserCreateRequest request) {
    User user = new User();
    user.setId(id);
    user.setName(request.name());
    user.setUsername(request.username());
    user.setRole(UserRole.valueOf(request.role()));
    user.setEmail(request.email());
    user.setAccountNonLocked(request.isAccountNonLocked());
    user.setEnabled(request.isEnabled());
    return userRepository.update(user);
  }

  @Override
  public User deleteById(Long id) {
    return userRepository.deleteById(id);
  }
}
