package com.fikirtepe.app.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@Entity
public class Classroom implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int capacity;
    private int classroomSize;
    private int grade;

    @OneToMany//(mappedBy = "classroom", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Student> students;

    @ManyToMany//(mappedBy = "classrooms", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Teacher> teachers;

    @ManyToMany//(mappedBy = "classrooms", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Lecture> lectures;

    @ManyToOne
//    @JoinColumn(name = "term_id")
    private Term term;

}
