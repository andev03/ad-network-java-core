/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Request;
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
public class RequestDAO implements I_RequestDAO {

    @Override
    public Request getRequestById(int requestId) {
        Request rs = null;
        Connection cn = null;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqlRequest = "Select [requestId], [cusId], [empId], [contractId], [statusNo], [requestTypeId], [requestDate], [finishDate], [requestContent]\n"
                        + "From Request\n"
                        + "Where [requestId] = ?";
                PreparedStatement pstRequest = cn.prepareStatement(sqlRequest);

                pstRequest.setInt(1, requestId);
                ResultSet tableAllRequest = pstRequest.executeQuery();

                if (tableAllRequest != null && tableAllRequest.next()) {
                    int r_requestId = tableAllRequest.getInt("requestId");
                    int r_cusId = tableAllRequest.getInt("cusId");
                    int r_empId = tableAllRequest.getInt("empId");
                    int r_contractId = tableAllRequest.getInt("contractId");
                    int r_statusNo = tableAllRequest.getInt("statusNo");
                    int r_requestTypeId = tableAllRequest.getInt("requestTypeId");
                    String r_requestDate = tableAllRequest.getTimestamp("requestDate").toLocalDateTime().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
                    String r_finishDate = tableAllRequest.getTimestamp("finishDate").toLocalDateTime().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
                    String r_requestContent = tableAllRequest.getString("requestContent");

                    rs = new Request(r_requestId, new CustomerDAO().getCustomerById(r_cusId), new EmployeeDAO().getEmployeeByAccId(r_empId), new ContractDAO().getContract(r_contractId), new ReStatusDAO().getReStatus(r_statusNo), new RetypeDAO().getReType(r_requestTypeId), r_requestDate, r_finishDate, r_requestContent);
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
    public ArrayList<Request> getAllRequest() {
        ArrayList<Request> allRequest = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqlAllRequest = "Select [requestId], [cusId], [empId], [contractId], [statusNo], [requestTypeId], [requestDate], [finishDate], [requestContent]\n"
                        + "From [Request]";
                Statement stAllRequest = cn.createStatement();

                ResultSet tableAllRequest = stAllRequest.executeQuery(sqlAllRequest);
                if (tableAllRequest != null) {
                    while (tableAllRequest.next()) {
                        int r_requestId = tableAllRequest.getInt("requestId");
                        int r_cusId = tableAllRequest.getInt("cusId");
                        int r_empId = tableAllRequest.getInt("empId");
                        int r_contractId = tableAllRequest.getInt("contractId");
                        int r_statusNo = tableAllRequest.getInt("statusNo");
                        int r_requestTypeId = tableAllRequest.getInt("requestTypeId");
                        String r_requestDate = null;
                        String r_finishDate = null;
                        String r_requestContent = tableAllRequest.getString("requestContent");
                        try {
                            r_requestDate = tableAllRequest.getTimestamp("requestDate").toLocalDateTime().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
                            r_finishDate = tableAllRequest.getTimestamp("finishDate").toLocalDateTime().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
                            allRequest.add(new Request(r_requestId, new CustomerDAO().getCustomerById(r_cusId), new EmployeeDAO().getEmployeeByAccId(r_empId), new ContractDAO().getContract(r_contractId), new ReStatusDAO().getReStatus(r_statusNo), new RetypeDAO().getReType(r_requestTypeId), r_requestDate, r_finishDate, r_requestContent));
                        } catch (NullPointerException e) {
                            allRequest.add(new Request(r_requestId, new CustomerDAO().getCustomerById(r_cusId), new EmployeeDAO().getEmployeeByAccId(r_empId), new ContractDAO().getContract(r_contractId), new ReStatusDAO().getReStatus(r_statusNo), new RetypeDAO().getReType(r_requestTypeId), r_requestDate, r_finishDate, r_requestContent));
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
        return allRequest;
    }

    @Override
    public ArrayList<Request> getRequestByScope(int beginScope, int endScope) {
        ArrayList<Request> requestByAmout = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqlRequestByAmout = "Select [requestId], [cusId], [empId], [contractId], [statusNo], [requestTypeId], [requestDate], [finishDate], [requestContent]\n"
                        + "From [Request]\n"
                        + "Where [requestId] >= ? And [requestId] <= ?";
                PreparedStatement pstRequestByAmout = cn.prepareStatement(sqlRequestByAmout);
                pstRequestByAmout.setInt(1, beginScope);
                pstRequestByAmout.setInt(2, endScope);
                ResultSet tableAllRequest = pstRequestByAmout.executeQuery();
                if (tableAllRequest != null) {
                    while (tableAllRequest.next()) {
                        int r_requestId = tableAllRequest.getInt("requestId");
                        int r_cusId = tableAllRequest.getInt("cusId");
                        int r_empId = tableAllRequest.getInt("empId");
                        int r_contractId = tableAllRequest.getInt("contractId");
                        int r_statusNo = tableAllRequest.getInt("statusNo");
                        int r_requestTypeId = tableAllRequest.getInt("requestTypeId");
                        String r_requestDate = null;
                        String r_finishDate = null;
                        String r_requestContent = tableAllRequest.getString("requestContent");
                        try {
                            r_requestDate = tableAllRequest.getTimestamp("requestDate").toLocalDateTime().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
                            r_finishDate = tableAllRequest.getTimestamp("finishDate").toLocalDateTime().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
                            requestByAmout.add(new Request(r_requestId, new CustomerDAO().getCustomerById(r_cusId), new EmployeeDAO().getEmployeeByAccId(r_empId), new ContractDAO().getContract(r_contractId), new ReStatusDAO().getReStatus(r_statusNo), new RetypeDAO().getReType(r_requestTypeId), r_requestDate, r_finishDate, r_requestContent));
                        } catch (NullPointerException e) {
                            requestByAmout.add(new Request(r_requestId, new CustomerDAO().getCustomerById(r_cusId), new EmployeeDAO().getEmployeeByAccId(r_empId), new ContractDAO().getContract(r_contractId), new ReStatusDAO().getReStatus(r_statusNo), new RetypeDAO().getReType(r_requestTypeId), r_requestDate, r_finishDate, r_requestContent));
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
        return requestByAmout;
    }

    @Override
    public int quantityRequest() {
        Connection cn = null;
        int quantity = 0;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqlQuantityRequest = "Select COUNT(requestId) As [Quantity]\n"
                        + "From [Request]";
                Statement stQuantityRequest = cn.createStatement();
                ResultSet tableQuantityRequest = stQuantityRequest.executeQuery(sqlQuantityRequest);
                if (tableQuantityRequest != null && tableQuantityRequest.next()) {
                    quantity = tableQuantityRequest.getInt("Quantity");
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
    public ArrayList<Request> getRequestByCusId(int cusId, int beginCus, int endCus) {
        ArrayList<Request> requestByCusId = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqlRequestByCusId = "WITH SortedUsers AS (\n"
                        + "SELECT [requestId], [cusId], [empId], [contractId], [statusNo], [requestTypeId], [requestDate], [finishDate], [requestContent],\n"
                        + "ROW_NUMBER() Over(Order By [cusId] desc)  AS RowNumber\n"
                        + "FROM Request\n"
                        + "Where empId = ? and statusNo between 1 and 2)\n"
                        + "SELECT [requestId], [cusId], [empId], [contractId], [statusNo], [requestTypeId], [requestDate], [finishDate], [requestContent]\n"
                        + "FROM SortedUsers\n"
                        + "WHERE RowNumber BETWEEN ? AND ?	";
                PreparedStatement pstRequestByCusId = cn.prepareStatement(sqlRequestByCusId);
                pstRequestByCusId.setInt(1, cusId);
                pstRequestByCusId.setInt(2, beginCus);
                pstRequestByCusId.setInt(3, endCus);
                ResultSet tableRequestByCusId = pstRequestByCusId.executeQuery();
                if (tableRequestByCusId != null) {
                    while (tableRequestByCusId.next()) {
                        int r_requestId = tableRequestByCusId.getInt("requestId");
                        int r_cusId = tableRequestByCusId.getInt("cusId");
                        int r_empId = tableRequestByCusId.getInt("empId");
                        int r_contractId = tableRequestByCusId.getInt("contractId");
                        int r_statusNo = tableRequestByCusId.getInt("statusNo");
                        int r_requestTypeId = tableRequestByCusId.getInt("requestTypeId");
                        String r_requestDate = null;
                        String r_finishDate = null;
                        String r_requestContent = tableRequestByCusId.getString("requestContent");
                        try {
                            r_requestDate = tableRequestByCusId.getTimestamp("requestDate").toLocalDateTime().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
                            r_finishDate = tableRequestByCusId.getTimestamp("finishDate").toLocalDateTime().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
                            requestByCusId.add(new Request(r_requestId, new CustomerDAO().getCustomerById(r_cusId), new EmployeeDAO().getEmployeeByAccId(r_empId), new ContractDAO().getContract(r_contractId), new ReStatusDAO().getReStatus(r_statusNo), new RetypeDAO().getReType(r_requestTypeId), r_requestDate, r_finishDate, r_requestContent));
                        } catch (NullPointerException e) {
                            requestByCusId.add(new Request(r_requestId, new CustomerDAO().getCustomerById(r_cusId), new EmployeeDAO().getEmployeeByAccId(r_empId), new ContractDAO().getContract(r_contractId), new ReStatusDAO().getReStatus(r_statusNo), new RetypeDAO().getReType(r_requestTypeId), r_requestDate, r_finishDate, r_requestContent));
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
        return requestByCusId;
    }

    @Override
    public ArrayList<Request> getRequestForView(int cusId) {
        ArrayList<Request> allRequest = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqlAllRequest = "WITH SortedUsers AS (\n"
                        + "SELECT [requestId], [cusId], [empId], [contractId], [statusNo], [requestTypeId], [requestDate], [finishDate], [requestContent],\n"
                        + "ROW_NUMBER() Over(Order By [cusId] desc)  AS RowNumber\n"
                        + "FROM Request\n"
                        + "Where empId = ? and statusNo between 1 and 3)\n"
                        + "SELECT [requestId], [cusId], [empId], [contractId], [statusNo], [requestTypeId], [requestDate], [finishDate], [requestContent]\n"
                        + "FROM SortedUsers order By requestDate desc";
                PreparedStatement pstRequestByCusId = cn.prepareStatement(sqlAllRequest);
                pstRequestByCusId.setInt(1, cusId);
                ResultSet tableRequestByCusId = pstRequestByCusId.executeQuery();
                if (tableRequestByCusId != null) {
                    while (tableRequestByCusId.next()) {
                        int r_requestId = tableRequestByCusId.getInt("requestId");
                        int r_cusId = tableRequestByCusId.getInt("cusId");
                        int r_statusNo = tableRequestByCusId.getInt("statusNo");
                        int r_requestTypeId = tableRequestByCusId.getInt("requestTypeId");
                        String r_requestDate = tableRequestByCusId.getTimestamp("requestDate").toLocalDateTime().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));;
                        String r_requestContent = tableRequestByCusId.getString("requestContent");
                        allRequest.add(new Request(r_requestId, new CustomerDAO().getCustomerForViewTechni(r_cusId), new ReStatusDAO().getReStatus(r_statusNo), new RetypeDAO().getReType(r_requestTypeId), r_requestDate, r_requestContent));
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
        return allRequest;
    }

    @Override
    public int updateStatusForTechni(int status, int empId, int requestId) {
        Connection cn = null;
        int rs = 0;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqlUpdateStatusRequest = "Update Request\n"
                        + "Set statusNo = ?, finishDate = GETDATE()\n"
                        + "where empId = ? And requestId = ?";

                PreparedStatement pstUpdateStatusRequest = cn.prepareStatement(sqlUpdateStatusRequest);
                pstUpdateStatusRequest.setInt(1, status);
                pstUpdateStatusRequest.setInt(2, empId);
                pstUpdateStatusRequest.setInt(3, requestId);
                rs = pstUpdateStatusRequest.executeUpdate();
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
    public ArrayList<Request> requestTechniByDate(String date, int empId) {
        ArrayList<Request> allRequest = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqlAllRequest = "SELECT [requestId], [cusId], [empId], [contractId], [statusNo], [requestTypeId], [requestDate], [finishDate], [requestContent]\n"
                        + "FROM [Request] \n"
                        + "WHERE CONVERT(date, [requestDate]) = ? and empId = ? Order By requestDate desc";
                PreparedStatement pstRequestByCusId = cn.prepareStatement(sqlAllRequest);
                pstRequestByCusId.setString(1, date);
                pstRequestByCusId.setInt(2, empId);
                ResultSet tableRequestByCusId = pstRequestByCusId.executeQuery();
                if (tableRequestByCusId != null) {
                    while (tableRequestByCusId.next()) {
                        int r_requestId = tableRequestByCusId.getInt("requestId");
                        int r_cusId = tableRequestByCusId.getInt("cusId");
                        int r_statusNo = tableRequestByCusId.getInt("statusNo");
                        int r_requestTypeId = tableRequestByCusId.getInt("requestTypeId");
                        String r_requestDate = tableRequestByCusId.getTimestamp("requestDate").toLocalDateTime().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));;
                        String r_requestContent = tableRequestByCusId.getString("requestContent");
                        allRequest.add(new Request(r_requestId, new CustomerDAO().getCustomerForViewTechni(r_cusId), new ReStatusDAO().getReStatus(r_statusNo), new RetypeDAO().getReType(r_requestTypeId), r_requestDate, r_requestContent));
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
        return allRequest;
    }

    @Override
    public ArrayList<Request> requestShowByChoice(int statusNo, int empId) {
        ArrayList<Request> allRequest = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqlAllRequest = "  SELECT [requestId], [cusId], [empId], [contractId], [statusNo], [requestTypeId], [requestDate], [finishDate], [requestContent]\n"
                        + "FROM [Request] \n"
                        + "WHERE statusNo = ? and empId = ? Order By requestDate desc";
                PreparedStatement pstRequestByCusId = cn.prepareStatement(sqlAllRequest);
                pstRequestByCusId.setInt(1, statusNo);
                pstRequestByCusId.setInt(2, empId);
                ResultSet tableRequestByCusId = pstRequestByCusId.executeQuery();
                if (tableRequestByCusId != null) {
                    while (tableRequestByCusId.next()) {
                        int r_requestId = tableRequestByCusId.getInt("requestId");
                        int r_cusId = tableRequestByCusId.getInt("cusId");
                        int r_statusNo = tableRequestByCusId.getInt("statusNo");
                        int r_requestTypeId = tableRequestByCusId.getInt("requestTypeId");
                        String r_requestDate = tableRequestByCusId.getTimestamp("requestDate").toLocalDateTime().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));;
                        String r_requestContent = tableRequestByCusId.getString("requestContent");
                        allRequest.add(new Request(r_requestId, new CustomerDAO().getCustomerForViewTechni(r_cusId), new ReStatusDAO().getReStatus(r_statusNo), new RetypeDAO().getReType(r_requestTypeId), r_requestDate, r_requestContent));
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
        return allRequest;
    }

    @Override
    public ArrayList<Request> getRequestByStatus(int reStatus, int reType) {
        ArrayList<Request> requestByStatus = new ArrayList<>();
        Connection cn = null;
        try {
            //1. Make connection voi sqlserver
            cn = DBUtil.getConnection();
            if (cn != null) {
                //2. viet sql va run
                String sqlRequestByStatus = "Select [requestId], [cusId], [empId], [contractId], [statusNo], [requestTypeId], [requestDate], [finishDate], [requestContent]\n"
                        + "From [Request]\n"
                        + "Where [statusNo] like ? And  [requestTypeId] like ?";
                PreparedStatement pstByStatus = cn.prepareStatement(sqlRequestByStatus);
                //gan input params vao 2 dau cham hoi
                pstByStatus.setInt(1, reStatus);
                pstByStatus.setInt(2, reType);

                ResultSet tableRequestByStatus = pstByStatus.executeQuery();
                //3.lay data trong tableCus
                if (tableRequestByStatus != null) {
                    while (tableRequestByStatus.next()) {
                        int r_requestId = tableRequestByStatus.getInt("requestId");
                        int r_cusId = tableRequestByStatus.getInt("cusId");
                        int r_empId = tableRequestByStatus.getInt("empId");
                        int r_contractId = tableRequestByStatus.getInt("contractId");
                        int r_statusNo = tableRequestByStatus.getInt("statusNo");
                        int r_requestTypeId = tableRequestByStatus.getInt("requestTypeId");
                        String r_requestDate = null;
                        String r_finishDate = null;
                        String r_requestContent = tableRequestByStatus.getString("requestContent");
                        try {
                            r_requestDate = tableRequestByStatus.getTimestamp("requestDate").toLocalDateTime().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
                            r_finishDate = tableRequestByStatus.getTimestamp("finishDate").toLocalDateTime().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
                            requestByStatus.add(new Request(r_requestId, new CustomerDAO().getCustomerById(r_cusId), new EmployeeDAO().getEmployeeByAccId(r_empId), new ContractDAO().getContract(r_contractId), new ReStatusDAO().getReStatus(r_statusNo), new RetypeDAO().getReType(r_requestTypeId), r_requestDate, r_finishDate, r_requestContent));
                        } catch (NullPointerException e) {
                            requestByStatus.add(new Request(r_requestId, new CustomerDAO().getCustomerById(r_cusId), new EmployeeDAO().getEmployeeByAccId(r_empId), new ContractDAO().getContract(r_contractId), new ReStatusDAO().getReStatus(r_statusNo), new RetypeDAO().getReType(r_requestTypeId), r_requestDate, r_finishDate, r_requestContent));
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
        return requestByStatus;
    }

    @Override
    public int updateStatusRequestForCreateContract(int cusId, int contractId) {
        int rs = 0;
        Connection cn = null;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqlContract = "Update [Request]\n"
                        + "Set statusNo = 1\n"
                        + "Where cusId = ? And statusNo = 0 And requestTypeId = 2 and contractId = ?";
                PreparedStatement pstContract = cn.prepareStatement(sqlContract);
                pstContract.setDouble(1, cusId);
                pstContract.setInt(2, contractId);

                rs = pstContract.executeUpdate();
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
    public ArrayList<Request> requestForManageRequest(int empId) {
        ArrayList<Request> allRequest = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqlAllRequest = "WITH SortedUsers AS (\n"
                        + "SELECT [requestId], [cusId], [empId], [contractId], [statusNo], [requestTypeId], [requestDate], [finishDate], [requestContent],\n"
                        + "ROW_NUMBER() Over(Order By [cusId] desc)  AS RowNumber\n"
                        + "FROM Request\n"
                        + "Where empId = ? and statusNo between 1 and 3)\n"
                        + "SELECT [requestId], [cusId], [empId], [contractId], [statusNo], [requestTypeId], [requestDate], [finishDate], [requestContent]\n"
                        + "FROM SortedUsers";
                PreparedStatement pstRequestByCusId = cn.prepareStatement(sqlAllRequest);
                ResultSet tableRequestByCusId = pstRequestByCusId.executeQuery();
                if (tableRequestByCusId != null) {
                    while (tableRequestByCusId.next()) {
                        int r_requestId = tableRequestByCusId.getInt("requestId");
                        int r_cusId = tableRequestByCusId.getInt("cusId");
                        int r_statusNo = tableRequestByCusId.getInt("statusNo");
                        int r_requestTypeId = tableRequestByCusId.getInt("requestTypeId");
                        String r_requestDate = tableRequestByCusId.getTimestamp("requestDate").toLocalDateTime().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));;
                        String r_requestContent = tableRequestByCusId.getString("requestContent");
                        allRequest.add(new Request(r_requestId, new CustomerDAO().getCustomerForViewTechni(r_cusId), new ReStatusDAO().getReStatus(r_statusNo), new RetypeDAO().getReType(r_requestTypeId), r_requestDate, r_requestContent));
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
        return allRequest;
    }

    @Override
    public int insertRequestForBuy(int cusId, int contractId) {
        int rs = 0;
        Connection cn = null;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqlDevice = "INSERT INTO [Request] ([cusId], [contractId], [statusNo], [requestTypeId], [requestDate]) VALUES\n"
                        + "(?, ?, 0, 2, GETDATE())";
                PreparedStatement pstDevice = cn.prepareStatement(sqlDevice, PreparedStatement.RETURN_GENERATED_KEYS);
                pstDevice.setInt(1, cusId);
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
    public ArrayList<Request> requestForManageRequestOfAdmin(int beginCus, int endCus) {
        ArrayList<Request> allRequest = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqlAllRequest = "WITH SortedUsers AS (\n"
                        + "SELECT [requestId], [cusId], [empId], [contractId], [statusNo], [requestTypeId], [requestDate], [finishDate], [requestContent],\n"
                        + "ROW_NUMBER() Over(Order By [requestId])  AS RowNumber\n"
                        + "FROM Request)\n"
                        + "SELECT [requestId], [cusId], [empId], [contractId], [statusNo], [requestTypeId], [requestDate], [finishDate], [requestContent], RowNumber\n"
                        + "FROM SortedUsers Where RowNumber Between ? And ?";
                PreparedStatement pstRequestByCusId = cn.prepareStatement(sqlAllRequest);
                pstRequestByCusId.setInt(1, beginCus);
                pstRequestByCusId.setInt(2, endCus);
                ResultSet tableRequestByCusId = pstRequestByCusId.executeQuery();
                if (tableRequestByCusId != null) {
                    while (tableRequestByCusId.next()) {
                        int r_requestId = tableRequestByCusId.getInt("requestId");
                        int r_cusId = tableRequestByCusId.getInt("cusId");
                        int r_statusNo = tableRequestByCusId.getInt("statusNo");
                        int r_requestTypeId = tableRequestByCusId.getInt("requestTypeId");
                        String r_requestDate = tableRequestByCusId.getTimestamp("requestDate").toLocalDateTime().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));;
                        String r_requestContent = tableRequestByCusId.getString("requestContent");
                        allRequest.add(new Request(r_requestId, new CustomerDAO().getCustomerForViewTechni(r_cusId), new ReStatusDAO().getReStatus(r_statusNo), new RetypeDAO().getReType(r_requestTypeId), r_requestDate, r_requestContent));
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
        return allRequest;
    }

    @Override
    public ArrayList<Request> getScopeRequestByNameCustomer(int beginScope, int endScope, String nameCustomer) {
        ArrayList<Request> requestByNameCus = new ArrayList<>();
        Connection cn = null;

        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqlAllRequestByNameCus
                        = "WITH SortedUsers AS (\n"
                        + "    SELECT re.requestId, re.cusId, re.empId, re.contractId, re.statusNo, re.requestTypeId, re.requestDate, re.finishDate, re.requestContent, \n"
                        + "           CONCAT(us.firstName, ' ', us.lastName)  AS FULLNAME, \n"
                        + "           ROW_NUMBER() Over(Order By re.requestId)  AS RowNumber \n"
                        + "    FROM [Request] re \n"
                        + "    JOIN Customer cus ON re.cusId = cus.accId \n"
                        + "    JOIN Users us ON cus.userId = us.userId \n"
                        + "    WHERE CONCAT(us.firstName, ' ', us.lastName) LIKE ?\n"
                        + ")\n"
                        + "SELECT requestId, cusId, empId, contractId, statusNo, requestTypeId, requestDate, finishDate, requestContent \n"
                        + "FROM SortedUsers \n"
                        + "WHERE RowNumber BETWEEN ? AND ?";
                PreparedStatement pstRequestByNameCus = cn.prepareStatement(sqlAllRequestByNameCus);

                pstRequestByNameCus.setString(1, "%" + nameCustomer + "%");
                pstRequestByNameCus.setInt(2, beginScope);
                pstRequestByNameCus.setInt(3, endScope);

                ResultSet tableRequestByNameCus = pstRequestByNameCus.executeQuery();

                if (tableRequestByNameCus != null) {
                    while (tableRequestByNameCus.next()) {
                        int r_requestId = tableRequestByNameCus.getInt("requestId");
                        int r_cusId = tableRequestByNameCus.getInt("cusId");
                        int r_empId = tableRequestByNameCus.getInt("empId");
                        int r_contractId = tableRequestByNameCus.getInt("contractId");
                        int r_statusNo = tableRequestByNameCus.getInt("statusNo");
                        int r_requestTypeId = tableRequestByNameCus.getInt("requestTypeId");
                        String r_requestDate = tableRequestByNameCus.getTimestamp("requestDate").toLocalDateTime().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
                        String r_finishDate = tableRequestByNameCus.getTimestamp("finishDate").toLocalDateTime().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
                        String r_requestContent = tableRequestByNameCus.getString("requestContent");

                        requestByNameCus.add(new Request(r_requestId, new CustomerDAO().getCustomerById(r_cusId), new EmployeeDAO().getEmployeeByAccId(r_empId), new ContractDAO().getContract(r_contractId), new ReStatusDAO().getReStatus(r_statusNo), new RetypeDAO().getReType(r_requestTypeId), r_requestDate, r_finishDate, r_requestContent));
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
        return requestByNameCus;
    }

    @Override
    public ArrayList<Request> getAllRequestByCusId(int cusId) {
        ArrayList<Request> allRequestByCusId = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqlRequestByCusId = "Select [requestId], [cusId], [empId], [contractId], [statusNo], [requestTypeId], [requestDate], [requestContent]\n"
                        + "From [Request]\n"
                        + "Where [cusId] = ? order by requestDate desc";
                PreparedStatement pstRequestByCusId = cn.prepareStatement(sqlRequestByCusId);
                pstRequestByCusId.setInt(1, cusId);
                ResultSet tableRequestByCusId = pstRequestByCusId.executeQuery();
                if (tableRequestByCusId != null) {
                    while (tableRequestByCusId.next()) {
                        int r_requestId = tableRequestByCusId.getInt("requestId");
                        int r_cusId = tableRequestByCusId.getInt("cusId");
                        int r_empId = tableRequestByCusId.getInt("empId");
                        int r_contractId = tableRequestByCusId.getInt("contractId");
                        int r_statusNo = tableRequestByCusId.getInt("statusNo");
                        int r_requestTypeId = tableRequestByCusId.getInt("requestTypeId");
                        String r_requestDate = tableRequestByCusId.getTimestamp("requestDate").toLocalDateTime().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
                        String r_requestContent = tableRequestByCusId.getString("requestContent");

                        allRequestByCusId.add(new Request(r_requestId, new CustomerDAO().getCustomerById(r_cusId), new EmployeeDAO().getEmployeeByAccId(r_empId), new ContractDAO().getContract(r_contractId), new ReStatusDAO().getReStatus(r_statusNo), new RetypeDAO().getReType(r_requestTypeId), r_requestDate, r_requestContent));
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
        return allRequestByCusId;
    }

    @Override
    public int insertRequestForExtend(int cusId, int contractId) {
        int rs = 0;
        Connection cn = null;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqlDevice = "INSERT INTO [Request] ([cusId], [contractId], [statusNo], [requestTypeId], [requestDate]) VALUES\n"
                        + "(?, ?, 0, 3, GETDATE())";
                PreparedStatement pstDevice = cn.prepareStatement(sqlDevice, PreparedStatement.RETURN_GENERATED_KEYS);
                pstDevice.setInt(1, cusId);
                pstDevice.setInt(2, contractId);

                // Thiết lập giá trị cho các cột khác nếu cần
                // Thực hiện câu lệnh INSERT
                rs = pstDevice.executeUpdate();
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
    public int updateStatusRequestByContractId(int empId, int contractId) {
        int rs = 0;
        Connection cn = null;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqlDevice = "UPDATE Request\n"
                        + "SET empId = ?, statusNo = 1, finishDate = GETDATE()\n"
                        + "OUTPUT inserted.requestId AS requestId\n"
                        + "WHERE contractId = ? AND empId IS NULL AND statusNo = 0";
                PreparedStatement pstDevice = cn.prepareStatement(sqlDevice);
                pstDevice.setInt(1, empId);
                pstDevice.setInt(2, contractId);
                // Thiết lập giá trị cho các cột khác nếu cần
                ResultSet table = pstDevice.executeQuery();
                if (table != null && table.next()) { // Kiểm tra xem ResultSet có hàng dữ liệu nào không
                    rs = table.getInt("requestId"); // Lấy giá trị "requestId" từ ResultSet
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
    public int setRequestToTechni(int empId, int requestId, String sqlQueries) {
        int rs = 0;
        Connection cn = null;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                PreparedStatement pstDevice = cn.prepareStatement(sqlQueries);
                pstDevice.setInt(1, empId);
                pstDevice.setInt(2, requestId);
                // Thiết lập giá trị cho các cột khác nếu cần
                ResultSet table = pstDevice.executeQuery();
                if (table != null && table.next()) { // Kiểm tra xem ResultSet có hàng dữ liệu nào không
                    rs = table.getInt("requestId"); // Lấy giá trị "requestId" từ ResultSet
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
