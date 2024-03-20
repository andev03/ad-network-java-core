/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Request;
import java.util.ArrayList;

/**
 *
 * @author nguye
 */
public interface I_RequestDAO {

    Request getRequestById(int requestId);

    ArrayList<Request> getAllRequest();

    ArrayList<Request> getRequestByScope(int beginScope, int endScope);

    int quantityRequest();

    ArrayList<Request> getRequestByCusId(int cusId, int beginCus, int endCus);

    ArrayList<Request> getRequestForView(int cusId);

    int updateStatusForTechni(int status, int empId, int requestId);

    ArrayList<Request> requestTechniByDate(String date, int empId);

    ArrayList<Request> requestShowByChoice(int statusNo, int empId);

    ArrayList<Request> getRequestByStatus(int reStatus, int reType);

    int updateStatusRequestForCreateContract(int cusId, int contractId);

    ArrayList<Request> requestForManageRequest(int empId);

    int insertRequestForBuy(int cusId, int contractId);

    ArrayList<Request> requestForManageRequestOfAdmin(int beginCus, int endCus);
    
    int insertRequestForExtend(int cusId, int contractId);
    
    int updateStatusRequestByContractId(int empId, int contractId);
    
    ArrayList<Request> getScopeRequestByNameCustomer(int beginScope, int endScope, String nameCustomer);
    
    ArrayList<Request> getAllRequestByCusId(int cusId);
    
    int setRequestToTechni(int empId, int statusNo, String sqlQueries);
    
}
