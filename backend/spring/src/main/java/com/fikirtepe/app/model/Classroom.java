package com.fikirtepe.app.model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

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

    @JoinColumn(nullable = false)
    @ManyToOne
    private Term term;
    //create a composite key for classroom
}
