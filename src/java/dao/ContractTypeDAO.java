/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.CoStatus;
import dto.ContractType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import utils.DBUtil;

/**
 *
 * @author nguye
 */
public class ContractTypeDAO implements I_ContractTypeDAO {

    @Override
    public ContractType getContractType(int contractNo) {
        ContractType rs = null;
        Connection cn = null;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqlContractType = "Select [contractNo], [typeName] \n"
                        + "From [ContractType]\n"
                        + "Where[contractNo] = ?";

                PreparedStatement pstContractType = cn.prepareStatement(sqlContractType);
                pstContractType.setInt(1, contractNo);

                ResultSet tableContractType = pstContractType.executeQuery();

                if (tableContractType != null && tableContractType.next()) {
                    int cs_contractNo = tableContractType.getInt("contractNo");
                    int cs_typeName = tableContractType.getInt("typeName");

                    rs = new ContractType(cs_contractNo, cs_typeName);
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
