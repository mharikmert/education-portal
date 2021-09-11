package com.fikirtepe.app.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
@Entity
public class Lecture {
    @Id
    private Long id;
    private String name;
    @OneToMany
    private List<Teacher> teachers;
    @OneToMany
    private List<Class> classes;
}
