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
public class Contract implements Serializable {

    private int contractId;
    private Customer cusId;
    private Employee empId;
    private Transaction transactionId;
    private CoStatus statusNo;
    private ContractType contractNo;
    private String startDate;
    private String endDate;

    public Contract() {
    }

    public Contract(int contractId, Customer cusId, Employee empId, Transaction transactionId, CoStatus statusNo, ContractType contractNo, String startDate, String endDate) {
        this.contractId = contractId;
        this.cusId = cusId;
        this.empId = empId;
        this.transactionId = transactionId;
        this.statusNo = statusNo;
        this.contractNo = contractNo;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Contract(int contractId, Customer cusId) {
        this.contractId = contractId;
        this.cusId = cusId;
    }

    public int getContractId() {
        return contractId;
    }

    public void setContractId(int contractId) {
        this.contractId = contractId;
    }

    public Customer getCusId() {
        return cusId;
    }

    public void setCusId(Customer cusId) {
        this.cusId = cusId;
    }

    public Employee getEmpId() {
        return empId;
    }

    public void setEmpId(Employee empId) {
        this.empId = empId;
    }

    public Transaction getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Transaction transactionId) {
        this.transactionId = transactionId;
    }

    public CoStatus getStatusNo() {
        return statusNo;
    }

    public void setStatusNo(CoStatus statusNo) {
        this.statusNo = statusNo;
    }

    public ContractType getContractNo() {
        return contractNo;
    }

    public void setContractNo(ContractType contractNo) {
        this.contractNo = contractNo;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Contract{" + "contractId=" + contractId + ", cusId=" + cusId + ", empId=" + empId + ", transactionId=" + transactionId + ", statusNo=" + statusNo + ", contractNo=" + contractNo + ", startDate=" + startDate + ", endDate=" + endDate + '}';
    }

}
