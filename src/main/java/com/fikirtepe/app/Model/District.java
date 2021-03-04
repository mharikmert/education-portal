package com.fikirtepe.app.Model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class District {

    @Id
    private long id;

    private long cityPlateNo;

    private String districtName;
}
