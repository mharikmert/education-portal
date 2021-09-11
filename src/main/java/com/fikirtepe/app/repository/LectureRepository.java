package com.fikirtepe.app.repository;

import com.fikirtepe.app.model.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureRepository extends JpaRepository<Lecture,Long> {
    Lecture findLectureByName(String name);
}
