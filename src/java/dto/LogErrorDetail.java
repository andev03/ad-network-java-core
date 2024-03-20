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
public class LogErrorDetail implements Serializable{
    private int logDetailId;
    private String logDetail;

    public LogErrorDetail() {
    }

    public LogErrorDetail(int logDetailId, String logDetail) {
        this.logDetailId = logDetailId;
        this.logDetail = logDetail;
    }

    public int getLogDetailId() {
        return logDetailId;
    }

    public void setLogDetailId(int logDetailId) {
        this.logDetailId = logDetailId;
    }

    public String getLogDetail() {
        return logDetail;
    }

    public void setLogDetail(String logDetail) {
        this.logDetail = logDetail;
    }

    @Override
    public String toString() {
        return "LogErrorDetail{" + "logDetailId=" + logDetailId + ", logDetail=" + logDetail + '}';
    }
}
