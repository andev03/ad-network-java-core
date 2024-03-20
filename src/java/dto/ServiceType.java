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
public class ServiceType implements Serializable{

    private int sTypeId;
    private String sTypeDesc;

    public ServiceType() {
    }

    public ServiceType(int sTypeId, String sTypeDesc) {
        this.sTypeId = sTypeId;
        this.sTypeDesc = sTypeDesc;
    }

    public int getsTypeId() {
        return sTypeId;
    }

    public void setsTypeId(int sTypeId) {
        this.sTypeId = sTypeId;
    }

    public String getsTypeDesc() {
        return sTypeDesc;
    }

    public void setsTypeDesc(String sTypeDesc) {
        this.sTypeDesc = sTypeDesc;
    }

    @Override
    public String toString() {
        return "ServiceType{" + "sTypeId=" + sTypeId + ", sTypeDesc=" + sTypeDesc + '}';
    }

}
