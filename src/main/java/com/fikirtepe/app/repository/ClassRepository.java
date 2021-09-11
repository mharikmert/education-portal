package com.fikirtepe.app.repository;

import com.fikirtepe.app.model.Class;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassRepository extends JpaRepository<Class,Long> {
    Class findClassByName(String className);
    void deleteClassByName(String name);
}
