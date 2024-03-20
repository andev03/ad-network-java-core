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
public class Service implements Serializable{

    private int serviceId;
    private Device deviceId;
    private ServiceType sTypeId;
    private double price;
    private String serviceName;
    private ServiceStatusType serviceStatus;

    public Service() {
    }

    public Service(int serviceId, Device deviceId, ServiceType sTypeId, double price, String serviceName, ServiceStatusType serviceStatus) {
        this.serviceId = serviceId;
        this.deviceId = deviceId;
        this.sTypeId = sTypeId;
        this.price = price;
        this.serviceName = serviceName;
        this.serviceStatus = serviceStatus;
    }
    
    public Service(int serviceId, Device deviceId, ServiceType sTypeId, double price, String serviceName) {
        this.serviceId = serviceId;
        this.deviceId = deviceId;
        this.sTypeId = sTypeId;
        this.price = price;
        this.serviceName = serviceName;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public Device getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Device deviceId) {
        this.deviceId = deviceId;
    }

    public ServiceType getsTypeId() {
        return sTypeId;
    }

    public void setsTypeId(ServiceType sTypeId) {
        this.sTypeId = sTypeId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public ServiceStatusType getServiceStatus() {
        return serviceStatus;
    }

    public void setServiceStatus(ServiceStatusType serviceStatus) {
        this.serviceStatus = serviceStatus;
    }

    @Override
    public String toString() {
        return "Service{" + "serviceId=" + serviceId + ", deviceId=" + deviceId + ", sTypeId=" + sTypeId + ", price=" + price + ", serviceName=" + serviceName + ", serviceStatus=" + serviceStatus + '}';
    }

}
