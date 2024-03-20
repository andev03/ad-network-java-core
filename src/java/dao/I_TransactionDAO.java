/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;


import dto.Transaction;
import java.util.ArrayList;

/**
 *
 * @author nguye
 */
public interface I_TransactionDAO {

    ArrayList<Transaction> getAllTransactions();

    Transaction getTransactionById(int transactionId);

    ArrayList<Transaction> getTransactionByScope(int beginScope, int endScope);

    int quantityTracnsaction();

    ArrayList<Transaction> getTransactionByDate(int beginScope, int endScope, String date);

    Transaction getTransactionByIdForViewContract(int transactionId);

    int insertForBuy(int cusId, int typeId, int transCycle, double transAmount, int serviceId);

    int insertForCart(int cusId, int typeId, int transCycle, double transAmount);
    
    ArrayList<Transaction> sortByAction(int beginCus, int endCus, String action);
    
    int insertTransactionForExtend(int cusId, int typeId, int transCycle, double transAmount);
    
    int updateTransaction(int transactionId);
    
    ArrayList<Transaction> getScopePaymentByNameCustomer(int beginScope, int endScope, String nameCustomer);
    
}
