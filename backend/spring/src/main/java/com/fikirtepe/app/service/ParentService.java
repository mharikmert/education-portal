package com.fikirtepe.app.service;

import com.fikirtepe.app.model.Parent;
import com.fikirtepe.app.model.Student;

import java.util.List;
import java.util.Set;

public interface ParentService {
    Parent createParent(Parent parent);
    List<Parent> findAllParents();
    Parent findParentById(Long id);
    Set<Student> findStudentsByParentId(Long id);
    void deleteParent(Parent parent);
}
