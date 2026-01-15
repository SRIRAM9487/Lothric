package com.lothric.backend.user.domain.entity;

import com.lothric.backend.shared.entity.BasedEntity;
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
}
