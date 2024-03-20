/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;

/**
 *
 * @author nguye
 */
public class District implements Serializable{

    private int districtId;
    private City cityId;
    private String districtName;

    public District() {
    }

    public District(int districtId, City cityId, String districtName) {
        this.districtId = districtId;
        this.cityId = cityId;
        this.districtName = districtName;
    }

    public int getDistrictId() {
        return districtId;
    }

    public void setDistrictId(int districtId) {
        this.districtId = districtId;
    }

    public City getCityId() {
        return cityId;
    }

    public void setCityId(City cityId) {
        this.cityId = cityId;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    @Override
    public String toString() {
        return "District{" + "districtId=" + districtId + ", cityId=" + cityId + ", districtName=" + districtName + '}';
    }

}
