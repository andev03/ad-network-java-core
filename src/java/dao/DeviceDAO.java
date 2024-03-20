/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Device;
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
public class DeviceDAO implements I_DeviceDAO {

    @Override
    public Device getDevice(int deviceId) {
        Device rs = null;
        Connection cn = null;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqlDevice = "Select [deviceId], [deviceName], [devDetailDesc]  \n"
                        + "From Device \n"
                        + "Where [deviceId] = ?";
                PreparedStatement pstDevice = cn.prepareStatement(sqlDevice);
                pstDevice.setInt(1, deviceId);
                ResultSet tableDevice = pstDevice.executeQuery();

                if (tableDevice != null && tableDevice.next()) {
                    int d_deviceId = tableDevice.getInt("deviceId");
                    String d_deviceName = tableDevice.getString("deviceName");
                    String d_deviceDetail = tableDevice.getString("devDetailDesc");

                    rs = new Device(d_deviceId, d_deviceName, d_deviceDetail);
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
    public ArrayList<Device> getAllDevice() {
        ArrayList<Device> allDevice = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqlAllDevice = "Select [deviceId], [deviceName], [devDetailDesc]\n"
                        + "From [Device]";
                Statement stAllDevice = cn.createStatement();

                ResultSet tableAllDevice = stAllDevice.executeQuery(sqlAllDevice);
                if (tableAllDevice != null) {
                    while (tableAllDevice.next()) {
                        int d_deviceId = tableAllDevice.getInt("deviceId");
                        String d_deviceName = tableAllDevice.getString("deviceName");
                        String d_deviceDetail = tableAllDevice.getString("devDetailDesc");
                        allDevice.add(new Device(d_deviceId, d_deviceName, d_deviceDetail));
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
        return allDevice;
    }

    @Override
    public int createDevice(String deviceName, String deviceDetailDesc) {

        int rs = 0;
        Connection cn = null;
        try {
            cn = DBUtil.getConnection();
            if (cn != null) {
                String sqlDevice = "INSERT INTO [Device] ([deviceName], [devDetailDesc]) VALUES\n"
                        + "(?,?)";
                PreparedStatement pstDevice = cn.prepareStatement(sqlDevice);
                pstDevice.setString(1, deviceName);
                pstDevice.setString(2, deviceDetailDesc);
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
