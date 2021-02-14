package com.fikirtepe.app.Model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Parent {
    @Id
    private long id;

    private String firstName;

    private String lastName;

    private String email;
}
