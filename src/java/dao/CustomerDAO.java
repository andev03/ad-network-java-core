package dao;

import dto.Customer;
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
public class CustomerDAO implements I_CustomerDAO {

    @Override
    public Customer getCustomer(String telephoneNumber, String password) {
        Customer rs = null;
        Connection cn = null;
        try {
            //1. Make connection voi sqlserver
            cn = DBUtil.getConnection();
            if (cn != null) {
                //2. viet sql va run
                String sqlCus = "Select [accId], [userId], [accTel], [statusNo], [accPassCus] From Customer \n"
                        + "Where accTel like ? And  [accPassCus] like ?";
                PreparedStatement pstUser = cn.prepareStatement(sqlCus);
                //gan input params vao 2 dau cham hoi
                pstUser.setString(1, telephoneNumber);
                pstUser.setString(2, password);

                ResultSet tableCus = pstUser.executeQuery();
                //3.lay data trong tableCus
                if (tableCus != null && tableCus.next()) {
                    int c_accId = tableCus.getInt("accId");
                    int c_userId = tableCus.getInt("userId");
                    String c_accTel = tableCus.getString("accTel");
                    int c_statusNo = tableCus.getInt("statusNo");
                    String c_accPassCus = tableCus.getString("accPassCus");

                    rs = new Customer(c_accId, new UsersDAO().getUsers(c_userId), c_accTel, new StatusDAO().getStatus(c_statusNo), c_accPassCus);
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
    public ArrayList<Customer> getScopeCustomerByTelephone(int beginScope, int endScope, String telephoneNumber) {
        ArrayList<Customer> cusByPhone = new ArrayList<>();
        Connection cn = null;

        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqlAllCusByPhone = "WITH SortedUsers AS (\n"
                        + "    SELECT [accId], [userId], [accTel], [statusNo], [accPassCus],\n"
                        + "           ROW_NUMBER() Over(Order By accId)  AS RowNumber\n"
                        + "    FROM Customer\n"
                        + "	WHERE [accTel] LIKE ?\n"
                        + ")\n"
                        + "SELECT [accId], [userId], [accTel], [statusNo], [accPassCus]\n"
                        + "FROM SortedUsers\n"
                        + "WHERE RowNumber BETWEEN ? AND ?";
                PreparedStatement pstCusByPhone = cn.prepareStatement(sqlAllCusByPhone);
                pstCusByPhone.setString(1, telephoneNumber + "%");
                pstCusByPhone.setInt(2, beginScope);
                pstCusByPhone.setInt(3, endScope);

                ResultSet tableCusByPhone = pstCusByPhone.executeQuery();

                if (tableCusByPhone != null) {
                    while (tableCusByPhone.next()) {
                        int c_accId = tableCusByPhone.getInt("accId");
                        int c_userId = tableCusByPhone.getInt("userId");
                        String c_accTel = tableCusByPhone.getString("accTel");
                        int c_statusNo = tableCusByPhone.getInt("statusNo");
                        String c_accPassCus = tableCusByPhone.getString("accPassCus");
                        cusByPhone.add(new Customer(c_accId, new UsersDAO().getUsers(c_userId), c_accTel, new StatusDAO().getStatus(c_statusNo), c_accPassCus));
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
        return cusByPhone;
    }

    @Override
    public ArrayList<Customer> getAllCustomer() {
        ArrayList<Customer> allCustomer = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqlAllCus = "Select [accId], [userId], [accTel], [statusNo], [accPassCus] \n"
                        + "From Customer";
                Statement stAllCus = cn.createStatement();

                ResultSet tableAllCus = stAllCus.executeQuery(sqlAllCus);
                if (tableAllCus != null) {
                    while (tableAllCus.next()) {
                        int c_accId = tableAllCus.getInt("accId");
                        int c_userId = tableAllCus.getInt("userId");
                        String c_accTel = tableAllCus.getString("accTel");
                        int c_statusNo = tableAllCus.getInt("statusNo");
                        String c_accPassCus = tableAllCus.getString("accPassCus");
                        allCustomer.add(new Customer(c_accId, new UsersDAO().getUsers(c_userId), c_accTel, new StatusDAO().getStatus(c_statusNo), c_accPassCus));
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
        return allCustomer;
    }

    @Override
    public int updateStatusCus(int cusId, int statusNo) {
        Connection cn = null;
        int rs = 0;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqlUpdateStatusCus = "Update Customer\n"
                        + "Set statusNo = ? \n"
                        + "where accId= ?";

                PreparedStatement pstUpdateStatusCus = cn.prepareStatement(sqlUpdateStatusCus);
                pstUpdateStatusCus.setInt(1, statusNo);
                pstUpdateStatusCus.setInt(2, cusId);
                rs = pstUpdateStatusCus.executeUpdate();
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
    public Customer getCustomerByTelephoneNumber(String telephoneNumber) {
        Connection cn = null;
        Customer cusByPhone = null;
        try {
            cn = DBUtil.getConnection();
            if (true) {
                cn = DBUtil.getConnection();
                if (cn != null) {
                    //2. viet sql va run
                    String sqlCusByPhone = "Select [accId], [userId], [accTel], [statusNo], [accPassCus] \n"
                            + "From Customer\n"
                            + "Where [accTel] like ?";
                    PreparedStatement pstCusByPhone = cn.prepareStatement(sqlCusByPhone);
                    pstCusByPhone.setString(1, telephoneNumber);

                    ResultSet tableCusByPhone = pstCusByPhone.executeQuery();

                    if (tableCusByPhone != null && tableCusByPhone.next()) {
                        int c_accId = tableCusByPhone.getInt("accId");
                        int c_userId = tableCusByPhone.getInt("userId");
                        String c_accTel = tableCusByPhone.getString("accTel");
                        int c_statusNo = tableCusByPhone.getInt("statusNo");
                        String c_accPassCus = tableCusByPhone.getString("accPassCus");
                        cusByPhone = new Customer(c_accId, new UsersDAO().getUsers(c_userId), c_accTel, new StatusDAO().getStatus(c_statusNo), c_accPassCus);
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
        return cusByPhone;
    }

    @Override
    public Customer getCustomerById(int accId) {
        Connection cn = null;
        Customer cusByAccId = null;
        try {
            cn = DBUtil.getConnection();
            if (true) {
                cn = DBUtil.getConnection();
                if (cn != null) {
                    //2. viet sql va run
                    String sqlCusByPhone = "Select [accId], [userId], [accTel], [statusNo], [accPassCus] \n"
                            + "From Customer\n"
                            + "Where [accId] = ?";
                    PreparedStatement pstCusByPhone = cn.prepareStatement(sqlCusByPhone);
                    pstCusByPhone.setInt(1, accId);

                    ResultSet tableCusByPhone = pstCusByPhone.executeQuery();

                    if (tableCusByPhone != null && tableCusByPhone.next()) {
                        int c_accId = tableCusByPhone.getInt("accId");
                        int c_userId = tableCusByPhone.getInt("userId");
                        String c_accTel = tableCusByPhone.getString("accTel");
                        int c_statusNo = tableCusByPhone.getInt("statusNo");
                        String c_accPassCus = tableCusByPhone.getString("accPassCus");
                        cusByAccId = new Customer(c_accId, new UsersDAO().getUsers(c_userId), c_accTel, new StatusDAO().getStatus(c_statusNo), c_accPassCus);
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
        return cusByAccId;
    }

    @Override
    public ArrayList<Customer> getCustomerByScope(int scopeBegin, int scopeEnd) {
        ArrayList<Customer> cusByAmout = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqlCusByAmout = "Select [accId], [userId], [accTel], [statusNo], [accPassCus] \n"
                        + "From Customer\n"
                        + "Where accId >= ? And accId <= ?";
                PreparedStatement pstCusByAmout = cn.prepareStatement(sqlCusByAmout);
                pstCusByAmout.setInt(1, scopeBegin);
                pstCusByAmout.setInt(2, scopeEnd);
                ResultSet tableCusByAmout = pstCusByAmout.executeQuery();
                if (tableCusByAmout != null) {
                    while (tableCusByAmout.next()) {
                        int c_accId = tableCusByAmout.getInt("accId");
                        int c_userId = tableCusByAmout.getInt("userId");
                        String c_accTel = tableCusByAmout.getString("accTel");
                        int c_statusNo = tableCusByAmout.getInt("statusNo");
                        String c_accPassCus = tableCusByAmout.getString("accPassCus");
                        cusByAmout.add(new Customer(c_accId, new UsersDAO().getUsers(c_userId), c_accTel, new StatusDAO().getStatus(c_statusNo), c_accPassCus));
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
        return cusByAmout;
    }

    @Override
    public int quantityCustomer() {
        Connection cn = null;
        int quantity = 0;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqlQuantityCustomer = "Select COUNT(accId) As [Quantity]\n"
                        + "From [Customer]";
                Statement stCustomerContract = cn.createStatement();
                ResultSet tableQuantityCustomer = stCustomerContract.executeQuery(sqlQuantityCustomer);
                if (tableQuantityCustomer != null && tableQuantityCustomer.next()) {
                    quantity = tableQuantityCustomer.getInt("Quantity");
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
        return quantity;
    }

    @Override
    public int quantityCustomerSearch() {
        Connection cn = null;
        int quantity = 0;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqlQuantityCustomer = "WITH SortedUsers AS (   SELECT [accId], [userId], [accTel], [statusNo], [accPassCus],\n"
                        + "ROW_NUMBER() Over(Order By accId)  AS RowNumber\n"
                        + "FROM Customer\n"
                        + "WHERE [accTel] LIKE '09' + '%')\n"
                        + "SELECT Count(accId) as Quantity\n"
                        + "FROM SortedUsers";
                Statement stCustomerContract = cn.createStatement();
                ResultSet tableQuantityCustomer = stCustomerContract.executeQuery(sqlQuantityCustomer);
                if (tableQuantityCustomer != null && tableQuantityCustomer.next()) {
                    quantity = tableQuantityCustomer.getInt("Quantity");
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
        return quantity;
    }

    @Override
    public int insertLogErrCus(int accId, int logDetailId) {
        Connection cn = null;
        int rs = 0;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqlUpdateStatusCus = "Insert Into LogError (accId, logDetailId)\n"
                        + "values (?, ?)";

                PreparedStatement pstUpdateStatusCus = cn.prepareStatement(sqlUpdateStatusCus);
                pstUpdateStatusCus.setInt(1, accId);
                pstUpdateStatusCus.setInt(2, logDetailId);
                rs = pstUpdateStatusCus.executeUpdate();
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
    public Customer getCustomerForViewTechni(int cusId) {
        Connection cn = null;
        Customer cusByAccId = null;
        try {
            cn = DBUtil.getConnection();
            if (true) {
                cn = DBUtil.getConnection();
                if (cn != null) {
                    //2. viet sql va run
                    String sqlCusByPhone = "Select [accId], [userId], [accTel], [statusNo], [accPassCus] \n"
                            + "From Customer\n"
                            + "Where [accId] = ?";
                    PreparedStatement pstCusByPhone = cn.prepareStatement(sqlCusByPhone);
                    pstCusByPhone.setInt(1, cusId);

                    ResultSet tableCusByPhone = pstCusByPhone.executeQuery();

                    if (tableCusByPhone != null && tableCusByPhone.next()) {
                        int c_accId = tableCusByPhone.getInt("accId");
                        int c_userId = tableCusByPhone.getInt("userId");
                        String c_accTel = tableCusByPhone.getString("accTel");
                        cusByAccId = new Customer(c_accId, new UsersDAO().getUsersForViewTechni(c_userId), c_accTel);
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
        return cusByAccId;
    }

    @Override
    public Customer getCustomerForViewContract(int cusId) {
        Connection cn = null;
        Customer cusByAccId = null;
        try {
            cn = DBUtil.getConnection();
            if (true) {
                cn = DBUtil.getConnection();
                if (cn != null) {
                    //2. viet sql va run
                    String sqlCusByPhone = "Select [accId], [userId], [accTel] \n"
                            + "From [Customer]　\n"
                            + "where accId = ?";
                    PreparedStatement pstCusByPhone = cn.prepareStatement(sqlCusByPhone);
                    pstCusByPhone.setInt(1, cusId);

                    ResultSet tableCusByPhone = pstCusByPhone.executeQuery();

                    if (tableCusByPhone != null && tableCusByPhone.next()) {
                        int c_accId = tableCusByPhone.getInt("accId");
                        int c_userId = tableCusByPhone.getInt("userId");
                        String c_accTel = tableCusByPhone.getString("accTel");
                        cusByAccId = new Customer(c_accId, new UsersDAO().getUsersForViewContract(c_userId), c_accTel);
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
        return cusByAccId;
    }

    @Override
    public ArrayList<Customer> sortCustomerByAction(int beginCus, int endCus, String action) {
        ArrayList<Customer> cusByPhone = new ArrayList<>();
        Connection cn = null;

        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqltTransByDate;
                if (action.equalsIgnoreCase("sortByName")) {
                    sqltTransByDate = "WITH SortedUsers AS (\n"
                            + "SELECT [accId], Customer.[userId], [accTel], [statusNo], [accPassCus],\n"
                            + "ROW_NUMBER() Over(Order By lastName, firstName asc) AS RowNumber\n"
                            + "FROM Customer JOIN Users ON Customer.[userId] = Users.[userId])\n"
                            + "SELECT [accId], [userId], [accTel], [statusNo], [accPassCus], RowNumber\n"
                            + "FROM SortedUsers\n"
                            + "WHERE RowNumber BETWEEN ? AND ?";
                } else {
                    sqltTransByDate = "WITH SortedUsers AS (\n"
                            + "SELECT [accId], [userId], [accTel], [statusNo], [accPassCus],\n"
                            + "ROW_NUMBER() Over(Order By [statusNo] desc) AS RowNumber\n"
                            + "FROM Customer)\n"
                            + "SELECT [accId], [userId], [accTel], [statusNo], [accPassCus], RowNumber\n"
                            + "FROM SortedUsers\n"
                            + "WHERE RowNumber BETWEEN ? AND ?";
                }

                PreparedStatement pstCusByPhone = cn.prepareStatement(sqltTransByDate);
                pstCusByPhone.setInt(1, beginCus);
                pstCusByPhone.setInt(2, endCus);

                ResultSet tableCusByPhone = pstCusByPhone.executeQuery();

                if (tableCusByPhone != null) {
                    while (tableCusByPhone.next()) {
                        int c_accId = tableCusByPhone.getInt("accId");
                        int c_userId = tableCusByPhone.getInt("userId");
                        String c_accTel = tableCusByPhone.getString("accTel");
                        int c_statusNo = tableCusByPhone.getInt("statusNo");
                        String c_accPassCus = tableCusByPhone.getString("accPassCus");
                        cusByPhone.add(new Customer(c_accId, new UsersDAO().getUsers(c_userId), c_accTel, new StatusDAO().getStatus(c_statusNo), c_accPassCus));
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
        return cusByPhone;
    }

    @Override
    public int insertCustomerRegister(int userId, String telephone, String password) {
        Connection cn = null;
        int rs = 0;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqlInsertCustomerRegister = "Insert Into Customer ([userId], [accTel], [statusNo], [accPassCus])\n"
                        + "values (?, ?, 0, ?)";

                PreparedStatement pstInsertCustomerRegister = cn.prepareStatement(sqlInsertCustomerRegister);
                pstInsertCustomerRegister.setInt(1, userId);
                pstInsertCustomerRegister.setString(2, telephone);
                pstInsertCustomerRegister.setString(3, password);
                rs = pstInsertCustomerRegister.executeUpdate();
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
    public boolean isTelephoneRegister(String telephone) {
        Connection cn = null;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqlCheckTelephone = "SELECT [accTel] FROM Customer WHERE accTel = ?";

                PreparedStatement pstCheckTelephone = cn.prepareStatement(sqlCheckTelephone);
                pstCheckTelephone.setString(1, telephone);

                ResultSet rs = pstCheckTelephone.executeQuery();

                // Nếu có bất kỳ hàng nào được trả về, số điện thoại đã được đăng ký
                if (rs.next()) {
                    return true;
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
        // Nếu không có hàng nào được trả về, số điện thoại chưa được đăng ký
        return false;
    }
}
