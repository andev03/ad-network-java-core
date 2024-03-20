/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.LogError;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import utils.DBUtil;

/**
 *
 * @author nguye
 */
public class LogErrorDAO implements I_LogErrorDAO {

    @Override
    public LogError getLogError(int logAccId) {
        LogError rs = null;
        Connection cn = null;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqlLogError = "Select Top 1 [logId], [accId], [logDetailId] From LogError\n"
                        + "Where accId = ? \n"
                        + "Order By logId Desc";
                PreparedStatement pstLogError = cn.prepareStatement(sqlLogError);
                pstLogError.setInt(1, logAccId);

                ResultSet tableLogError = pstLogError.executeQuery();

                if (tableLogError != null && tableLogError.next()) {
                    int lge_logId = tableLogError.getInt("logId");
                    int lge_accId = tableLogError.getInt("accId");
                    int lge_logDetailId = tableLogError.getInt("logDetailId");

                    rs = new LogError(lge_logId, new CustomerDAO().getCustomerById(lge_accId), new LogErrorDetailDAO().getLogErrorDetail(lge_logDetailId));
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
