package com.fikirtepe.app.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
public class User{

    @Id
    private long id;

    private String firstName;

    private String lastName;

    private String password;

    @JsonFormat(pattern = "mm-dd-yyyy")
    private LocalDate birthDate;

    private String address;

    private String phoneNumber;

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
