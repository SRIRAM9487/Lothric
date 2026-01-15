package com.lothric.backend.user.infrastructure.controller;

import com.lothric.backend.shared.constant.RestConstant;
import com.lothric.backend.shared.dto.ApiResponse;
import com.lothric.backend.user.application.service.UserService;
import com.lothric.backend.user.infrastructure.dto.request.UserCreateRequest;
import com.lothric.backend.user.infrastructure.dto.response.UserResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** User Controller. */
@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  /** User Controller. */
  @GetMapping
  public ResponseEntity<ApiResponse<List<UserResponse>>> getAll() {
    var users = userService.getAll().stream().map(c -> c.toResponse()).toList();
    ApiResponse<List<UserResponse>> response = ApiResponse.create(users);
    return ResponseEntity.ok(response);
  }

  /** User Controller. */
  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse<UserResponse>> get(@PathVariable("id") Long id) {
    ApiResponse<UserResponse> response = ApiResponse.create(userService.get(id).toResponse());
    return ResponseEntity.ok(response);
  }

  /** User Controller. */
  @PostMapping
  public ResponseEntity<ApiResponse<String>> create(@RequestBody UserCreateRequest createRequest) {
    userService.create(createRequest);
    ApiResponse<String> response = ApiResponse.create(RestConstant.CREATED);
    return ResponseEntity.ok(response);
  }

  /** User Controller. */
  @PatchMapping("/{id}")
  public ResponseEntity<ApiResponse<String>> update(
      @PathVariable("id") Long id, @RequestBody UserCreateRequest createRequest) {
    userService.update(id, createRequest);
    ApiResponse<String> response = ApiResponse.create(RestConstant.UPDATED);
    return ResponseEntity.ok(response);
  }

  /** User Controller. */
  @DeleteMapping("/{id}")
  public ResponseEntity<ApiResponse<String>> delete(@PathVariable("id") Long id) {
    userService.deleteById(id);
    ApiResponse<String> response = ApiResponse.create(RestConstant.DELETED);
    return ResponseEntity.ok(response);
  }
}
