/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Service;
import java.util.ArrayList;

/**
 *
 * @author nguye
 */
public interface I_ServiceDAO {

    Service getService(int serviceId);

    ArrayList<Service> getServiceByScope(int scopeBegin, int scopeEnd);

    ArrayList<Service> getAllService();

    int quantityService();

    ArrayList<Service> getServiceByType(int sTypeId);

    Service getServiceForCartByName(String name);

    int createService(int deviceId, int sTypeId, double price, String serviceName);

    int updatePriceService(int serviceId, double servicePrice);

    int updateStatusService(int serviceId, int serviceStatus);

    int updatePriceAndStatusService(int serviceId, double servicePrice, int serviceStatus);
    
    ArrayList<Service> getScopeServiceByName(int beginScope, int endScope, String nameService);
}
