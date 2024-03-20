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
public class ContractService implements Serializable{

    private Service serviceId;
    private Contract contractId;

    public ContractService() {
    }

    public ContractService(Service serviceId, Contract contractId) {
        this.serviceId = serviceId;
        this.contractId = contractId;
    }

    public Service getServiceId() {
        return serviceId;
    }

    public void setServiceId(Service serviceId) {
        this.serviceId = serviceId;
    }

    public Contract getContractId() {
        return contractId;
    }

    public void setContractId(Contract contractId) {
        this.contractId = contractId;
    }

    @Override
    public String toString() {
        return "ContractService{" + "serviceId=" + serviceId + ", contractId=" + contractId + '}';
    }

}
