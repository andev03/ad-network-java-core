/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Status;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import utils.DBUtil;

/**
 *
 * @author nguye
 */
public class StatusDAO implements I_StatusDAO {

    @Override
    public Status getStatus(int statusId) {
        Connection cn = null;
        Status rs = null;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqlStatus = "Select [statusNo], [statusName] \n"
                        + "From [Status]\n"
                        + "Where [statusNo] = ?";
                PreparedStatement pstStatus = cn.prepareStatement(sqlStatus);
                pstStatus.setInt(1, statusId);

                ResultSet tableStatus = pstStatus.executeQuery();

                if (tableStatus != null && tableStatus.next()) {
                    int s_statusNo = tableStatus.getInt("statusNo");
                    String s_StatusName = tableStatus.getString("statusName");

                    rs = new Status(s_statusNo, s_StatusName);
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
