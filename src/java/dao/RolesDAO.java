/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Roles;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import utils.DBUtil;

/**
 *
 * @author nguye
 */
public class RolesDAO implements I_RolesDAO {

    @Override
    public Roles getRoles(int roleId) {
        Connection cn = null;
        Roles rs = null;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqlRoles = "Select [roleId], [roleName] \n"
                        + "From Roles \n"
                        + "where roleId = ?";
                PreparedStatement pstRoles = cn.prepareStatement(sqlRoles);
                pstRoles.setInt(1, roleId);

                ResultSet tableRoles = pstRoles.executeQuery();
                if (tableRoles != null && tableRoles.next()) {
                    int r_roleId = tableRoles.getInt("roleId");
                    String r_roleName = tableRoles.getString("roleName");

                    rs = new Roles(r_roleId, r_roleName);
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
