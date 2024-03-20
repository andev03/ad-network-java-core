/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author nguye
 */
public interface I_ContractRequestDAO {

    int insertContractRequest(int contractId, int requestId);
    
    int getContractIdByRequestId(int requestId);
}
