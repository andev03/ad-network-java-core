/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Employee;
import java.util.ArrayList;

/**
 *
 * @author nguye
 */
public interface I_EmployeeDAO {

    Employee getEmployee(String email, String password);

    Employee getEmployeeByAccId(int accId);
    
    Employee getTechnician(int accId);
    
    ArrayList<Employee> getAllTechnician();
    
    ArrayList<Employee> getTechnicianByScope(int beginScope, int endScope);
    
    int quantityTechnician();
    
    ArrayList<Employee> getScopeEmployeeByEmail(int beginScope, int endScope, String email);
    
    Employee getEmployeeByAccIdForContract(int accId);
    
    int updateStatusTechni(int accId, int statusNo);
    
    ArrayList<Employee> getAllTechnicianActive();
    
    ArrayList<Employee> sortEmployeeByAction(int beginCus, int endCus, String action);
}
