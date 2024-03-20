/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Contract;
import java.util.ArrayList;

/**
 *
 * @author nguye
 */
public interface I_ContractDAO {

    Contract getContract(int contractId);

    ArrayList<Contract> getAllContract();

    ArrayList<Contract> getContractByScope(int scopeBegin, int scopeEnd);

    int quantityContract();

    ArrayList<Contract> getContractByScopeForViewContract(int scopeBegin, int scopeEnd);

    int createContract(int empId, int cusId, int contracId);

    ArrayList<Contract> getContractForCreate();

    int insertContractForBuy(int cusId, int transactionId, int contractNo, int serviceId);

    int insertContractForCart(int cusId, int transactionId, int contractNo);

    ArrayList<Contract> getContractByCustomerId(int cusId);
    
    int inserContractForExtend(int cusId, int transactionId, int contractNo, int numberContract);
    
    int updateStatusContractForExtend(int contractId);
    
    int updateInformationContractForUpdateContract(int empId, int contractId);
    
    int getTransactionIdByContractId(int contractId);
    
    int updateStatusContractAfterUpdate(int contractId);
    
    ArrayList<Contract> getScopeContractCustomerByName(int beginScope, int endScope, String nameCustomer);
}
