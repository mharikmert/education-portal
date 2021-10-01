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

    public Section(){ }
    public Section(Long id, String day, String startingTime, int numberOfHours, Lecture lecture, Teacher teacher, Classroom classroom) {
        this.id = id;
        this.day = day;
        this.startingTime = startingTime;
        this.numberOfHours = numberOfHours;
        this.lecture = lecture;
        this.teacher = teacher;
        this.classroom = classroom;
    }

    @Override
    public String toString() {
        return "Section{" +
                "id=" + id +
                ", day='" + day + '\'' +
                ", startingTime='" + startingTime + '\'' +
                ", numberOfHours=" + numberOfHours +
                ", lecture=" + lecture +
                ", teacher=" + teacher +
                ", classroom=" + classroom +
                '}';
    }
}
