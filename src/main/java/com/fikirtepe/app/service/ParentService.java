package com.fikirtepe.app.service;

import com.fikirtepe.app.model.Parent;
import com.fikirtepe.app.model.Student;

import java.util.List;

public interface ParentService {
    Parent createParent(Parent parent);
    Parent findParentById(Long id);
    List<Student> findStudentsByParentId(Long id);
    void deleteParent(Parent parent);
}
