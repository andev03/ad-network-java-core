/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.ContractService;
import java.util.ArrayList;

/**
 *
 * @author nguye
 */
public interface I_ContracServiceDAO {
    ContractService getContractService(int serviceId, int contractId);
    
    int insertContractService(int serviceId, int contractId);
    
    ArrayList<ContractService>  getContractServiceByContract(int contractId);
}
