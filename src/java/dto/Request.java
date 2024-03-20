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
public class Request implements Serializable {

    private int requestId;
    private Customer cusId;
    private Employee empId;
    private Contract contractId;
    private ReStatus statusNo;
    private Retype requestTypeId;
    private String requestDate;
    private String finishDate;
    private String requestContent;

    public Request() {
    }

    public Request(int requestId, Customer cusId, Employee empId, Contract contractId, ReStatus statusNo, Retype requestTypeId, String requestDate, String finishDate, String requestContent) {
        this.requestId = requestId;
        this.cusId = cusId;
        this.empId = empId;
        this.contractId = contractId;
        this.statusNo = statusNo;
        this.requestTypeId = requestTypeId;
        this.requestDate = requestDate;
        this.finishDate = finishDate;
        this.requestContent = requestContent;
    }

    public Request(int requestId, Customer cusId, ReStatus statusNo, Retype requestTypeId, String requestDate, String requestContent) {
        this.requestId = requestId;
        this.cusId = cusId;
        this.statusNo = statusNo;
        this.requestTypeId = requestTypeId;
        this.requestDate = requestDate;
        this.requestContent = requestContent;
    }

    public Request(int requestId, Customer cusId, Employee empId, Contract contractId, ReStatus statusNo, Retype requestTypeId, String requestDate, String requestContent) {
        this.requestId = requestId;
        this.cusId = cusId;
        this.empId = empId;
        this.contractId = contractId;
        this.statusNo = statusNo;
        this.requestTypeId = requestTypeId;
        this.requestDate = requestDate;
        this.requestContent = requestContent;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
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

    public Contract getContractId() {
        return contractId;
    }

    public void setContractId(Contract contractId) {
        this.contractId = contractId;
    }

    public ReStatus getStatusNo() {
        return statusNo;
    }

    public void setStatusNo(ReStatus statusNo) {
        this.statusNo = statusNo;
    }

    public Retype getRequestTypeId() {
        return requestTypeId;
    }

    public void setRequestTypeId(Retype requestTypeId) {
        this.requestTypeId = requestTypeId;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public String getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(String finishDate) {
        this.finishDate = finishDate;
    }

    public String getRequestContent() {
        return requestContent;
    }

    public void setRequestContent(String requestContent) {
        this.requestContent = requestContent;
    }

    @Override
    public String toString() {
        return "Request{" + "requestId=" + requestId + ", cusId=" + cusId + ", empId=" + empId + ", contractId=" + contractId + ", statusNo=" + statusNo + ", requestTypeId=" + requestTypeId + ", requestDate=" + requestDate + ", finishDate=" + finishDate + ", requestContent=" + requestContent + '}';
    }

}
