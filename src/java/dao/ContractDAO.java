/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Contract;
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
public class ContractDAO implements I_ContractDAO {

    @Override
    public Contract getContract(int contractId) {
        Contract rs = null;
        Connection cn = null;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqlContractType = "Select [contractId], [cusId], [empId], [transactionId], [statusNo], [contractNo], [startDate], [endDate]\n"
                        + "From [Contract]\n"
                        + "Where [contractId] = ?";

                PreparedStatement pstContract = cn.prepareStatement(sqlContractType);
                pstContract.setInt(1, contractId);

                ResultSet tableContract = pstContract.executeQuery();

                if (tableContract != null && tableContract.next()) {
                    int c_contractId = tableContract.getInt("contractId");
                    int c_cusId = tableContract.getInt("cusId");
                    int c_empId = tableContract.getInt("empId");
                    int c_transactionId = tableContract.getInt("transactionId");
                    int c_statusNo = tableContract.getInt("statusNo");
                    int c_contractNo = tableContract.getInt("contractNo");
                    String c_startDate = tableContract.getTimestamp("startDate").toLocalDateTime().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));;
                    String c_endDate = tableContract.getTimestamp("endDate").toLocalDateTime().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

                    rs = new Contract(c_contractId, new CustomerDAO().getCustomerById(c_cusId), new EmployeeDAO().getEmployeeByAccId(c_empId), new TransactionDAO().getTransactionById(c_transactionId), new CoStatusDAO().getCoStatus(c_statusNo), new ContractTypeDAO().getContractType(c_contractNo), c_startDate, c_endDate);

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
    public ArrayList<Contract> getAllContract() {
        ArrayList<Contract> allContract = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqlAllContract = "Select [contractId], [cusId], [empId], [transactionId], [statusNo], [contractNo], [startDate], [endDate] \n"
                        + "From [Contract]";
                Statement stAllContract = cn.createStatement();

                ResultSet tableAllContract = stAllContract.executeQuery(sqlAllContract);
                if (tableAllContract != null) {
                    while (tableAllContract.next()) {
                        int c_contractId = tableAllContract.getInt("contractId");
                        int c_cusId = tableAllContract.getInt("cusId");
                        int c_empId = tableAllContract.getInt("empId");
                        int c_transactionId = tableAllContract.getInt("transactionId");
                        int c_statusNo = tableAllContract.getInt("statusNo");
                        int c_contractNo = tableAllContract.getInt("contractNo");
                        String c_startDate = tableAllContract.getTimestamp("startDate").toLocalDateTime().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));;
                        String c_endDate = tableAllContract.getTimestamp("endDate").toLocalDateTime().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

                        allContract.add(new Contract(c_contractId, new CustomerDAO().getCustomerById(c_cusId), new EmployeeDAO().getEmployeeByAccId(c_empId),
                                new TransactionDAO().getTransactionById(c_transactionId), new CoStatusDAO().getCoStatus(c_statusNo),
                                new ContractTypeDAO().getContractType(c_contractNo), c_startDate, c_endDate));
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
        return allContract;
    }

    @Override
    public ArrayList<Contract> getContractByScope(int scopeBegin, int scopeEnd) {
        ArrayList<Contract> contractByAmout = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqlContractByAmout = "Select [contractId], [cusId], [empId], [transactionId], [statusNo], [contractNo], [startDate], [endDate] \n"
                        + "From [Contract]\n"
                        + "Where [contractId] >= ? And [contractId] <= ?";
                PreparedStatement pstContractCusByAmout = cn.prepareStatement(sqlContractByAmout);
                pstContractCusByAmout.setInt(1, scopeBegin);
                pstContractCusByAmout.setInt(2, scopeEnd);
                ResultSet tableContractByAmout = pstContractCusByAmout.executeQuery();
                if (tableContractByAmout != null) {
                    while (tableContractByAmout.next()) {
                        int c_contractId = tableContractByAmout.getInt("contractId");
                        int c_cusId = tableContractByAmout.getInt("cusId");
                        int c_empId = tableContractByAmout.getInt("empId");
                        int c_transactionId = tableContractByAmout.getInt("transactionId");
                        int c_statusNo = tableContractByAmout.getInt("statusNo");
                        int c_contractNo = tableContractByAmout.getInt("contractNo");
                        String c_startDate = tableContractByAmout.getTimestamp("startDate").toLocalDateTime().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));;
                        String c_endDate = tableContractByAmout.getTimestamp("endDate").toLocalDateTime().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                        contractByAmout.add(new Contract(c_contractId, new CustomerDAO().getCustomerById(c_cusId), new EmployeeDAO().getEmployeeByAccId(c_empId),
                                new TransactionDAO().getTransactionById(c_transactionId), new CoStatusDAO().getCoStatus(c_statusNo),
                                new ContractTypeDAO().getContractType(c_contractNo), c_startDate, c_endDate));

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
        return contractByAmout;
    }

    @Override
    public int quantityContract() {
        Connection cn = null;
        int quantity = 0;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqlQuantityContract = "Select COUNT(contractId) As [Quantity]\n"
                        + "From [Contract] \n"
                        + "Where empId Is Not null Or statusNo = 2";
                Statement stQuantityContract = cn.createStatement();
                ResultSet tableQuantityContract = stQuantityContract.executeQuery(sqlQuantityContract);
                if (tableQuantityContract != null && tableQuantityContract.next()) {
                    quantity = tableQuantityContract.getInt("Quantity");
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
    public ArrayList<Contract> getContractByScopeForViewContract(int scopeBegin, int scopeEnd) {
        ArrayList<Contract> contractByAmout = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqlContractByAmout = "WITH SortedUsers AS (\n"
                        + "Select [contractId], [cusId], [empId], [transactionId], [statusNo], [contractNo], [startDate], [endDate],\n"
                        + "ROW_NUMBER() Over(Order By [contractId])  AS RowNumber\n"
                        + "FROM [Contract] Where empId Is Not Null)\n"
                        + "SELECT [contractId], [cusId], [empId], [transactionId], [statusNo], [contractNo], [startDate], [endDate], [RowNumber]\n"
                        + "FROM SortedUsers Where RowNumber Between ? and ?";
                PreparedStatement pstContractCusByAmout = cn.prepareStatement(sqlContractByAmout);
                pstContractCusByAmout.setInt(1, scopeBegin);
                pstContractCusByAmout.setInt(2, scopeEnd);
                ResultSet tableContractByAmout = pstContractCusByAmout.executeQuery();
                if (tableContractByAmout != null) {
                    while (tableContractByAmout.next()) {
                        int c_contractId = tableContractByAmout.getInt("contractId");
                        int c_cusId = tableContractByAmout.getInt("cusId");
                        int c_empId = tableContractByAmout.getInt("empId");
                        String c_transactionId = tableContractByAmout.getString("transactionId");
                        int c_statusNo = tableContractByAmout.getInt("statusNo");
                        int c_contractNo = tableContractByAmout.getInt("contractNo");
                        String c_startDate = tableContractByAmout.getTimestamp("startDate").toLocalDateTime().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));;
                        String c_endDate = tableContractByAmout.getTimestamp("endDate").toLocalDateTime().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                        contractByAmout.add(new Contract(c_contractId, new CustomerDAO().getCustomerForViewContract(c_cusId), new EmployeeDAO().getEmployeeByAccIdForContract(c_empId),
                                new TransactionDAO().getTransactionByIdForViewContract(Integer.parseInt(c_transactionId)),
                                new CoStatusDAO().getCoStatus(c_statusNo),
                                new ContractTypeDAO().getContractType(c_contractNo), c_startDate, c_endDate));

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
        return contractByAmout;
    }

    @Override
    public int createContract(int empId, int cusId, int contracId) {
        int result = 0;
        Connection cn = null;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqlContract = "UPDATE [Contract]\n"
                        + "Set empId = ?, statusNo = 1\n"
                        + "Where cusId = ? And empId Is Null And contractId = ?";
                PreparedStatement pstContract = cn.prepareStatement(sqlContract);
                pstContract.setDouble(1, empId);
                pstContract.setInt(2, cusId);
                pstContract.setInt(3, contracId);
                int resultContract = pstContract.executeUpdate();
                if (resultContract == 1) {
                    int resultRequest = new RequestDAO().updateStatusRequestForCreateContract(cusId, contracId);
                    if (resultRequest == 0) {
                        sqlContract = "UPDATE [Contract]\n"
                                + "Set empId = null\n"
                                + "Where contracId = ?";
                        pstContract = cn.prepareStatement(sqlContract);
                        pstContract.setInt(1, contracId);
                        pstContract.executeUpdate();
                    } else {
                        result = 1;
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
        return result;
    }

    @Override
    public ArrayList<Contract> getContractForCreate() {
        ArrayList<Contract> allContract = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqlAllContract = "Select [contractId], [cusId] \n"
                        + "From [Contract] \n"
                        + "where empId Is Null";
                Statement stAllContract = cn.createStatement();

                ResultSet tableAllContract = stAllContract.executeQuery(sqlAllContract);
                if (tableAllContract != null) {
                    while (tableAllContract.next()) {
                        int c_contractId = tableAllContract.getInt("contractId");
                        int c_cusId = tableAllContract.getInt("cusId");

                        allContract.add(new Contract(c_contractId, new CustomerDAO().getCustomerForViewContract(c_cusId)));
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
        return allContract;
    }

    @Override
    public int insertContractForBuy(int cusId, int transactionId, int contractNo, int serviceId) {
        int rs = 0;
        Connection cn = null;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqlDevice = "INSERT INTO [Contract] ([cusId], [transactionId], [statusNo], [contractNo], [startDate], [endDate]) VALUES\n"
                        + "(?,?,4,?,GETDATE(),DATEADD(MONTH, ?, GETDATE()))";
                PreparedStatement pstDevice = cn.prepareStatement(sqlDevice, PreparedStatement.RETURN_GENERATED_KEYS);
                pstDevice.setInt(1, cusId);
                pstDevice.setInt(2, transactionId);
                pstDevice.setInt(3, contractNo);
                pstDevice.setDouble(4, contractNo);

                // Thiết lập giá trị cho các cột khác nếu cần
                // Thực hiện câu lệnh INSERT
                rs = pstDevice.executeUpdate();

                // Lấy IDENTITY value của bản ghi vừa chèn vào
                if (rs > 0) {
                    ResultSet generatedKeys = pstDevice.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        int contractId = generatedKeys.getInt(1);
                        new ContractServiceDAO().insertContractService(serviceId, contractId);
                        new RequestDAO().insertRequestForBuy(cusId, contractId);
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
    public int insertContractForCart(int cusId, int transactionId, int contractNo) {
        int rs = 0;
        Connection cn = null;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqlDevice = "INSERT INTO [Contract] ([cusId], [transactionId], [statusNo], [contractNo], [startDate], [endDate]) VALUES\n"
                        + "(?,?,4,?,GETDATE(),DATEADD(MONTH, ?, GETDATE()))";
                PreparedStatement pstDevice = cn.prepareStatement(sqlDevice, PreparedStatement.RETURN_GENERATED_KEYS);
                pstDevice.setInt(1, cusId);
                pstDevice.setInt(2, transactionId);
                pstDevice.setInt(3, contractNo);
                pstDevice.setDouble(4, contractNo);

                // Thiết lập giá trị cho các cột khác nếu cần
                // Thực hiện câu lệnh INSERT
                rs = pstDevice.executeUpdate();

                // Lấy IDENTITY value của bản ghi vừa chèn vào
                if (rs > 0) {
                    ResultSet generatedKeys = pstDevice.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        rs = generatedKeys.getInt(1);
                        new RequestDAO().insertRequestForBuy(cusId, rs);
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
    public ArrayList<Contract> getContractByCustomerId(int cusId) {
        ArrayList<Contract> rs = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqlContractByCusId = "Select [contractId], [cusId], [empId], [transactionId], [statusNo], [contractNo], [startDate], [endDate]\n"
                        + "From [Contract]\n"
                        + "Where [cusId] = ?";

                PreparedStatement pstContractByCusId = cn.prepareStatement(sqlContractByCusId);
                pstContractByCusId.setInt(1, cusId);

                ResultSet tableContractByCusId = pstContractByCusId.executeQuery();

                while (tableContractByCusId != null && tableContractByCusId.next()) {
                    int c_contractId = tableContractByCusId.getInt("contractId");
                    int c_cusId = tableContractByCusId.getInt("cusId");
                    int c_empId = tableContractByCusId.getInt("empId");
                    int c_transactionId = tableContractByCusId.getInt("transactionId");
                    int c_statusNo = tableContractByCusId.getInt("statusNo");
                    int c_contractNo = tableContractByCusId.getInt("contractNo");
                    String c_startDate = tableContractByCusId.getTimestamp("startDate").toLocalDateTime().toLocalDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                    String c_endDate = tableContractByCusId.getTimestamp("endDate").toLocalDateTime().toLocalDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

                    rs.add(new Contract(c_contractId, new CustomerDAO().getCustomerById(c_cusId), new EmployeeDAO().getEmployeeByAccId(c_empId), new TransactionDAO().getTransactionById(c_transactionId), new CoStatusDAO().getCoStatus(c_statusNo), new ContractTypeDAO().getContractType(c_contractNo), c_startDate, c_endDate));
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
    public ArrayList<Contract> getScopeContractCustomerByName(int beginScope, int endScope, String nameCustomer) {
        ArrayList<Contract> contractByNameCus = new ArrayList<>();
        Connection cn = null;

        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqlAllContractByNameCus
                        = "WITH SortedUsers AS (\n"
                        + "    SELECT co.contractId, co.cusId, co.empId, co.transactionId, co.statusNo, co.contractNo, co.startDate, co.endDate, \n"
                        + "           CONCAT(us.firstName, ' ', us.lastName)  AS FULLNAME, \n"
                        + "           ROW_NUMBER() Over(Order By co.contractId)  AS RowNumber \n"
                        + "    FROM Contract co \n"
                        + "    JOIN Customer cus ON co.cusId = cus.accId \n"
                        + "    JOIN Users us ON cus.userId = us.userId \n"
                        + "    WHERE CONCAT(us.firstName, ' ', us.lastName) LIKE ? And co.empId IS NOT NULL\n"
                        + ")\n"
                        + "SELECT contractId, cusId, empId, transactionId, statusNo, contractNo, startDate, endDate \n"
                        + "FROM SortedUsers \n"
                        + "WHERE RowNumber BETWEEN ? AND ?";
                PreparedStatement pstContractByNameCus = cn.prepareStatement(sqlAllContractByNameCus);

                pstContractByNameCus.setString(1, "%" + nameCustomer + "%");
                pstContractByNameCus.setInt(2, beginScope);
                pstContractByNameCus.setInt(3, endScope);

                ResultSet tableContractByNameCus = pstContractByNameCus.executeQuery();

                if (tableContractByNameCus != null) {
                    while (tableContractByNameCus.next()) {
                        int c_contractId = tableContractByNameCus.getInt("contractId");
                        int c_cusId = tableContractByNameCus.getInt("cusId");
                        int c_empId = tableContractByNameCus.getInt("empId");
                        int c_transactionId = tableContractByNameCus.getInt("transactionId");
                        int c_statusNo = tableContractByNameCus.getInt("statusNo");
                        int c_contractNo = tableContractByNameCus.getInt("contractNo");
                        String c_startDate = tableContractByNameCus.getTimestamp("startDate").toLocalDateTime().toLocalDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                        String c_endDate = tableContractByNameCus.getTimestamp("endDate").toLocalDateTime().toLocalDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

                        contractByNameCus.add(new Contract(c_contractId, new CustomerDAO().getCustomerById(c_cusId), new EmployeeDAO().getEmployeeByAccId(c_empId),
                                new TransactionDAO().getTransactionById(c_transactionId), new CoStatusDAO().getCoStatus(c_statusNo),
                                new ContractTypeDAO().getContractType(c_contractNo), c_startDate, c_endDate));
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
        return contractByNameCus;
    }

    @Override
    public int inserContractForExtend(int cusId, int transactionId, int contractNo, int numberContract) {
        int rs = 0;
        Connection cn = null;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqlDevice = "INSERT INTO [Contract] ([cusId], [transactionId], [statusNo], [contractNo], [startDate], [endDate]) VALUES\n"
                        + "(?,?,4,?,GETDATE(),DATEADD(MONTH, ?, GETDATE()))";
                PreparedStatement pstDevice = cn.prepareStatement(sqlDevice, PreparedStatement.RETURN_GENERATED_KEYS);
                pstDevice.setInt(1, cusId);
                pstDevice.setInt(2, transactionId);
                pstDevice.setInt(3, contractNo);
                pstDevice.setDouble(4, numberContract);

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
    public int updateStatusContractForExtend(int contractId) {
        int rs = 0;
        Connection cn = null;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqlDevice = "Update Contract Set statusNo = 2\n"
                        + "Where contractId = ?";
                PreparedStatement pstDevice = cn.prepareStatement(sqlDevice);
                pstDevice.setInt(1, contractId);

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
    public int updateInformationContractForUpdateContract(int empId, int contractId) {
        int rs = 0;
        Connection cn = null;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqlDevice = "Update [Contract] Set empId = ?, statusNo = 1\n"
                        + "OUTPUT inserted.contractId AS contractId\n" // Định danh cột OUTPUT
                        + "Where [contractId] = ?";
                PreparedStatement pstDevice = cn.prepareStatement(sqlDevice);
                pstDevice.setInt(1, empId);
                pstDevice.setInt(2, contractId);
                // Thiết lập giá trị cho các cột khác nếu cần
                ResultSet table = pstDevice.executeQuery();
                if (table != null && table.next()) { // Kiểm tra xem ResultSet có hàng dữ liệu nào không
                    rs = table.getInt("contractId"); // Lấy giá trị "requestId" từ ResultSet
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
    public int getTransactionIdByContractId(int contractId) {
        int rs = 0;
        Connection cn = null;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqlDevice = "Select transactionId From [Contract] Where contractId = ?";
                PreparedStatement pstDevice = cn.prepareStatement(sqlDevice);
                pstDevice.setInt(1, contractId);

                ResultSet table = pstDevice.executeQuery();
                if (table != null && table.next()) {
                    rs = table.getInt("transactionId");
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
    public int updateStatusContractAfterUpdate(int contractId) {
        int rs = 0;
        Connection cn = null;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqlDevice = "Update [Contract] Set statusNo = 3 Where contractId = ?";
                PreparedStatement pstDevice = cn.prepareStatement(sqlDevice);
                pstDevice.setInt(1, contractId);

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
