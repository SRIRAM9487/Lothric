package com.lothric.backend.user.infrastructure.persistence.impl;

import com.lothric.backend.user.domain.entity.User;
import com.lothric.backend.user.domain.entity.UserRole;
import com.lothric.backend.user.domain.exception.UserException;
import com.lothric.backend.user.infrastructure.persistence.UserRepository;
import java.sql.Timestamp;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/** User Repository impl. */
@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

  private final JdbcTemplate jdbcTemplate;

  private final RowMapper<User> userRowMapper =
      (res, row) -> {
        User user = new User();
        user.setId(res.getLong("id"));
        user.setName(res.getString("name"));
        user.setUsername(res.getString("username"));
        user.setEmail(res.getString("email"));
        user.setRole(UserRole.valueOf(res.getString("role")));
        user.setAccountNonLocked(res.getBoolean("is_account_non_locked"));
        user.setEnabled(res.getBoolean("is_enabled"));
        user.setCreatedAt(res.getTimestamp("created_at").toLocalDateTime());
        Timestamp updated = res.getTimestamp("updated_at");
        if (updated != null) {
          user.setUpdatedAt(updated.toLocalDateTime());
        }
        return user;
      };

  @Override
  public List<User> findAll() {
    String sql =
        "SELECT id, name, username, email, role, is_account_non_locked, is_enabled, created_at,"
            + " updated_at FROM users WHERE deleted_at IS NULL;";
    return jdbcTemplate.query(sql, userRowMapper);
  }

  @Override
  public User findById(Long id) {
    String sql =
        "SELECT id, name, username, email, role, is_account_non_locked, is_enabled, created_at,"
            + " updated_at FROM users WHERE id =? AND deleted_at IS NULL;";
    try {
      return jdbcTemplate.queryForObject(sql, userRowMapper, id);
    } catch (EmptyResultDataAccessException e) {
      throw UserException.notFound();
    }
  }

  @Override
  public User save(User user) {
    String sql =
        "INSERT INTO users(name, username, email, role, is_account_non_locked, is_enabled)"
            + " VALUES(?,?,?,?,?,?) RETURNING id,name, username, email, role,"
            + " is_account_non_locked, is_enabled, created_at, updated_at;";

    try {
      return jdbcTemplate.queryForObject(
          sql,
          userRowMapper,
          user.getName(),
          user.getUsername(),
          user.getEmail(),
          user.getRole().name(),
          user.isAccountNonLocked(),
          user.isEnabled());
    } catch (DataIntegrityViolationException ex) {
      if (ex.getMessage().contains("Key (username)")) {
        throw UserException.userNameNotUniqueViolation();
      }
      if (ex.getMessage().contains("Key (email)")) {
        throw UserException.emailNotUniqueViolation();
      }
      ex.printStackTrace();
      throw ex;
    }
  }

  @Override
  public User update(User user) {
    String sql =
        "UPDATE users SET name =?, username =?, email =?, role =?, is_account_non_locked =?,"
            + " is_enabled =? WHERE id =? AND deleted_at IS NULL RETURNING id, name, username,"
            + " email, role, is_account_non_locked, is_enabled, created_at, updated_at;";
    try {
      return jdbcTemplate.queryForObject(
          sql,
          userRowMapper,
          user.getName(),
          user.getUsername(),
          user.getEmail(),
          user.getRole().name(),
          user.isAccountNonLocked(),
          user.isEnabled(),
          user.getId());
    } catch (DataIntegrityViolationException ex) {
      if (ex.getMessage().contains("Key (username)")) {
        throw UserException.userNameNotUniqueViolation();
      }
      if (ex.getMessage().contains("Key (email)")) {
        throw UserException.emailNotUniqueViolation();
      }
      ex.printStackTrace();
      throw ex;
    } catch (EmptyResultDataAccessException ex) {
      throw UserException.notFound();
    }
  }

  @Override
  public User deleteById(Long id) {
    String sql =
        """
        UPDATE users
        SET
          deleted_at = NOW()
        WHERE id = ?
          AND deleted_at IS NULL
        RETURNING
          id, name, username, email, role,
          is_account_non_locked, is_enabled,
          created_at, updated_at
        """;

    try {
      return jdbcTemplate.queryForObject(sql, userRowMapper, id);
    } catch (EmptyResultDataAccessException ex) {
      throw UserException.notFound();
    }
  }

  @Override
  public Long count() {
    String sql = "SELECT COUNT(*) FROM users WHERE deleted_at IS NULL";
    return jdbcTemplate.queryForObject(sql, Long.class);
  }
}
