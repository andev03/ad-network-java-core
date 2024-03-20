/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.ContractService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import utils.DBUtil;

/**
 *
 * @author nguye
 */
public class ContractServiceDAO implements I_ContracServiceDAO {

    @Override
    public ContractService getContractService(int serviceId, int contractId) {
        return null;
    }

    @Override
    public int insertContractService(int serviceId, int contractId) {
 int rs = 0;
        Connection cn = null;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqlDevice = "INSERT INTO [ContractService] ([serviceId], [contractId]) VALUES\n"
                        + "(?,?)";
                PreparedStatement pstDevice = cn.prepareStatement(sqlDevice);
                pstDevice.setInt(1, serviceId);
                pstDevice.setInt(2, contractId);

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
    public ArrayList<ContractService> getContractServiceByContract(int contractId) {

        ArrayList<ContractService> rs = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqlContractServiceByContract = "Select [contractId], [serviceId]\n"
                        + "From [ContractService]\n"
                        + "Where [contractId] = ?";

                PreparedStatement pstContractServiceByContract = cn.prepareStatement(sqlContractServiceByContract);
                pstContractServiceByContract.setInt(1, contractId);

                ResultSet tableContractServiceByContract = pstContractServiceByContract.executeQuery();

                while (tableContractServiceByContract != null && tableContractServiceByContract.next()) {
                    int c_contractId = tableContractServiceByContract.getInt("contractId");
                    int c_serviceId = tableContractServiceByContract.getInt("serviceId");

                    rs.add(new ContractService(new ServiceDAO().getService(c_serviceId), new ContractDAO().getContract(c_contractId)));
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
