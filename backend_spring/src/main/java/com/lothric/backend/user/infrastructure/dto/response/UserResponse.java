package com.lothric.backend.user.infrastructure.dto.response;

/** User Resonse . */
public record UserResponse(
    Long id,
    String name,
    String username,
    String role,
    boolean isAccountNonLocked,
    boolean isEnabled) {}
