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
public class ReStatus implements Serializable{

    private int statusNo;
    private String statusName;

    public ReStatus() {
    }

    public ReStatus(int statusNo, String statusName) {
        this.statusNo = statusNo;
        this.statusName = statusName;
    }

    public int getStatusNo() {
        return statusNo;
    }

    public void setStatusNo(int statusNo) {
        this.statusNo = statusNo;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    @Override
    public String toString() {
        return "ReStatus{" + "statusNo=" + statusNo + ", statusName=" + statusName + '}';
    }

}
