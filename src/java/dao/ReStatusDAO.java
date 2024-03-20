/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.ReStatus;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import utils.DBUtil;

/**
 *
 * @author nguye
 */
public class ReStatusDAO implements I_ReStatusDAO {

    @Override
    public ReStatus getReStatus(int statusNo) {
        Connection cn = null;
        ReStatus rs = null;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqlReStatus = "Select [statusNo], [statusName]\n"
                        + "From ReStatus\n"
                        + "Where [statusNo] = ?";
                PreparedStatement pstReStatus = cn.prepareStatement(sqlReStatus);
                pstReStatus.setInt(1, statusNo);

                ResultSet tableReStatus = pstReStatus.executeQuery();
                if (tableReStatus != null && tableReStatus.next()) {
                    int rs_requestTypeId = tableReStatus.getInt("statusNo");
                    String rs_typeName = tableReStatus.getString("statusName");

                    rs = new ReStatus(rs_requestTypeId, rs_typeName);
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
    public ArrayList<ReStatus> getAllReStatus() {
        ArrayList<ReStatus> getReStatus = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqlRequestByCusId = "Select [statusNo], [statusName] \n"
                        + "From ReStatus \n"
                        + "Where statusNo between 1 and 5";
                PreparedStatement pstgetReStatus = cn.prepareStatement(sqlRequestByCusId);
                ResultSet tablegetReStatus = pstgetReStatus.executeQuery();
                if (tablegetReStatus != null) {
                    while (tablegetReStatus.next()) {
                        int rs_requestTypeId = tablegetReStatus.getInt("statusNo");
                        String rs_typeName = tablegetReStatus.getString("statusName");

                        getReStatus.add(new ReStatus(rs_requestTypeId, rs_typeName));
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
        return getReStatus;
    }

    @Override
    public ArrayList<ReStatus> getAllReStatusForTechni() {
        ArrayList<ReStatus> getReStatus = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqlRequestByCusId = "Select [statusNo], [statusName] \n"
                        + "From ReStatus \n"
                        + "Where statusNo between 2 and 3";
                PreparedStatement pstgetReStatus = cn.prepareStatement(sqlRequestByCusId);
                ResultSet tablegetReStatus = pstgetReStatus.executeQuery();
                if (tablegetReStatus != null) {
                    while (tablegetReStatus.next()) {
                        int rs_requestTypeId = tablegetReStatus.getInt("statusNo");
                        String rs_typeName = tablegetReStatus.getString("statusName");

                        getReStatus.add(new ReStatus(rs_requestTypeId, rs_typeName));
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
        return getReStatus;
    }
}
