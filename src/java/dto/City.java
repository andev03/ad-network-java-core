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
public class City implements Serializable {

    private int cityId;
    private Province provinceId;
    private String cityName;

    public City() {
    }

    public City(int cityId, Province provinceId, String cityName) {
        this.cityId = cityId;
        this.provinceId = provinceId;
        this.cityName = cityName;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public Province getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Province provinceId) {
        this.provinceId = provinceId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public String toString() {
        return "City{" + "cityId=" + cityId + ", provinceId=" + provinceId + ", cityName=" + cityName + '}';
    }

}
