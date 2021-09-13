package com.fikirtepe.app.model;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class User extends BaseEntity implements Serializable {

    private String username;

    private String firstName;

    private String lastName;

    private String password;

    private LocalDate birthDate;

    private String address;

    private String phoneNumber;

    private String email;

    private String city;

    private String district;

    private String schoolName;

    private int grade;

    private String section;

    private boolean hasInternet;

    private boolean isApproved;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<Role> roles;

    private String type;

    //constructor for tests
    public User(){}
    public User(Long id, String password , String firstName, String lastName, String email) {
        this.setId(id); // super class
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
    public User(Long id, String password){
        this.setId(id);
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + getId() + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", birthDate=" + birthDate +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                ", schoolName='" + schoolName + '\'' +
                ", grade=" + grade +
                ", section='" + section + '\'' +
                ", hasInternet=" + hasInternet +
                ", isApproved='" + isApproved + '\'' +
                ", roles='" + roles + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
