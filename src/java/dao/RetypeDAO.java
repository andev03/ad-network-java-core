/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Retype;
import dto.Roles;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import utils.DBUtil;

/**
 *
 * @author nguye
 */
public class RetypeDAO implements I_ReTypeDAO {

    @Override
    public Retype getReType(int requestTypeId) {
        Connection cn = null;
        Retype rs = null;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqlRetype = "Select [requestTypeId], [typeName]\n"
                        + "From ReType\n"
                        + "Where [requestTypeId] = ?";
                PreparedStatement pstRetype = cn.prepareStatement(sqlRetype);
                pstRetype.setInt(1, requestTypeId);

                ResultSet tableRetype = pstRetype.executeQuery();
                if (tableRetype != null && tableRetype.next()) {
                    int rt_requestTypeId = tableRetype.getInt("requestTypeId");
                    String rt_typeName = tableRetype.getString("typeName");

                    rs = new Retype(rt_requestTypeId, rt_typeName);
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
