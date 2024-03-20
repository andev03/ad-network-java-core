/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.ServiceStatusType;
import java.util.ArrayList;

/**
 *
 * @author nguye
 */
public interface I_ServiceStatusTypeDAO {
    ServiceStatusType getServiceStatusType(int serviceStatus);
    ArrayList<ServiceStatusType> getAllServiceStatusType();
}
