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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCityPlateNo() {
        return cityPlateNo;
    }

    public void setCityPlateNo(long cityPlateNo) {
        this.cityPlateNo = cityPlateNo;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }
}
