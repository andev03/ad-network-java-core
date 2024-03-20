/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Service;
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
public class ServiceDAO implements I_ServiceDAO {

    @Override
    public Service getService(int serviceId) {
        Connection cn = null;
        Service rs = null;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqlService = "Select [serviceId], [deviceId], [sTypeId], [price], [serviceName], [serviceStatus]\n"
                        + "From [Service]\n"
                        + "Where [serviceId] = ?";

                PreparedStatement pstService = cn.prepareStatement(sqlService);

                pstService.setInt(1, serviceId);

                ResultSet tableService = pstService.executeQuery();

                if (tableService != null && tableService.next()) {
                    int s_serviceId = tableService.getInt("serviceId");
                    int s_deviceId = tableService.getInt("deviceId");
                    int s_sTypeId = tableService.getInt("sTypeId");
                    double s_price = tableService.getDouble("price");
                    String s_serviceName = tableService.getString("serviceName");
                    int s_serviceStatus = tableService.getInt("serviceStatus");

                    rs = new Service(s_serviceId, new DeviceDAO().getDevice(s_deviceId), new ServiceTypeDAO().getServiceType(s_sTypeId), s_price, s_serviceName, new ServiceStatusTypeDAO().getServiceStatusType(s_serviceStatus));
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
    public ArrayList<Service> getServiceByScope(int scopeBegin, int scopeEnd) {
        ArrayList<Service> serByType = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqlSerByType = "Select [serviceId], [deviceId], [sTypeId], [price], [serviceName], [serviceStatus]\n"
                        + "From [Service]\n"
                        + "Where [serviceId] >= ? And [serviceId] <= ?";
                PreparedStatement pstServiceByType = cn.prepareStatement(sqlSerByType);
                pstServiceByType.setInt(1, scopeBegin);
                pstServiceByType.setInt(2, scopeEnd);
                ResultSet tableAllService = pstServiceByType.executeQuery();
                if (tableAllService != null) {
                    while (tableAllService.next()) {
                        int s_serviceId = tableAllService.getInt("serviceId");
                        int s_deviceId = tableAllService.getInt("deviceId");
                        int s_sTypeId = tableAllService.getInt("sTypeId");
                        double s_price = tableAllService.getDouble("price");
                        String s_serviceName = tableAllService.getString("serviceName");
                        int s_serviceStatus = tableAllService.getInt("serviceStatus");

                        serByType.add(new Service(s_serviceId, new DeviceDAO().getDevice(s_deviceId), new ServiceTypeDAO().getServiceType(s_sTypeId), s_price, s_serviceName, new ServiceStatusTypeDAO().getServiceStatusType(s_serviceStatus)));
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
        return serByType;
    }

    @Override
    public ArrayList<Service> getAllService() {
        ArrayList<Service> allService = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqlAllService = "Select [serviceId], [deviceId], [sTypeId], [price], [serviceName], [serviceStatus]\n"
                        + "From [Service]";
                Statement stAllService = cn.createStatement();

                ResultSet tableAllService = stAllService.executeQuery(sqlAllService);
                if (tableAllService != null) {
                    while (tableAllService.next()) {
                        int s_serviceId = tableAllService.getInt("serviceId");
                        int s_deviceId = tableAllService.getInt("deviceId");
                        int s_sTypeId = tableAllService.getInt("sTypeId");
                        double s_price = tableAllService.getDouble("price");
                        String s_serviceName = tableAllService.getString("serviceName");
                        int s_serviceStatus = tableAllService.getInt("serviceStatus");

                        allService.add(new Service(s_serviceId, new DeviceDAO().getDevice(s_deviceId), new ServiceTypeDAO().getServiceType(s_sTypeId), s_price, s_serviceName, new ServiceStatusTypeDAO().getServiceStatusType(s_serviceStatus)));
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
        return allService;
    }

    @Override
    public int quantityService() {
        Connection cn = null;
        int quantity = 0;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqlQuantityService = "Select COUNT(serviceId) As [Quantity]\n"
                        + "From [Service]";
                Statement stQuantityService = cn.createStatement();
                ResultSet tableQuantityService = stQuantityService.executeQuery(sqlQuantityService);
                if (tableQuantityService != null && tableQuantityService.next()) {
                    quantity = tableQuantityService.getInt("Quantity");
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
    public ArrayList<Service> getServiceByType(int sTypeId) {
        ArrayList<Service> serByType = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqlSerByType = "Select [serviceId], [deviceId], [sTypeId], [price], [serviceName], [serviceStatus]\n"
                        + "From [Service]\n"
                        + "Where [sTypeId] = ?　And [serviceStatus] != 2";
                PreparedStatement pstServiceByType = cn.prepareStatement(sqlSerByType);
                pstServiceByType.setInt(1, sTypeId);
                ResultSet tableAllService = pstServiceByType.executeQuery();
                if (tableAllService != null) {
                    while (tableAllService.next()) {
                        int s_serviceId = tableAllService.getInt("serviceId");
                        int s_deviceId = tableAllService.getInt("deviceId");
                        int s_sTypeId = tableAllService.getInt("sTypeId");
                        double s_price = tableAllService.getDouble("price");
                        String s_serviceName = tableAllService.getString("serviceName");
                        int s_serviceStatus = tableAllService.getInt("serviceStatus");

                        serByType.add(new Service(s_serviceId, new DeviceDAO().getDevice(s_deviceId), new ServiceTypeDAO().getServiceType(s_sTypeId), s_price, s_serviceName, new ServiceStatusTypeDAO().getServiceStatusType(s_serviceStatus)));
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
        return serByType;
    }

    @Override
    public Service getServiceForCartByName(String name) {
        Connection cn = null;
        Service rs = null;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqlService = "Select [serviceId], [deviceId], [sTypeId], [price], [serviceName]\n"
                        + "From [Service]\n"
                        + "Where [serviceName] = ?";

                PreparedStatement pstService = cn.prepareStatement(sqlService);

                pstService.setString(1, name);

                ResultSet tableService = pstService.executeQuery();

                if (tableService != null && tableService.next()) {
                    int s_serviceId = tableService.getInt("serviceId");
                    int s_deviceId = tableService.getInt("deviceId");
                    int s_sTypeId = tableService.getInt("sTypeId");
                    double s_price = tableService.getDouble("price");
                    String s_serviceName = tableService.getString("serviceName");

                    rs = new Service(s_serviceId, new DeviceDAO().getDevice(s_deviceId), new ServiceTypeDAO().getServiceType(s_sTypeId), s_price, s_serviceName);
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
    public int createService(int deviceId, int sTypeId, double price, String serviceName) {
        int rs = 0;
        Connection cn = null;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqlService = "INSERT INTO [Service] ([deviceId], [sTypeId], [price], [serviceName], [serviceStatus]) VALUES\n"
                        + "(?,?,?,?,1)";
                PreparedStatement pstService = cn.prepareStatement(sqlService);
                pstService.setInt(1, deviceId);
                pstService.setInt(2, sTypeId);
                pstService.setDouble(3, price);
                pstService.setString(4, serviceName);
                rs = pstService.executeUpdate();
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
    public int updatePriceService(int serviceId, double servicePrice) {
        int rs = 0;
        Connection cn = null;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqlService = "Update [Service] \n"
                        + "Set price = ?\n"
                        + "Where serviceId = ?";
                PreparedStatement pstService = cn.prepareStatement(sqlService);
                pstService.setDouble(1, servicePrice);
                pstService.setInt(2, serviceId);
                rs = pstService.executeUpdate();
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
    public int updateStatusService(int serviceId, int serviceStatus) {
        int rs = 0;
        Connection cn = null;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqlService = "Update [Service] \n"
                        + "Set serviceStatus = ?\n"
                        + "Where serviceId = ?";
                PreparedStatement pstService = cn.prepareStatement(sqlService);
                pstService.setInt(1, serviceStatus);
                pstService.setInt(2, serviceId);
                rs = pstService.executeUpdate();
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
    public int updatePriceAndStatusService(int serviceId, double servicePrice, int serviceStatus) {
        int rs = 0;
        Connection cn = null;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqlService = "Update [Service] \n"
                        + "Set price = ?, serviceStatus = ?  \n"
                        + "Where serviceId = ?";
                PreparedStatement pstService = cn.prepareStatement(sqlService);
                pstService.setDouble(1, servicePrice);
                pstService.setInt(2, serviceStatus);
                pstService.setInt(3, serviceId);
                rs = pstService.executeUpdate();
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
    public ArrayList<Service> getScopeServiceByName(int beginScope, int endScope, String nameService) {
        ArrayList<Service> serviceByName = new ArrayList<>();
        Connection cn = null;

        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqlAllServiceByName
                        = "WITH SortedUsers AS (\n"
                        + "    SELECT serviceId, deviceId, sTypeId, price, serviceName, serviceStatus, \n"
                        + "           ROW_NUMBER() Over(Order By serviceId)  AS RowNumber \n"
                        + "    FROM Service \n"
                        + "    WHERE serviceName LIKE ? "
                        + ")\n"
                        + "SELECT serviceId, deviceId, sTypeId, price, serviceName, serviceStatus \n"
                        + "FROM SortedUsers \n"
                        + "WHERE RowNumber BETWEEN ? AND ?";
                PreparedStatement pstServiceByName = cn.prepareStatement(sqlAllServiceByName);
                
                pstServiceByName.setString(1,"%" + nameService + "%");
                pstServiceByName.setInt(2, beginScope);
                pstServiceByName.setInt(3, endScope);

                ResultSet tableServiceByName = pstServiceByName.executeQuery();

                if (tableServiceByName != null) {
                    while (tableServiceByName.next()) {
                        int s_serviceId = tableServiceByName.getInt("serviceId");
                        int s_deviceId = tableServiceByName.getInt("deviceId");
                        int s_sTypeId = tableServiceByName.getInt("sTypeId");
                        double s_price = tableServiceByName.getDouble("price");
                        String s_serviceName = tableServiceByName.getString("serviceName");
                        int s_serviceStatus = tableServiceByName.getInt("serviceStatus");

                        serviceByName.add(new Service(s_serviceId, new DeviceDAO().getDevice(s_deviceId), new ServiceTypeDAO().getServiceType(s_sTypeId), s_price, s_serviceName, new ServiceStatusTypeDAO().getServiceStatusType(s_serviceStatus)));
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
        return serviceByName;
    }
}
