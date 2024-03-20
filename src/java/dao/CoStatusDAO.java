/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.CoStatus;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import utils.DBUtil;

/**
 *
 * @author nguye
 */
public class CoStatusDAO implements I_CoStatusDAO {

    @Override
    public CoStatus getCoStatus(int coStatusNo) {
        CoStatus rs = null;
        Connection cn = null;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqlCoStatus = "Select [statusNo], [statusName] \n"
                        + "From CoStatus\n"
                        + "Where [statusNo] = ?";

                PreparedStatement pstCoStatus = cn.prepareStatement(sqlCoStatus);
                pstCoStatus.setInt(1, coStatusNo);

                ResultSet tableCoStatus = pstCoStatus.executeQuery();

                if (tableCoStatus != null && tableCoStatus.next()) {
                    int cs_statusNo = tableCoStatus.getInt("statusNo");
                    String cs_statusName = tableCoStatus.getString("statusName");

                    rs = new CoStatus(cs_statusNo, cs_statusName);
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
