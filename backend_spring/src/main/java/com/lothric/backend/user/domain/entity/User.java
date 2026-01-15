package com.lothric.backend.user.domain.entity;

import com.lothric.backend.shared.entity.BasedEntity;
import com.lothric.backend.user.infrastructure.dto.response.UserResponse;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/** User entity. */
@Getter
@Setter
@ToString
public class User extends BasedEntity {
  private String username;
  private String email;
  private String password;
  private UserRole role;
  private boolean isAccountNonLocked;
  private boolean isEnabled;

  public UserResponse toResponse() {
    return new UserResponse(
        this.getId(), this.getName(), username, role.name(), isAccountNonLocked, isEnabled);
  }
}
