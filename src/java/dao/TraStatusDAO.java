/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.TraStatus;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import utils.DBUtil;

/**
 *
 * @author nguye
 */
public class TraStatusDAO implements I_TraStatusDAO {

    @Override
    public TraStatus getTraStatus(int statusNo) {
        TraStatus rs = null;
        Connection cn = null;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqlTraStatus = "Select [statusNo], [statusName] \n"
                        + "From TraStatus\n"
                        + "Where statusNo = ?";

                PreparedStatement pstTraStatus = cn.prepareStatement(sqlTraStatus);
                pstTraStatus.setInt(1, statusNo);
                ResultSet tableTracStatus = pstTraStatus.executeQuery();

                if (tableTracStatus != null && tableTracStatus.next()) {
                    int ts_statusNo = tableTracStatus.getInt("statusNo");
                    String ts_statusName = tableTracStatus.getString("statusName");

                    rs = new TraStatus(ts_statusNo, ts_statusName);
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
