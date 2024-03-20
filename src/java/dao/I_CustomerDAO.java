/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Customer;
import java.util.ArrayList;

/**
 *
 * @author nguye
 */
public interface I_CustomerDAO {

    Customer getCustomer(String telephoneNumber, String password);

    Customer getCustomerByTelephoneNumber(String telephoneNumber);

    ArrayList<Customer> getScopeCustomerByTelephone(int beginScope, int endScope, String telephoneNumber);

    ArrayList<Customer> getAllCustomer();

    int updateStatusCus(int cusId, int status);

    Customer getCustomerById(int accId);

    ArrayList<Customer> getCustomerByScope(int scopeBegin, int scopeEnd);

    int quantityCustomer();

    int quantityCustomerSearch();
    
    int insertLogErrCus(int accId, int logDetailId);
    
    Customer getCustomerForViewTechni(int cusId);
    
    Customer getCustomerForViewContract(int cusId);
    
    ArrayList<Customer> sortCustomerByAction(int beginCus, int endCus, String action);
    
    int insertCustomerRegister(int UserId, String telephone, String password);
    
    boolean isTelephoneRegister(String telephone);
    
}
