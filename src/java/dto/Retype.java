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
public class Retype implements Serializable{

    private int requestTypeId;
    private String typeName;

    public Retype() {
    }

    public Retype(int requestTypeId, String typeName) {
        this.requestTypeId = requestTypeId;
        this.typeName = typeName;
    }

    public int getRequestTypeId() {
        return requestTypeId;
    }

    public void setRequestTypeId(int requestTypeId) {
        this.requestTypeId = requestTypeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        return "Retype{" + "requestTypeId=" + requestTypeId + ", typeName=" + typeName + '}';
    }

}
