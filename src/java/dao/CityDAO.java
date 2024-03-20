/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.City;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import utils.DBUtil;

/**
 *
 * @author user
 */
public class CityDAO implements I_CityDAO {

    @Override
    public City getCity(int cityId) {
        City rs = null;
        Connection cn = null;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqlCity = "Select [cityId], [provinceId], [cityName]\n"
                        + "From [City]\n"
                        + "Where [cityId] = ?";

                PreparedStatement pstCity = cn.prepareStatement(sqlCity);
                pstCity.setInt(1, cityId);

                ResultSet tableCity = pstCity.executeQuery();

                if (tableCity != null && tableCity.next()) {
                    int c_cityId = tableCity.getInt("cityId");
                    int c_provinceId = tableCity.getInt("provinceId");
                    String c_provinceName = tableCity.getString("cityName");

                    rs = new City(c_cityId, new ProvinceDAO().getProvince(c_provinceId), c_provinceName);

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
