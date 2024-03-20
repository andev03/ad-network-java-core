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
public class Transaction implements Serializable {

    private int transactionId;
    Customer cusId;
    private Payment typeId;
    private TraStatus statusNo;
    private int transCycle;
    private double transAmount;
    private String transTime;

    public Transaction() {
    }

    public Transaction(int transactionId, Customer cusId, Payment typeId, TraStatus statusNo, int transCycle, double transAmount, String transTime) {
        this.transactionId = transactionId;
        this.typeId = typeId;
        this.statusNo = statusNo;
        this.cusId = cusId;
        this.transCycle = transCycle;
        this.transAmount = transAmount;
        this.transTime = transTime;
    }

    public Transaction(int transactionId, double transAmount) {
        this.transactionId = transactionId;
        this.transAmount = transAmount;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public Payment getTypeId() {
        return typeId;
    }

    public void setTypeId(Payment typeId) {
        this.typeId = typeId;
    }

    public TraStatus getStatusNo() {
        return statusNo;
    }

    public void setStatusNo(TraStatus statusNo) {
        this.statusNo = statusNo;
    }

    public Customer getCusId() {
        return cusId;
    }

    public void setCusId(Customer cusId) {
        this.cusId = cusId;
    }

    public int getTransCycle() {
        return transCycle;
    }

    public void setTransCycle(int transCycle) {
        this.transCycle = transCycle;
    }

    public double getTransAmount() {
        return transAmount;
    }

    public void setTransAmount(double transAmount) {
        this.transAmount = transAmount;
    }

    public String getTransTime() {
        return transTime;
    }

    public void setTransTime(String transTime) {
        this.transTime = transTime;
    }

    @Override
    public String toString() {
        return "Transaction{" + "transactionId=" + transactionId + ", typeId=" + typeId + ", statusNo=" + statusNo + ", cusId=" + cusId + ", transCycle=" + transCycle + ", transAmount=" + transAmount + ", transTime=" + transTime + '}';
    }

}
