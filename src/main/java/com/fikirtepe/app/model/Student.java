package com.fikirtepe.app.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "students")
public class Student implements Serializable {
    @Id
    private Long id;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "students_lectures",
            joinColumns = {
            @JoinColumn(name = "student_id", referencedColumnName = "id",
            nullable = false),
            },
            inverseJoinColumns = {
            @JoinColumn(name = "lecture_id", referencedColumnName = "id",
            nullable = false)
            })
    private Set<Lecture> lectures = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "term_id")
    private Term term;

    @ManyToOne
    @JoinColumn(name = "classroom_id")
    private Classroom classroom;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Parent parent;

}
