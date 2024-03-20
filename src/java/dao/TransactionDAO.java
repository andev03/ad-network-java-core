/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Transaction;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import utils.DBUtil;

/**
 *
 * @author nguye
 */
public class TransactionDAO implements I_TransactionDAO {

    @Override
    public ArrayList<Transaction> getAllTransactions() {
        ArrayList<Transaction> rs = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqlTransaction = "Select [transactionId], [cusId], [typeId], [statusNo], [transCycle], [transAmount], [transTime] \n"
                        + "from [Transaction]";
                Statement pstTransaction = cn.createStatement();

                ResultSet tableTransaction = pstTransaction.executeQuery(sqlTransaction);

                if (tableTransaction != null) {
                    while (tableTransaction.next()) {
                        int t_transactionId = tableTransaction.getInt("transactionId");
                        int t_cusId = tableTransaction.getInt("cusId");
                        int t_typeId = tableTransaction.getInt("typeId");
                        int t_statusNo = tableTransaction.getInt("statusNo");
                        int t_transCycle = tableTransaction.getInt("transCycle");
                        double t_transAmout = tableTransaction.getDouble("transAmount");
                        String t_transTime = tableTransaction.getTimestamp("transTime").toLocalDateTime().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));

                        rs.add(new Transaction(t_transactionId, new CustomerDAO().getCustomerForViewContract(t_cusId), new PaymentDAO().getPayment(t_typeId), new TraStatusDAO().getTraStatus(t_statusNo), t_transCycle, t_transAmout, t_transTime));
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
        return rs;
    }

    @Override
    public Transaction getTransactionById(int transactionId) {
        Transaction rs = null;
        Connection cn = null;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqlTransaction = "Select [transactionId], [cusId], [typeId], [statusNo], [transCycle], [transAmount], [transTime] \n"
                        + "From [Transaction]"
                        + "Where [transactionId] = ?";
                PreparedStatement pstTransaction = cn.prepareStatement(sqlTransaction);

                pstTransaction.setInt(1, transactionId);
                ResultSet tableTransaction = pstTransaction.executeQuery();

                if (tableTransaction != null && tableTransaction.next()) {
                    int t_transactionId = tableTransaction.getInt("transactionId");
                    int t_cusId = tableTransaction.getInt("cusId");
                    int t_typeId = tableTransaction.getInt("typeId");
                    int t_statusNo = tableTransaction.getInt("statusNo");
                    int t_transCycle = tableTransaction.getInt("transCycle");
                    double t_transAmout = tableTransaction.getDouble("transAmount");
                    String t_transTime = tableTransaction.getTimestamp("transTime").toLocalDateTime().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));

                    rs = new Transaction(t_transactionId, new CustomerDAO().getCustomerForViewContract(t_cusId), new PaymentDAO().getPayment(t_typeId), new TraStatusDAO().getTraStatus(t_statusNo), t_transCycle, t_transAmout, t_transTime);
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
    public ArrayList<Transaction> getTransactionByScope(int beginScope, int endScope) {
        ArrayList<Transaction> transByAmout = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqlTransByAmout = "Select [transactionId], [cusId], [typeId], [statusNo], [transCycle], [transAmount], [transTime] \n"
                        + "From [Transaction]\n"
                        + "Where [transactionId] >= ? And [transactionId] <= ?";
                PreparedStatement pstTransByAmout = cn.prepareStatement(sqlTransByAmout);
                pstTransByAmout.setInt(1, beginScope);
                pstTransByAmout.setInt(2, endScope);
                ResultSet tableTransByAmout = pstTransByAmout.executeQuery();
                if (tableTransByAmout != null) {
                    while (tableTransByAmout.next()) {
                        int t_transactionId = tableTransByAmout.getInt("transactionId");
                        int t_cusId = tableTransByAmout.getInt("cusId");
                        int t_typeId = tableTransByAmout.getInt("typeId");
                        int t_statusNo = tableTransByAmout.getInt("statusNo");
                        int t_transCycle = tableTransByAmout.getInt("transCycle");
                        double t_transAmout = tableTransByAmout.getDouble("transAmount");
                        String t_transTime = tableTransByAmout.getString("transTime");

                        transByAmout.add(new Transaction(t_transactionId, new CustomerDAO().getCustomerForViewContract(t_cusId), new PaymentDAO().getPayment(t_typeId), new TraStatusDAO().getTraStatus(t_statusNo), t_transCycle, t_transAmout, t_transTime));
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
        return transByAmout;
    }

    @Override
    public int quantityTracnsaction() {
        Connection cn = null;
        int quantity = 0;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqlQuantityTransaction = "Select COUNT(transactionId) As [Quantity]\n"
                        + "From [Transaction]";
                Statement stQuantityTransaction = cn.createStatement();
                ResultSet tableQuantityTransaction = stQuantityTransaction.executeQuery(sqlQuantityTransaction);
                if (tableQuantityTransaction != null && tableQuantityTransaction.next()) {
                    quantity = tableQuantityTransaction.getInt("Quantity");
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
    public ArrayList<Transaction> getTransactionByDate(int beginScope, int endScope, String date) {
        ArrayList<Transaction> transByDate = new ArrayList<>();
        Connection cn = null;

        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqltTransByDate = "WITH SortedUsers AS(\n"
                        + "SELECT [transactionId], [cusId], [typeId], [statusNo], [transCycle], [transAmount], [transTime],\n"
                        + "ROW_NUMBER() Over(Order By [transactionId] asc)  AS RowNumber\n"
                        + "FROM [Transaction] \n"
                        + "WHERE CONVERT(date, transTime) = ?\n"
                        + ")\n"
                        + "SELECT [transactionId], [typeId], [statusNo], [transCycle], [transAmount], [transTime]\n"
                        + "FROM SortedUsers\n"
                        + "WHERE RowNumber BETWEEN ? AND ?";
                PreparedStatement pstTransByDate = cn.prepareStatement(sqltTransByDate);
                pstTransByDate.setString(1, date);
                pstTransByDate.setInt(2, beginScope);
                pstTransByDate.setInt(3, endScope);

                ResultSet tableTransByDate = pstTransByDate.executeQuery();

                if (tableTransByDate != null) {
                    while (tableTransByDate.next()) {
                        int t_transactionId = tableTransByDate.getInt("transactionId");
                        int t_cusId = tableTransByDate.getInt("cusId");
                        int t_typeId = tableTransByDate.getInt("typeId");
                        int t_statusNo = tableTransByDate.getInt("statusNo");
                        int t_transCycle = tableTransByDate.getInt("transCycle");
                        double t_transAmout = tableTransByDate.getDouble("transAmount");
                        String t_transTime = tableTransByDate.getString("transTime");

                        transByDate.add(new Transaction(t_transactionId, new CustomerDAO().getCustomerForViewContract(t_cusId), new PaymentDAO().getPayment(t_typeId), new TraStatusDAO().getTraStatus(t_statusNo), t_transCycle, t_transAmout, t_transTime));

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
        return transByDate;
    }

    @Override
    public Transaction getTransactionByIdForViewContract(int transactionId) {
        Transaction rs = null;
        Connection cn = null;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqlTransaction = "Select [transactionId], [transAmount] \n"
                        + "From [Transaction] \n"
                        + "Where [transactionId] = ?";
                PreparedStatement pstTransaction = cn.prepareStatement(sqlTransaction);

                pstTransaction.setInt(1, transactionId);
                ResultSet tableTransaction = pstTransaction.executeQuery();

                if (tableTransaction != null && tableTransaction.next()) {
                    int t_transactionId = tableTransaction.getInt("transactionId");
                    double t_transAmout = tableTransaction.getDouble("transAmount");
                    rs = new Transaction(t_transactionId, t_transAmout);
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
    public int insertForBuy(int cusId, int typeId, int transCycle, double transAmount, int serviceId) {

        int rs = 0;
        Connection cn = null;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqlDevice = "INSERT INTO [Transaction] ([cusId], [typeId], [statusNo], [transCycle], [transAmount], [transTime]) VALUES\n"
                        + "(?,?,0,?,?,GETDATE())";
                PreparedStatement pstDevice = cn.prepareStatement(sqlDevice, PreparedStatement.RETURN_GENERATED_KEYS);
                pstDevice.setInt(1, cusId);
                pstDevice.setInt(2, typeId);
                pstDevice.setInt(3, transCycle);
                pstDevice.setDouble(4, transAmount);

                // Thiết lập giá trị cho các cột khác nếu cần
                // Thực hiện câu lệnh INSERT
                rs = pstDevice.executeUpdate();

                // Lấy IDENTITY value của bản ghi vừa chèn vào
                if (rs > 0) {
                    ResultSet generatedKeys = pstDevice.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        int transactionId = generatedKeys.getInt(1);
                        switch (transCycle) {
                            case 6:
                                transCycle = 2;
                                break;
                            case 12:
                                transCycle = 3;
                                break;
                            default:
                                transCycle = 1;
                                break;
                        }
                        new ContractDAO().insertContractForBuy(cusId, transactionId, transCycle, serviceId);
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
        return rs;
    }

    @Override
    public int insertForCart(int cusId, int typeId, int transCycle, double transAmount) {
        int rs = 0;
        Connection cn = null;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqlDevice = "INSERT INTO [Transaction] ([cusId], [typeId], [statusNo], [transCycle], [transAmount], [transTime]) VALUES\n"
                        + "(?,?,0,?,?,GETDATE())";
                PreparedStatement pstDevice = cn.prepareStatement(sqlDevice, PreparedStatement.RETURN_GENERATED_KEYS);
                pstDevice.setInt(1, cusId);
                pstDevice.setInt(2, typeId);
                pstDevice.setInt(3, transCycle);
                pstDevice.setDouble(4, transAmount);

                // Thiết lập giá trị cho các cột khác nếu cần
                // Thực hiện câu lệnh INSERT
                rs = pstDevice.executeUpdate();

                // Lấy IDENTITY value của bản ghi vừa chèn vào
                if (rs > 0) {
                    ResultSet generatedKeys = pstDevice.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        rs = generatedKeys.getInt(1);
                        switch (transCycle) {
                            case 6:
                                transCycle = 2;
                                break;
                            case 12:
                                transCycle = 3;
                                break;
                            default:
                                transCycle = 1;
                                break;
                        }
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
        return rs;
    }

    @Override
    public ArrayList<Transaction> sortByAction(int beginCus, int endCus, String action) {
        ArrayList<Transaction> transByDate = new ArrayList<>();
        Connection cn = null;

        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqltTransByDate;
                if (action.equalsIgnoreCase("sortByStatus")) {
                    sqltTransByDate = "WITH SortedUsers AS (\n"
                            + "SELECT [transactionId], [cusId], [typeId], [statusNo], [transCycle], [transAmount], [transTime],\n"
                            + "ROW_NUMBER() Over(Order By [statusNo] asc)  AS RowNumber\n"
                            + "FROM [Transaction])\n"
                            + "SELECT [transactionId], [cusId], [typeId], [statusNo], [transCycle], [transAmount], [transTime], RowNumber\n"
                            + "FROM SortedUsers Where RowNumber Between ? And ?";
                } else {
                    sqltTransByDate = "WITH SortedUsers AS (\n"
                            + "SELECT [transactionId], [cusId], [typeId], [statusNo], [transCycle], [transAmount], [transTime],\n"
                            + "ROW_NUMBER() Over(Order By [typeId] asc)  AS RowNumber\n"
                            + "FROM [Transaction])\n"
                            + "SELECT [transactionId], [cusId], [typeId], [statusNo], [transCycle], [transAmount], [transTime], RowNumber\n"
                            + "FROM SortedUsers Where RowNumber Between ? And ?";
                }

                PreparedStatement pstTransByDate = cn.prepareStatement(sqltTransByDate);
                pstTransByDate.setInt(1, beginCus);
                pstTransByDate.setInt(2, endCus);

                ResultSet tableTransByDate = pstTransByDate.executeQuery();

                if (tableTransByDate != null) {
                    while (tableTransByDate.next()) {
                        int t_transactionId = tableTransByDate.getInt("transactionId");
                        int t_cusId = tableTransByDate.getInt("cusId");
                        int t_typeId = tableTransByDate.getInt("typeId");
                        int t_statusNo = tableTransByDate.getInt("statusNo");
                        int t_transCycle = tableTransByDate.getInt("transCycle");
                        double t_transAmout = tableTransByDate.getDouble("transAmount");
                        String t_transTime = tableTransByDate.getString("transTime");

                        transByDate.add(new Transaction(t_transactionId, new CustomerDAO().getCustomerForViewContract(t_cusId), new PaymentDAO().getPayment(t_typeId), new TraStatusDAO().getTraStatus(t_statusNo), t_transCycle, t_transAmout, t_transTime));

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
        return transByDate;
    }

    @Override
    public ArrayList<Transaction> getScopePaymentByNameCustomer(int beginScope, int endScope, String nameCustomer) {
        ArrayList<Transaction> paymentByNameCus = new ArrayList<>();
        Connection cn = null;

        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqlAllPaymentByNameCus
                        = "WITH SortedUsers AS (\n"
                        + "    SELECT tra.transactionId, tra.cusId, tra.typeId, tra.statusNo, tra.transCycle, tra.transAmount, tra.transTime, \n"
                        + "           CONCAT(us.firstName, ' ', us.lastName)  AS FULLNAME, \n"
                        + "           ROW_NUMBER() Over(Order By tra.transactionId)  AS RowNumber \n"
                        + "    FROM [Transaction] tra \n"
                        + "    JOIN Customer cus ON tra.cusId = cus.accId \n"
                        + "    JOIN Users us ON cus.userId = us.userId \n"
                        + "    WHERE CONCAT(us.firstName, ' ', us.lastName) LIKE ?\n"
                        + ")\n"
                        + "SELECT transactionId, cusId, typeId, statusNo, transCycle, transAmount, transTime \n"
                        + "FROM SortedUsers \n"
                        + "WHERE RowNumber BETWEEN ? AND ?";
                PreparedStatement pstPaymentByNameCus = cn.prepareStatement(sqlAllPaymentByNameCus);

                pstPaymentByNameCus.setString(1, "%" + nameCustomer + "%");
                pstPaymentByNameCus.setInt(2, beginScope);
                pstPaymentByNameCus.setInt(3, endScope);

                ResultSet tablePaymentByNameCus = pstPaymentByNameCus.executeQuery();

                if (tablePaymentByNameCus != null) {
                    while (tablePaymentByNameCus.next()) {
                        int t_transactionId = tablePaymentByNameCus.getInt("transactionId");
                        int t_cusId = tablePaymentByNameCus.getInt("cusId");
                        int t_typeId = tablePaymentByNameCus.getInt("typeId");
                        int t_statusNo = tablePaymentByNameCus.getInt("statusNo");
                        int t_transCycle = tablePaymentByNameCus.getInt("transCycle");
                        double t_transAmout = tablePaymentByNameCus.getDouble("transAmount");
                        String t_transTime = tablePaymentByNameCus.getTimestamp("transTime").toLocalDateTime().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));

                        paymentByNameCus.add(new Transaction(t_transactionId, new CustomerDAO().getCustomerForViewContract(t_cusId), new PaymentDAO().getPayment(t_typeId), new TraStatusDAO().getTraStatus(t_statusNo), t_transCycle, t_transAmout, t_transTime));
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
        return paymentByNameCus;
    }

    @Override
    public int insertTransactionForExtend(int cusId, int typeId, int transCycle, double transAmount) {
        int rs = 0;
        Connection cn = null;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqlDevice = "INSERT INTO [Transaction] ([cusId], [typeId], [statusNo], [transCycle], [transAmount], [transTime]) VALUES\n"
                        + "(?,?,0,?,?,GETDATE())";
                PreparedStatement pstDevice = cn.prepareStatement(sqlDevice, PreparedStatement.RETURN_GENERATED_KEYS);
                pstDevice.setInt(1, cusId);
                pstDevice.setInt(2, typeId);
                pstDevice.setInt(3, transCycle);
                pstDevice.setDouble(4, transAmount);

                // Thiết lập giá trị cho các cột khác nếu cần
                // Thực hiện câu lệnh INSERT
                rs = pstDevice.executeUpdate();

                // Lấy IDENTITY value của bản ghi vừa chèn vào
                if (rs > 0) {
                    ResultSet generatedKeys = pstDevice.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        rs = generatedKeys.getInt(1);
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
        return rs;
    }

    @Override
    public int updateTransaction(int transactionId) {
        int rs = 0;
        Connection cn = null;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqlDevice = "Update [Transaction] Set statusNo = 1 Where transactionId = ?";
                PreparedStatement pstDevice = cn.prepareStatement(sqlDevice, PreparedStatement.RETURN_GENERATED_KEYS);
                pstDevice.setInt(1, transactionId);

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
}
