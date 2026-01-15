package com.lothric.backend.shared.entity;

import lombok.Getter;
import lombok.Setter;

/** Named Entity. */
@Getter
@Setter
public abstract class NamedEntity extends SoftDelete {
  private String name;
}
