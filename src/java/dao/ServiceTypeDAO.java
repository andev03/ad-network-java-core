/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Device;
import dto.ServiceType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import utils.DBUtil;

/**
 *
 * @author nguye
 */
public class ServiceTypeDAO implements I_ServiceTypeDAO {

    @Override
    public ServiceType getServiceType(int sTypeId) {
        ServiceType rs = null;
        Connection cn = null;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqlServiceType = "Select [sTypeId], [sTypeDesc]\n"
                        + "From ServiceType\n"
                        + "where [sTypeId] = ?";
                PreparedStatement pstServiceType = cn.prepareStatement(sqlServiceType);
                pstServiceType.setInt(1, sTypeId);
                ResultSet tableDevice = pstServiceType.executeQuery();

                if (tableDevice != null && tableDevice.next()) {
                    int s_sTypeId = tableDevice.getInt("sTypeId");
                    String s_sTypeDesc = tableDevice.getString("sTypeDesc");

                    rs = new ServiceType(s_sTypeId, s_sTypeDesc);
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

}
