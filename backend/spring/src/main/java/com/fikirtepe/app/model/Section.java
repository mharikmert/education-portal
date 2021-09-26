package com.fikirtepe.app.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
public class Section implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String day;
    private String startingTime;
    private int numberOfHours;

    @ManyToOne
    private Lecture lecture;
    @ManyToOne
    private Teacher teacher;
    @ManyToOne
    private Classroom classroom;
}
