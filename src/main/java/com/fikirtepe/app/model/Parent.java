package com.fikirtepe.app.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "parents")
public class Parent implements Serializable {
    @Id
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Student> students;
}
