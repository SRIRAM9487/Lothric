package com.lothric.backend.shared.entity;

import lombok.Getter;
import lombok.Setter;

/** Base entity. */
@Getter
@Setter
public abstract class BasedEntity extends IdEntity {

  /** Name field. */
  private String name;
}
