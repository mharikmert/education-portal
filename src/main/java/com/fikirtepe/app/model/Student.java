package com.fikirtepe.app.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Getter
@Setter
@Entity
public class Student extends User{
    @OneToOne
    private Class studentClass;
    @OneToMany
    private List<Lecture> lectures;
}
