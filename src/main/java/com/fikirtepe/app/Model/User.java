package com.fikirtepe.app.Model;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class User extends BaseEntity{

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

    /* it'll be edited after handling the form json*/
//    @OneToOne
//    private Parent parent;
    private long parentId;

    private String parentFirstName;

    private String parentLastName;

    private String parentEmail;

    private boolean isApproved;

    private String role ;

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
                ", parentId=" + parentId +
                ", parentFirstName='" + parentFirstName + '\'' +
                ", parentLastName='" + parentLastName + '\'' +
                ", parentEmail='" + parentEmail + '\'' +
                ", isApproved='" + isApproved + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
