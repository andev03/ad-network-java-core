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
public class Employee implements Serializable {

    private int accId;
    private Users userId;
    private Status statusNo;
    private Roles roleId;
    private String accEmail;
    private String accPassEmp;

    public Employee() {
    }

    public Employee(int accId, Users userId, Status statusNo, Roles roleId, String accEmail, String accPassEmp) {
        this.accId = accId;
        this.userId = userId;
        this.statusNo = statusNo;
        this.roleId = roleId;
        this.accEmail = accEmail;
        this.accPassEmp = accPassEmp;
    }

    public Employee(int accId, Users userId, Roles roleId) {
        this.accId = accId;
        this.userId = userId;
        this.roleId = roleId;
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

    public Status getStatusNo() {
        return statusNo;
    }

    public void setStatusNo(Status statusNo) {
        this.statusNo = statusNo;
    }

    public Roles getRoleId() {
        return roleId;
    }

    public void setRoleId(Roles roleId) {
        this.roleId = roleId;
    }

    public String getAccEmail() {
        return accEmail;
    }

    public void setAccEmail(String accEmail) {
        this.accEmail = accEmail;
    }

    public String getAccPassEmp() {
        return accPassEmp;
    }

    public void setAccPassEmp(String accPassEmp) {
        this.accPassEmp = accPassEmp;
    }

    @Override
    public String toString() {
        return "Employee{" + "accId=" + accId + ", userId=" + userId + ", statusNo=" + statusNo + ", roleId=" + roleId + ", accEmail=" + accEmail + ", accPassEmp=" + accPassEmp + '}';
    }

}
