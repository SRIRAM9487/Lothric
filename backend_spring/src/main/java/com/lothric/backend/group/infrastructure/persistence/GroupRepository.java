package com.lothric.backend.group.infrastructure.persistence;

import com.lothric.backend.group.domain.entity.Groups;
import java.util.List;

public interface GroupRepository {

  List<Groups> findAll();

  Groups findById(Long id);

  Groups save(Groups groups);

  Groups update(Groups groups);

  Groups deleteById(Long id);
}
