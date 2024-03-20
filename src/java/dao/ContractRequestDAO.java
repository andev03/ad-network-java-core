/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import utils.DBUtil;

/**
 *
 * @author nguye
 */
public class ContractRequestDAO implements I_ContractRequestDAO {

    @Override
    public int insertContractRequest(int contractId, int requestId) {
        int rs = 0;
        Connection cn = null;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqlDevice = "INSERT INTO [ContractRequest] ([contractId], [requestId]) VALUES\n"
                        + "(?,?)";
                PreparedStatement pstDevice = cn.prepareStatement(sqlDevice);
                pstDevice.setInt(1, contractId);
                pstDevice.setInt(2, requestId);

                // Thiết lập giá trị cho các cột khác nếu cần
                // Thực hiện câu lệnh INSERT
                rs = pstDevice.executeUpdate();
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
    public int getContractIdByRequestId(int requestId) {
        int rs = 0;
        Connection cn = null;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqlDevice = "Select contractId From ContractRequest where requestId = ?";
                PreparedStatement pstDevice = cn.prepareStatement(sqlDevice);
                pstDevice.setInt(1, requestId);
                ResultSet table = pstDevice.executeQuery();
                if (table != null && table.next()) {
                    rs = table.getInt("contractId");
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
