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

    public long getPlateNo() {
        return plateNo;
    }

    public void setPlateNo(long plateNo) {
        this.plateNo = plateNo;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
