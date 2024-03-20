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
public class Users implements Serializable{

    private int userId;
    private String phoneNumber;
    private String fullName;
    private String email;
    private String identityNo;

    public Users() {
    }

    public Users(int userId, String phoneNumber, String fullName, String email, String identityNo) {
        this.userId = userId;
        this.phoneNumber = phoneNumber;
        this.fullName = fullName;
        this.email = email;
        this.identityNo = identityNo;
    }
    
    public Users(int userId, String phoneNumber, String fullName) {
        this.userId = userId;
        this.phoneNumber = phoneNumber;
        this.fullName = fullName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdentityNo() {
        return identityNo;
    }

    public void setIdentityNo(String identityNo) {
        this.identityNo = identityNo;
    }

    @Override
    public String toString() {
        return "Users{" + "userId=" + userId + ", phoneNumber=" + phoneNumber + ", fullName=" + fullName + ", email=" + email + ", identityNo=" + identityNo + '}';
    }

}
