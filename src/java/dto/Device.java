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
public class Device implements Serializable {

    private int deviceId;
    private String deviceName;
    private String deviceDetailDesc;

    public Device() {
    }

    public Device(int deviceId, String deviceName, String deviceDetailDesc) {
        this.deviceId = deviceId;
        this.deviceName = deviceName;
        this.deviceDetailDesc = deviceDetailDesc;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceDetailDesc() {
        return deviceDetailDesc;
    }

    public void setDeviceDetailDesc(String deviceDetailDesc) {
        this.deviceDetailDesc = deviceDetailDesc;
    }

    @Override
    public String toString() {
        return "Device{" + "deviceId=" + deviceId + ", deviceName=" + deviceName + ", deviceDetailDesc=" + deviceDetailDesc + '}';
    }

}
