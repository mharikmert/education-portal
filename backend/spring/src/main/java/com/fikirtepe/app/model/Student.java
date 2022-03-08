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
    private String schoolName;
    private int grade;
    private String section;
    private boolean hasInternet;

    @ManyToMany
    private Set<Lecture> lectures = new HashSet<>(); // m-n

    @ManyToOne
    private Classroom classroom; // 1-n

    @ManyToOne
    private Parent parent; // 1-n

    @Override
    public String toString() {
        return super.toString() + "Student{" +
                "birthDate=" + birthDate +
                ", address='" + address + '\'' +
                ", schoolName='" + schoolName + '\'' +
                ", grade=" + grade +
                ", section='" + section + '\'' +
                ", hasInternet=" + hasInternet +
                '}';
    }
}
