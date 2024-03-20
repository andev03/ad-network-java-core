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
public class ServiceStatusType implements Serializable{

    private int serviceStatus;
    private String typeName;

    public ServiceStatusType() {
    }

    public ServiceStatusType(int serviceStatus, String typeName) {
        this.serviceStatus = serviceStatus;
        this.typeName = typeName;
    }

    public int getServiceStatus() {
        return serviceStatus;
    }

    public void setServiceStatus(int serviceStatus) {
        this.serviceStatus = serviceStatus;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        return "ServiceStatusType{" + "serviceStatus=" + serviceStatus + ", typeName=" + typeName + '}';
    }

}
