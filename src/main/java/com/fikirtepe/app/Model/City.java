package com.fikirtepe.app.Model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class City {

    @Id
    private long plateNo;

    private String cityName;

}
