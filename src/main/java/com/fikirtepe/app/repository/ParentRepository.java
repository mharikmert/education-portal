package com.fikirtepe.app.repository;

import com.fikirtepe.app.model.Parent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParentRepository extends JpaRepository<Parent,Long> {
    Parent findParentById(Long id);
}
