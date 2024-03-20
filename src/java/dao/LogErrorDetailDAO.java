/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.LogErrorDetail;
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
public class LogErrorDetailDAO implements I_LogErrorDetailDAO {

    @Override
    public LogErrorDetail getLogErrorDetail(int logErrDeId) {
        LogErrorDetail rs = null;
        Connection cn = null;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqlLogErrorDetail = "Select [logDetailId], [logDetail] \n"
                        + "From LogErrorDetail\n"
                        + "Where [logDetailId] = ?";
                PreparedStatement pstLogErrorDetail = cn.prepareStatement(sqlLogErrorDetail);
                pstLogErrorDetail.setInt(1, logErrDeId);

                ResultSet tableLogErrorDetail = pstLogErrorDetail.executeQuery();

                if (tableLogErrorDetail != null && tableLogErrorDetail.next()) {
                    int lged_logDetailId = tableLogErrorDetail.getInt("logDetailId");
                    String lged_logDetail = tableLogErrorDetail.getString("logDetail");

                    rs = new LogErrorDetail(lged_logDetailId, lged_logDetail);
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
    public ArrayList<LogErrorDetail> getAllLogErrorDetai() {
        ArrayList<LogErrorDetail> allLogErrorDetai = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqlAllLogErDetail = "Select [logDetailId], [logDetail] \n"
                        + "From LogErrorDetail";
                Statement stAllLogErDetail = cn.createStatement();

                ResultSet tablestAllLogErDetail = stAllLogErDetail.executeQuery(sqlAllLogErDetail);
                if (tablestAllLogErDetail != null) {
                    while (tablestAllLogErDetail.next()) {
                        int lged_logDetailId = tablestAllLogErDetail.getInt("logDetailId");
                        String lged_logDetail = tablestAllLogErDetail.getString("logDetail");
                        allLogErrorDetai.add(new LogErrorDetail(lged_logDetailId, lged_logDetail));
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
        return allLogErrorDetai;
    }

}
