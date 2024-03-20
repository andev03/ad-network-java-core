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
public class Customer implements Serializable{

    private int accId;
    private Users userId;
    private String accTel;
    private Status statusNo;
    private String accPassCus;

    public Customer() {
    }

    public Customer(int accId, Users userId, String accTel, Status statusNo, String accPassCus) {
        this.accId = accId;
        this.userId = userId;
        this.accTel = accTel;
        this.statusNo = statusNo;
        this.accPassCus = accPassCus;
    }
    
    public Customer(int accId, Users userId, String accTel) {
        this.accId = accId;
        this.userId = userId;
        this.accTel = accTel;
    }

    public int getAccId() {
        return accId;
    }

    public void setAccId(int accId) {
        this.accId = accId;
    }

    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
        this.userId = userId;
    }

    public String getAccTel() {
        return accTel;
    }

    public void setAccTel(String accTel) {
        this.accTel = accTel;
    }

    public Status getStatusNo() {
        return statusNo;
    }

    public void setStatusNo(Status statusNo) {
        this.statusNo = statusNo;
    }

    public String getAccPassCus() {
        return accPassCus;
    }

    public void setAccPassCus(String accPassCus) {
        this.accPassCus = accPassCus;
    }

    @Override
    public String toString() {
        return "Customer{" + "accId=" + accId + ", userId=" + userId + ", accTel=" + accTel + ", statusNo=" + statusNo + ", accPassCus=" + accPassCus + '}';
    }

}
