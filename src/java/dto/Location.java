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
public class Location implements Serializable {

    private int locationId;
    private Province provinceId;
    private City cityId;
    private District districtId;
    private Users userId;
    private String locationDetail;

    public Location() {
    }

    public Location(int locationId, Province provinceId, City cityId, District districtId, Users userId, String locationDetail) {
        this.locationId = locationId;
        this.provinceId = provinceId;
        this.cityId = cityId;
        this.districtId = districtId;
        this.userId = userId;
        this.locationDetail = locationDetail;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public Province getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Province provinceId) {
        this.provinceId = provinceId;
    }

    public City getCityId() {
        return cityId;
    }

    public void setCityId(City cityId) {
        this.cityId = cityId;
    }

    public District getDistrictId() {
        return districtId;
    }

    public void setDistrictId(District districtId) {
        this.districtId = districtId;
    }

    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
        this.userId = userId;
    }

    public String getLocationDetail() {
        return locationDetail;
    }

    public void setLocationDetail(String locationDetail) {
        this.locationDetail = locationDetail;
    }

    @Override
    public String toString() {
        return "Location{" + "locationId=" + locationId + ", provinceId=" + provinceId + ", cityId=" + cityId + ", districtId=" + districtId + ", userId=" + userId + ", locationDetail=" + locationDetail + '}';
    }

}
