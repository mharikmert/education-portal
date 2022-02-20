package com.fikirtepe.app.model;

import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
@Inheritance(strategy = InheritanceType.JOINED)
public class User implements Serializable {
    @CreatedBy
    private String createdBy;

    @CreatedDate
    private Date createdDate;

    @LastModifiedBy
    private String updatedBy;

    @LastModifiedDate
    private String lastModifiedDate;

    @Id
    private long id;
    @Column(unique = true)
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String type;
    private String city;
    private String district;
    private boolean isApproved;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<Role> roles;

    public User(){}
    public User(Long id, String password){
        this.setId(id);
        this.password = password;
    }
    //create a constructor for all fields
    public User(Long id, String username, String password, 
                String firstName, String lastName, String phoneNumber, 
                String email, String type, String city, String district, 
                boolean isApproved, List<Role> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.type = type;
        this.city = city;
        this.district = district;
        this.isApproved = isApproved;
        this.roles = roles;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", approved=" + isApproved +
                ", roles=" + roles +
                '}';
    }
}
