package com.lothric.backend.user.domain.entity;

import java.util.Collection;
import java.util.Collections;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/** Principal object. */
public class UserImpl implements UserDetails {

  private final User user;

  /** Default costructor. */
  public UserImpl(User user) {
    this.user = user;
  }

  @Override
  public String getUsername() {
    return user.getUsername();
  }

  @Override
  public @Nullable String getPassword() {
    return user.getPassword();
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Collections.singleton(new SimpleGrantedAuthority(user.getRole().name()));
  }

  @Override
  public boolean isAccountNonLocked() {
    return user.isAccountNonLocked();
  }

  @Override
  public boolean isEnabled() {
    return user.isEnabled();
  }
}
