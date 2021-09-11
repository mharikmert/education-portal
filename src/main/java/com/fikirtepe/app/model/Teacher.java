package com.fikirtepe.app.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
@Entity
public class Teacher extends User{
    @OneToMany
    private List<Lecture> givenLectures;
    @OneToMany
    private List<Class> classes;

}
