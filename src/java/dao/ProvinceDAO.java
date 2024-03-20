/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Province;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import utils.DBUtil;

/**
 *
 * @author user
 */
public class ProvinceDAO implements I_ProvinceDAO {

    @Override
    public Province getProvince(int provinceId) {
        Province rs = null;
        Connection cn = null;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqlProvince = "Select [provinceId], [provinceName]\n"
                        + "From [Province]\n"
                        + "Where [provinceId] = ?";

                PreparedStatement pstProvince = cn.prepareStatement(sqlProvince);
                pstProvince.setInt(1, provinceId);

                ResultSet tableProvince = pstProvince.executeQuery();

                if (tableProvince != null && tableProvince.next()) {
                    int c_provinceId = tableProvince.getInt("provinceId");
                    String c_provinceName = tableProvince.getString("provinceName");

                    rs = new Province(c_provinceId, c_provinceName);

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
    public ArrayList<Province> getAllProvince() {
        ArrayList<Province> allProvince = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqlAllProvince = "Select [provinceId], [provinceName] \n"
                        + "From [Province]";
                Statement stAllProvince = cn.createStatement();

                ResultSet tableAllProvince = stAllProvince.executeQuery(sqlAllProvince);
                if (tableAllProvince != null) {
                    while (tableAllProvince.next()) {
                        int p_provinceId = tableAllProvince.getInt("provinceId");
                        String p_provinceName = tableAllProvince.getString("provinceName");
                        allProvince.add(new Province(p_provinceId, p_provinceName));
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
        return allProvince;
    }
}
