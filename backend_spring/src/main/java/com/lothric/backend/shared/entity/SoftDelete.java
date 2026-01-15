package com.lothric.backend.shared.entity;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/** Enable soft-delete All entity should extend. */
@Getter
@Setter
public abstract class SoftDelete {

  /*
   * entity created at timestamp
   */
  private LocalDateTime createdAt;

  /*
   * user who created the entity
   */
  private String createdBy;

  /*
   * entity updated at timestamp
   */
  private LocalDateTime updatedAt;

  /*
   * user who updated the entity
   */
  private String updatedBy;

  /*
   * entity deleted at timestamp
   */
  private LocalDateTime deletedAt;

  /*
   * user who deleted the entity
   */
  private String deletedBy;

  /** soft delete the entity. */
  public void softDelete() {
    this.deletedAt = LocalDateTime.now();
  }
}
