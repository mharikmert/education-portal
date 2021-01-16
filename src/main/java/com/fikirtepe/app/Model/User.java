package com.fikirtepe.app.Model;
import lombok.Data;
import javax.persistence.*;

@Data
@Entity
public class User{

    @Id
    private long id;

    private String first_name;

    private String last_name;

    private String password;

    private String address;

    private String phone_number;

    private String city;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
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

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
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
                "id=" + this.id +
                ", first_name='" + this.first_name + '\'' +
                ", last_name='" + this.last_name + '\'' +
                ", password='" + this.password + '\'' +
                ", address='" + this.address + '\'' +
                ", phone_number='" + this.phone_number + '\'' +
                ", city='" + this.city + '\'' +
                '}';
    }
}
