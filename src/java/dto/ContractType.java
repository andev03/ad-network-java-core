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
public class ContractType implements Serializable{

    private int contractNo;
    private int typeName;

    public ContractType() {
    }

    public ContractType(int contractNo, int typeName) {
        this.contractNo = contractNo;
        this.typeName = typeName;
    }

    public int getContractNo() {
        return contractNo;
    }

    public void setContractNo(int contractNo) {
        this.contractNo = contractNo;
    }

    public int getTypeName() {
        return typeName;
    }

    public void setTypeName(int typeName) {
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        return "ContractType{" + "contractNo=" + contractNo + ", typeName=" + typeName + '}';
    }

}
