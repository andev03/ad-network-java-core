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
public class Invoice implements Serializable {

    private int invoiceId;
    private Transaction transactionId;
    private String createTime;

    public Invoice() {
    }

    public Invoice(int invoiceId, Transaction transactionId, String createTime) {
        this.invoiceId = invoiceId;
        this.transactionId = transactionId;
        this.createTime = createTime;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Transaction getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Transaction transactionId) {
        this.transactionId = transactionId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Invoice{" + "invoiceId=" + invoiceId + ", transactionId=" + transactionId + ", createTime=" + createTime + '}';
    }

}
