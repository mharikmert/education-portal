package com.fikirtepe.app.repository;

import com.fikirtepe.app.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {
    Student findStudentById(Long id);
}
