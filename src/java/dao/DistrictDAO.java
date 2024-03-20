/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.District;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import utils.DBUtil;

/**
 *
 * @author user
 */
public class DistrictDAO implements I_DistrictDAO{

    @Override
    public District getDistrict(int districtId) {
        District rs = null;
        Connection cn = null;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqlDistrict = "Select [districtId], [cityId], [districtName]\n"
                        + "From [District]\n"
                        + "Where [districtId] = ?";

                PreparedStatement pstDistrict = cn.prepareStatement(sqlDistrict);
                pstDistrict.setInt(1, districtId);

                ResultSet tableDistrict = pstDistrict.executeQuery();

                if (tableDistrict != null && tableDistrict.next()) {
                    int c_districtId = tableDistrict.getInt("districtId");
                    int c_cityId = tableDistrict.getInt("cityId");
                    String c_districtName = tableDistrict.getString("districtName");

                    rs = new District(c_districtId, new CityDAO().getCity(c_cityId), c_districtName);

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
