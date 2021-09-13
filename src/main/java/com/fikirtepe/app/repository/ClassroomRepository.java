package com.fikirtepe.app.repository;

import com.fikirtepe.app.model.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClassroomRepository extends JpaRepository<Classroom,Long> {
    Classroom findClassroomByName(String className);
    void deleteClassroomByName(String name);
    List<Classroom> findAll();
}
