package com.fikirtepe.app.repository;

import com.fikirtepe.app.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TeacherRepository extends JpaRepository<Teacher,Long> {
    Teacher findTeacherById(Long id);
}
