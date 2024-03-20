/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DBUtil {

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection cn = null;
        String IP = "localhost";
        String instanceName = "LAPTOP-6LUNS1UT\\SA";
        String port = "1433";
        String uid = "sa";
        String pwd = "12345";
        String db = "ProjectPRJ";
        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String url = "jdbc:sqlserver://" + IP + "\\" + instanceName + ":" + port
                + ";databasename=" + db + ";user=" + uid + ";password=" + pwd;
        Class.forName(driver);
        cn = DriverManager.getConnection(url);
        return cn;
    }

    public static void closeConnection(Connection c) {
        try {
            if (c != null) {
                c.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
