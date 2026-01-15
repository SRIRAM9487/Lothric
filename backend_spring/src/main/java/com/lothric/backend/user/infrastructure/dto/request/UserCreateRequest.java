package com.lothric.backend.user.infrastructure.dto.request;

import com.lothric.backend.user.domain.entity.User;

/** {@link User} User create request dto. */
public record UserCreateRequest(
    String name, String username, String role, boolean isAccountNonLocked, boolean isEnabled) {}
