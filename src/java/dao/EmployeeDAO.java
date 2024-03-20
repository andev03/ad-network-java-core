/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Employee;
import dto.Users;
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
public class EmployeeDAO implements I_EmployeeDAO {

    @Override
    public Employee getEmployee(String email, String password) {
        Connection cn = null;
        Employee rs = null;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqlEmp = "Select [accId], [userId], [statusNo], [roleId], [accEmail], [accPassEmp] \n"
                        + "From Employee\n"
                        + "Where [accEmail] like ? and [accPassEmp] like ?";
                PreparedStatement pstEmp = cn.prepareStatement(sqlEmp);
                pstEmp.setString(1, email);
                pstEmp.setString(2, password);

                ResultSet tableEmp = pstEmp.executeQuery();
                if (tableEmp != null && tableEmp.next()) {
                    int e_accId = tableEmp.getInt("accId");
                    int e_userId = tableEmp.getInt("userId");
                    int e_statusNo = tableEmp.getInt("statusNo");
                    int e_roles = tableEmp.getInt("roleId");
                    String e_accEmail = tableEmp.getString("accEmail");
                    String e_accPassEmp = tableEmp.getString("accPassEmp");

                    rs = new Employee(e_accId, new UsersDAO().getUsers(e_userId), new StatusDAO().getStatus(e_statusNo), new RolesDAO().getRoles(e_roles), e_accEmail, e_accPassEmp);
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
    public Employee getEmployeeByAccId(int accId) {
        Connection cn = null;
        Employee rs = null;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqlEmp = "Select [accId], [userId], [statusNo], [roleId], [accEmail], [accPassEmp] \n"
                        + "From Employee\n"
                        + "Where [accId] = ?";
                PreparedStatement pstEmp = cn.prepareStatement(sqlEmp);
                pstEmp.setInt(1, accId);

                ResultSet tableEmp = pstEmp.executeQuery();
                if (tableEmp != null && tableEmp.next()) {
                    int e_accId = tableEmp.getInt("accId");
                    int e_userId = tableEmp.getInt("userId");
                    int e_statusNo = tableEmp.getInt("statusNo");
                    int e_roles = tableEmp.getInt("roleId");
                    String e_accEmail = tableEmp.getString("accEmail");
                    String e_accPassEmp = tableEmp.getString("accPassEmp");

                    rs = new Employee(e_accId, new UsersDAO().getUsers(e_userId), new StatusDAO().getStatus(e_statusNo), new RolesDAO().getRoles(e_roles), e_accEmail, e_accPassEmp);
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
    public Employee getTechnician(int accId) {
        Connection cn = null;
        Employee rs = null;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqlTechnician = "Select [accId], [userId], [statusNo], [roleId], [accEmail], [accPassEmp] \n"
                        + "From Employee\n"
                        + "Where [roleId] = 2 and [accId] = ?";
                PreparedStatement pstTechnician = cn.prepareStatement(sqlTechnician);
                pstTechnician.setInt(1, accId);

                ResultSet tableTechnician = pstTechnician.executeQuery();
                if (tableTechnician != null && tableTechnician.next()) {
                    int e_accId = tableTechnician.getInt("accId");
                    int e_userId = tableTechnician.getInt("userId");
                    int e_statusNo = tableTechnician.getInt("statusNo");
                    int e_roles = tableTechnician.getInt("roleId");
                    String e_accEmail = tableTechnician.getString("accEmail");
                    String e_accPassEmp = tableTechnician.getString("accPassEmp");

                    rs = new Employee(e_accId, new UsersDAO().getUsers(e_userId), new StatusDAO().getStatus(e_statusNo), new RolesDAO().getRoles(e_roles), e_accEmail, e_accPassEmp);
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
    public ArrayList<Employee> getAllTechnician() {
        ArrayList<Employee> allTechnician = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqlAllTechnician = "Select [accId], [userId], [statusNo], [roleId], [accEmail], [accPassEmp] \n"
                        + "From [Employee]";
                Statement stAllTechnician = cn.createStatement();
                ResultSet tableAllTechnician = stAllTechnician.executeQuery(sqlAllTechnician);
                if (tableAllTechnician != null) {
                    while (tableAllTechnician.next()) {
                        int e_accId = tableAllTechnician.getInt("accId");
                        int e_userId = tableAllTechnician.getInt("userId");
                        int e_statusNo = tableAllTechnician.getInt("statusNo");
                        int e_roles = tableAllTechnician.getInt("roleId");
                        String e_accEmail = tableAllTechnician.getString("accEmail");
                        String e_accPassEmp = tableAllTechnician.getString("accPassEmp");

                        allTechnician.add(new Employee(e_accId, new UsersDAO().getUsers(e_userId), new StatusDAO().getStatus(e_statusNo), new RolesDAO().getRoles(e_roles), e_accEmail, e_accPassEmp));
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
        return allTechnician;
    }

    @Override
    public ArrayList<Employee> getTechnicianByScope(int beginScope, int endScope) {
        ArrayList<Employee> techByScope = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqltechByScope = "Select [accId], [userId], [statusNo], [roleId], [accEmail], [accPassEmp] \n"
                        + "From [Employee]\n"
                        + "Where [accId] >= ? And [accId] <= ?";
                PreparedStatement pstTechByScope = cn.prepareStatement(sqltechByScope);
                pstTechByScope.setInt(1, beginScope);
                pstTechByScope.setInt(2, endScope);
                ResultSet tableTechByScope = pstTechByScope.executeQuery();
                if (tableTechByScope != null) {
                    while (tableTechByScope.next()) {
                        int e_accId = tableTechByScope.getInt("accId");
                        int e_userId = tableTechByScope.getInt("userId");
                        int e_statusNo = tableTechByScope.getInt("statusNo");
                        int e_roles = tableTechByScope.getInt("roleId");
                        String e_accEmail = tableTechByScope.getString("accEmail");
                        String e_accPassEmp = tableTechByScope.getString("accPassEmp");

                        techByScope.add(new Employee(e_accId, new UsersDAO().getUsers(e_userId), new StatusDAO().getStatus(e_statusNo), new RolesDAO().getRoles(e_roles), e_accEmail, e_accPassEmp));
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
        return techByScope;
    }

    @Override
    public int quantityTechnician() {
        Connection cn = null;
        int quantity = 0;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqlQuantityTechnician = "Select COUNT(accId) As [Quantity]\n"
                        + "From [Employee] \n"
                        + "Where [roleId] = 2";
                Statement stQuantityTechnician = cn.createStatement();
                ResultSet tableQuantityTechnician = stQuantityTechnician.executeQuery(sqlQuantityTechnician);
                if (tableQuantityTechnician != null && tableQuantityTechnician.next()) {
                    quantity = tableQuantityTechnician.getInt("Quantity");
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
    public ArrayList<Employee> getScopeEmployeeByEmail(int beginScope, int endScope, String email) {
        ArrayList<Employee> empByEmail = new ArrayList<>();
        Connection cn = null;

        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqlAllEmpByEmail = "WITH SortedUsers AS (\n"
                        + "    SELECT [accId], [userId], [statusNo], [roleId], [accEmail], [accPassEmp],\n"
                        + "           ROW_NUMBER() Over(Order By accId)  AS RowNumber\n"
                        + "    FROM Employee\n"
                        + "	WHERE [accEmail] LIKE ?\n"
                        + ")\n"
                        + "SELECT [accId], [userId], [statusNo], [roleId], [accEmail], [accPassEmp]\n"
                        + "FROM SortedUsers\n"
                        + "WHERE RowNumber BETWEEN ? AND ?";
                PreparedStatement pstEmpByEmail = cn.prepareStatement(sqlAllEmpByEmail);
                pstEmpByEmail.setString(1, email + "%");
                pstEmpByEmail.setInt(2, beginScope);
                pstEmpByEmail.setInt(3, endScope);

                ResultSet tableEmpByEmail = pstEmpByEmail.executeQuery();

                if (tableEmpByEmail != null) {
                    while (tableEmpByEmail.next()) {
                        int e_accId = tableEmpByEmail.getInt("accId");
                        int e_userId = tableEmpByEmail.getInt("userId");
                        int e_statusNo = tableEmpByEmail.getInt("statusNo");
                        int e_roles = tableEmpByEmail.getInt("roleId");
                        String e_accEmail = tableEmpByEmail.getString("accEmail");
                        String e_accPassEmp = tableEmpByEmail.getString("accPassEmp");
                        empByEmail.add(new Employee(e_accId, new UsersDAO().getUsers(e_userId), new StatusDAO().getStatus(e_statusNo), new RolesDAO().getRoles(e_roles), e_accEmail, e_accPassEmp));
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
        return empByEmail;
    }

    @Override
    public Employee getEmployeeByAccIdForContract(int accId) {
        Connection cn = null;
        Employee rs = null;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqlEmp = "Select [accId], [userId], [roleId] \n"
                        + "From Employee \n"
                        + "where accId = ?";
                PreparedStatement pstEmp = cn.prepareStatement(sqlEmp);
                pstEmp.setInt(1, accId);

                ResultSet tableEmp = pstEmp.executeQuery();
                if (tableEmp != null && tableEmp.next()) {
                    int e_accId = tableEmp.getInt("accId");
                    int e_userId = tableEmp.getInt("userId");
                    int e_roles = tableEmp.getInt("roleId");
                    rs = new Employee(e_accId, new UsersDAO().getUsersForViewContract(e_userId), new RolesDAO().getRoles(e_roles));
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
    public int updateStatusTechni(int accId, int statusNo) {
        Connection cn = null;
        int rs = 0;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqlUpdateStatusCus = "Update Employee \n"
                        + "Set statusNo = ?\n"
                        + "Where accId = ?";

                PreparedStatement pstUpdateStatusCus = cn.prepareStatement(sqlUpdateStatusCus);
                pstUpdateStatusCus.setInt(1, statusNo);
                pstUpdateStatusCus.setInt(2, accId);
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
    public ArrayList<Employee> getAllTechnicianActive() {
        ArrayList<Employee> allTechnicianActive = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqlAllTechnician = "Select [accId], [userId], [statusNo], [roleId], [accEmail], [accPassEmp] \n"
                        + "From [Employee]"
                        + "WHERE [roleId] = 2 AND [statusNo] = 0";
                Statement stAllTechnician = cn.createStatement();
                ResultSet tableAllTechnician = stAllTechnician.executeQuery(sqlAllTechnician);
                if (tableAllTechnician != null) {
                    while (tableAllTechnician.next()) {
                        int e_accId = tableAllTechnician.getInt("accId");
                        int e_userId = tableAllTechnician.getInt("userId");
                        int e_statusNo = tableAllTechnician.getInt("statusNo");
                        int e_roles = tableAllTechnician.getInt("roleId");
                        String e_accEmail = tableAllTechnician.getString("accEmail");
                        String e_accPassEmp = tableAllTechnician.getString("accPassEmp");

                        allTechnicianActive.add(new Employee(e_accId, new UsersDAO().getUsers(e_userId), new StatusDAO().getStatus(e_statusNo), new RolesDAO().getRoles(e_roles), e_accEmail, e_accPassEmp));
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
        return allTechnicianActive;
    }

    @Override
    public ArrayList<Employee> sortEmployeeByAction(int beginCus, int endCus, String action) {
        ArrayList<Employee> sortEmployee = new ArrayList<>();
        Connection cn = null;

        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqlSortEmp;
                if (action.equalsIgnoreCase("sortByNameEmp")) {
                    sqlSortEmp = "WITH SortedUsers AS (\n"
                            + "SELECT [accId], Employee.[userId], [statusNo], [roleId], [accEmail], [accPassEmp],\n"
                            + "ROW_NUMBER() Over(Order By lastName, firstName asc) AS RowNumber\n"
                            + "FROM Employee JOIN Users ON Employee.[userId] = Users.[userId])\n"
                            + "SELECT [accId], [userId], [statusNo], [roleId], [accEmail], [accPassEmp], RowNumber\n"
                            + "FROM SortedUsers\n"
                            + "WHERE RowNumber BETWEEN ? AND ?";
                } else {
                    sqlSortEmp = "WITH SortedUsers AS (\n"
                            + "SELECT [accId], [userId], [statusNo], [roleId], [accEmail], [accPassEmp],\n"
                            + "ROW_NUMBER() Over(Order By [statusNo] desc) AS RowNumber\n"
                            + "FROM Employee)\n"
                            + "SELECT [accId], [userId], [statusNo], [roleId], [accEmail], [accPassEmp], RowNumber\n"
                            + "FROM SortedUsers\n"
                            + "WHERE RowNumber BETWEEN ? AND ?";
                }

                PreparedStatement pstSortEmp = cn.prepareStatement(sqlSortEmp);
                pstSortEmp.setInt(1, beginCus);
                pstSortEmp.setInt(2, endCus);

                ResultSet tableSortEmp = pstSortEmp.executeQuery();

                if (tableSortEmp != null) {
                    while (tableSortEmp.next()) {
                        int c_accId = tableSortEmp.getInt("accId");
                        int c_userId = tableSortEmp.getInt("userId");
                        int c_statusNo = tableSortEmp.getInt("statusNo");
                        int c_roles = tableSortEmp.getInt("roleId");
                        String c_accEmail = tableSortEmp.getString("accEmail");
                        String c_accPassEmp = tableSortEmp.getString("accPassEmp");
                        sortEmployee.add(new Employee(c_accId, new UsersDAO().getUsers(c_userId), new StatusDAO().getStatus(c_statusNo), new RolesDAO().getRoles(c_roles), c_accEmail, c_accPassEmp));
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
        return sortEmployee;
    }
}
