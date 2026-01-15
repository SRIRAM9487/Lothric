package com.lothric.backend.shared.entity;

import lombok.Getter;
import lombok.Setter;

/** Id Entity. */
@Getter
@Setter
public abstract class IdEntity extends SoftDelete {
  private Long id;
}
