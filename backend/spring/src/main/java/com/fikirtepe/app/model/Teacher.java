package com.fikirtepe.app.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Teacher extends User implements Serializable {
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
//    @JoinTable(name = "teachers_lectures",
//            joinColumns = {
//                    @JoinColumn(name = "teacher_id", referencedColumnName = "id",
//                            nullable = false),
//            },
//            inverseJoinColumns = {
//                    @JoinColumn(name = "lecture_id", referencedColumnName = "id",
//                            nullable = false)
//            })
    private Set<Lecture> lectures = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
//    @JoinTable(name = "teachers_classrooms",
//            joinColumns = {
//                    @JoinColumn(name = "teacher_id", referencedColumnName = "id",
//                            nullable = false),
//            },
//            inverseJoinColumns = {
//                    @JoinColumn(name = "classroom_id", referencedColumnName = "id",
//                            nullable = false)
//            })
    private Set<Classroom> classrooms = new HashSet<>();

    @ManyToOne
//    @JoinColumn(name = "term_id")
    private Term term;

}
