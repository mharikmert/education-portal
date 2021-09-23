package com.fikirtepe.app.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Student extends User implements Serializable {

    private LocalDate birthDate;
    private String address;
    private String phoneNumber;
    private String schoolName;
    private int grade;
    private String section;
    private boolean hasInternet;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
//    @JoinTable(name = "student_lecture",
//            joinColumns = {
//                    @JoinColumn(name = "student_id", referencedColumnName = "id",
//                            nullable = false),
//            },
//            inverseJoinColumns = {
//                    @JoinColumn(name = "lecture_id", referencedColumnName = "id",
//                            nullable = false)
//            })
    private Set<Lecture> lectures = new HashSet<>();

    @ManyToOne
//    @JoinColumn(name = "term_id")
    private Term term;

    @ManyToOne
//    @JoinColumn(name = "classroom_id")
    private Classroom classroom;

    @ManyToOne
//    @JoinColumn(name = "parent_id")
    private Parent parent;

    @Override
    public String toString() {
        return super.toString() + "Student{" +
                "birthDate=" + birthDate +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", schoolName='" + schoolName + '\'' +
                ", grade=" + grade +
                ", section='" + section + '\'' +
                ", hasInternet=" + hasInternet +
                '}';
    }
}
