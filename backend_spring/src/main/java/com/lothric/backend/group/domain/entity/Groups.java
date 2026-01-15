package com.lothric.backend.group.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/** Groups Entity. */
@Getter
@Setter
@AllArgsConstructor
public class Groups {
  private Long id;
  private String name;
  private String description;
}
