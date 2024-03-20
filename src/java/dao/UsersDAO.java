/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Users;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import utils.DBUtil;

/**
 *
 * @author nguye
 */
public class UsersDAO implements I_UsersDAO {

    @Override
    public Users getUsers(int userId) {
        Users rs = null;
        Connection cn = null;
        try {
            //1. Make connection voi sqlserver
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqlUsers = "Select [userId], [phoneNumber], [firstName], [lastName], [email], [indentityNo] \n"
                        + "From Users \n"
                        + "Where userId = ?";

                PreparedStatement pstUser = cn.prepareStatement(sqlUsers);
                pstUser.setInt(1, userId);
                ResultSet tableUsers = pstUser.executeQuery();

                if (tableUsers != null && tableUsers.next()) {
                    int u_userId = tableUsers.getInt("userId");
                    String u_telephoneNumber = tableUsers.getString("phoneNumber");
                    String u_fullName = tableUsers.getString("firstName") + " " + tableUsers.getString("lastName").trim();
                    String u_email = tableUsers.getString("email");
                    String u_indentityNo = tableUsers.getString("indentityNo");

                    rs = new Users(u_userId, u_telephoneNumber, u_fullName, u_email, u_indentityNo);
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
    public Users getUsersForViewTechni(int userId) {
        Users rs = null;
        Connection cn = null;
        try {
            //1. Make connection voi sqlserver
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqlUsers = "Select [userId], [firstName], [lastName], [phoneNumber] \n"
                        + "From Users \n"
                        + "where [userId] = ?";

                PreparedStatement pstUser = cn.prepareStatement(sqlUsers);
                pstUser.setInt(1, userId);
                ResultSet tableUsers = pstUser.executeQuery();

                if (tableUsers != null && tableUsers.next()) {
                    int u_userId = tableUsers.getInt("userId");
                    String u_telephoneNumber = tableUsers.getString("phoneNumber");
                    String u_fullName = tableUsers.getString("firstName") + " " + tableUsers.getString("lastName").trim();

                    rs = new Users(u_userId, u_telephoneNumber, u_fullName);
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
    public Users getUsersForViewContract(int userId) {
        Users rs = null;
        Connection cn = null;
        try {
            //1. Make connection voi sqlserver
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqlUsers = "Select [userId], [firstName], [lastName], [phoneNumber] \n"
                        + "From Users \n"
                        + "where [userId] = ?";

                PreparedStatement pstUser = cn.prepareStatement(sqlUsers);
                pstUser.setInt(1, userId);
                ResultSet tableUsers = pstUser.executeQuery();

                if (tableUsers != null && tableUsers.next()) {
                    int u_userId = tableUsers.getInt("userId");
                    String u_telephoneNumber = tableUsers.getString("phoneNumber");
                    String u_fullName = tableUsers.getString("firstName") + " " + tableUsers.getString("lastName").trim();

                    rs = new Users(u_userId, u_telephoneNumber, u_fullName);
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
    public int insertUsersRegister(String firstName, String lastName) {
        Connection cn = null;
        int userId = 0;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqlInsertUser = "Insert Into Users ([firstName], [lastName]) values (?, ?)";

                // Statement.RETURN_GENERATED_KEYS chỉ định rằng bất kỳ khóa nào được tạo tự động sẽ được trả về
                //Giống như sau khi tạo sql trên, mình muốn biết ID của cái mảnh ghép đó là bao nhiêu
                PreparedStatement pstInsertUser = cn.prepareStatement(sqlInsertUser, Statement.RETURN_GENERATED_KEYS);
                // Thiết lập giá trị cho các tham số trong câu lệnh SQL
                pstInsertUser.setString(1, firstName);
                pstInsertUser.setString(2, lastName);
                //Thiết lập giá trị cho số hàng bị ảnh hưởng
                int affectedRows = pstInsertUser.executeUpdate();

                //Kiểm tra xem có hàng nào bị ảnh hưởng không?
                if (affectedRows > 0) {
                    try (ResultSet rs = pstInsertUser.getGeneratedKeys()) {
                        if (rs.next()) {
                            userId = rs.getInt(1);
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
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
        return userId;
    }
}
