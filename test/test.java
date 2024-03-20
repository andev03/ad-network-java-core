
import dao.ContractDAO;
import dao.CustomerDAO;
import dao.DeviceDAO;
import dao.EmployeeDAO;
import dao.LocationDAO;
import dao.LogErrorDAO;
import dao.ProvinceDAO;
import dao.RequestDAO;
import dao.ServiceDAO;
import dao.ServiceStatusTypeDAO;
import dao.TransactionDAO;
import dto.Contract;
import dto.Customer;
import dto.Device;
import dto.Employee;
import dto.Location;
import dto.Province;
import dto.Request;
import dto.Service;
import dto.ServiceStatusType;
import dto.Transaction;
import java.sql.Timestamp;
import java.util.ArrayList;
import utils.Pagination;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author nguye
 */
public class test {

    public static void main(String[] args) {
//        for (Customer string : new CustomerDAO().sortCustomerByAction(1, 5, "sortByStatus")) {
//            System.out.println(string);
//        }
//        for (Customer string : new CustomerDAO().getCustomerByScope(5, 10)) {
//            System.out.println(string);
//        }

        for (Contract contract : new ContractDAO().getAllContract()) {
            System.out.println(contract);
        }
    }
}
