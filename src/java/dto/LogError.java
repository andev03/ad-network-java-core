/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;

/**
 *
 * @author user
 */
public class LogError implements Serializable {
    private int logId;
    private Customer accId;
    private LogErrorDetail logDetailId;

    public LogError() {
    }

    public LogError(int logId, Customer accId, LogErrorDetail logDetailId) {
        this.logId = logId;
        this.accId = accId;
        this.logDetailId = logDetailId;
    }

    public int getLogId() {
        return logId;
    }

    public void setLogId(int logId) {
        this.logId = logId;
    }

    public Customer getAccId() {
        return accId;
    }

    public void setAccId(Customer accId) {
        this.accId = accId;
    }

    public LogErrorDetail getLogDetailId() {
        return logDetailId;
    }

    public void setLogDetailId(LogErrorDetail logDetailId) {
        this.logDetailId = logDetailId;
    }

    @Override
    public String toString() {
        return "LogError{" + "logId=" + logId + ", accId=" + accId + ", logDetailId=" + logDetailId + '}';
    }
    
}
