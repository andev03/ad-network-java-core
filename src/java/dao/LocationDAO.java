/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Location;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import utils.DBUtil;

/**
 *
 * @author user
 */
public class LocationDAO implements I_LocationDAO{

    @Override
    public Location getLocation(int locationId) {
        Location rs = null;
        Connection cn = null;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqlLocation = "Select [locationId], [provinceId], [cityId], [districtId], [userId], [locationDetail]\n"
                        + "From [Location]\n"
                        + "Where [locationId] = ?";

                PreparedStatement pstLocation = cn.prepareStatement(sqlLocation);
                pstLocation.setInt(1, locationId);

                ResultSet tableLocation = pstLocation.executeQuery();

                if (tableLocation != null && tableLocation.next()) {
                    int c_locationId = tableLocation.getInt("locationId");
                    int c_provinceId = tableLocation.getInt("provinceId");
                    int c_cityId = tableLocation.getInt("cityId");
                    int c_districtId = tableLocation.getInt("districtId");
                    int c_userId = tableLocation.getInt("userId");
                    String c_locationDetail = tableLocation.getString("locationDetail");
                    rs = new Location(c_locationId, new ProvinceDAO().getProvince(c_provinceId), new CityDAO().getCity(c_cityId), new DistrictDAO().getDistrict(c_districtId), new UsersDAO().getUsers(c_userId), c_locationDetail);
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
    public Location getLocationByUserId(int userId) {
        Location locationByUser = null;
        Connection cn = null;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqllocationByUser = "Select [locationId], [provinceId], [cityId], [districtId], [userId], [locationDetail]\n"
                        + "From [Location]\n"
                        + "Where [userId] = ?";

                PreparedStatement pstlocationByUser = cn.prepareStatement(sqllocationByUser);
                pstlocationByUser.setInt(1, userId);

                ResultSet tablelocationByUser = pstlocationByUser.executeQuery();

                if (tablelocationByUser != null && tablelocationByUser.next()) {
                    int c_locationId = tablelocationByUser.getInt("locationId");
                    int c_provinceId = tablelocationByUser.getInt("provinceId");
                    int c_cityId = tablelocationByUser.getInt("cityId");
                    int c_districtId = tablelocationByUser.getInt("districtId");
                    int c_userId = tablelocationByUser.getInt("userId");
                    String c_locationDetail = tablelocationByUser.getString("locationDetail");
                    locationByUser = new Location(c_locationId, new ProvinceDAO().getProvince(c_provinceId), new CityDAO().getCity(c_cityId), new DistrictDAO().getDistrict(c_districtId), new UsersDAO().getUsersForViewTechni(c_userId), c_locationDetail);
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
        return locationByUser;
    }
}
