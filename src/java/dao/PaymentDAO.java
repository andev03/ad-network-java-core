/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Payment;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import utils.DBUtil;

/**
 *
 * @author nguye
 */
public class PaymentDAO implements I_PaymentDAO {

    @Override
    public Payment getPayment(int paymentId) {
        Payment rs = null;
        Connection cn = null;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqlPayment = "Select [typeId], [typeName] \n"
                        + "From Payment\n"
                        + "Where typeId = ?";

                PreparedStatement pstPayment = cn.prepareStatement(sqlPayment);
                pstPayment.setInt(1, paymentId);
                ResultSet tablePayment = pstPayment.executeQuery();

                if (tablePayment != null && tablePayment.next()) {
                    int p_typeId = tablePayment.getInt("typeId");
                    String p_typeName = tablePayment.getString("typeName");
                    rs = new Payment(p_typeId, p_typeName);
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
