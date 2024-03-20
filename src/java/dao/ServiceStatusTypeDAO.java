/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Device;
import dto.ServiceStatusType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import utils.DBUtil;

/**
 *
 * @author nguye
 */
public class ServiceStatusTypeDAO implements I_ServiceStatusTypeDAO {

    @Override
    public ServiceStatusType getServiceStatusType(int serviceStatus) {
        ServiceStatusType rs = null;
        Connection cn = null;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqlDevice = "Select [serviceStatus], [typeName]\n"
                        + "From serviceStatusType\n"
                        + "where [serviceStatus] = ?";
                PreparedStatement pstServiceStatusType = cn.prepareStatement(sqlDevice);
                pstServiceStatusType.setInt(1, serviceStatus);
                ResultSet tableServiceStatusType = pstServiceStatusType.executeQuery();

                if (tableServiceStatusType != null && tableServiceStatusType.next()) {
                    int st_ServiceStatus = tableServiceStatusType.getInt("serviceStatus");
                    String st_typeName = tableServiceStatusType.getString("typeName");

                    rs = new ServiceStatusType(st_ServiceStatus, st_typeName);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return rs;
    }

    @Override
    public ArrayList<ServiceStatusType> getAllServiceStatusType() {
        ArrayList<ServiceStatusType> allServiceStatusType = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqlAllServiceStatusType = "Select [serviceStatus], [typeName] \n"
                        + "From serviceStatusType";
                Statement stAllServiceStatusType = cn.createStatement();

                ResultSet tableAllServiceStatusType = stAllServiceStatusType.executeQuery(sqlAllServiceStatusType);
                if (tableAllServiceStatusType != null) {
                    while (tableAllServiceStatusType.next()) {
                        int st_ServiceStatus = tableAllServiceStatusType.getInt("serviceStatus");
                        String st_typeName = tableAllServiceStatusType.getString("typeName");
                        allServiceStatusType.add(new ServiceStatusType(st_ServiceStatus, st_typeName));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return allServiceStatusType;
    }

}
