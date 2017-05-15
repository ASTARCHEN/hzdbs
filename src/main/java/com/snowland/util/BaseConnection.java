package com.snowland.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseConnection {

  //  private static String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static String driverName = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/bs_hzd";
    private static String uid = "root";
    private static String pwd = "root";

    private Connection conn;
    public Connection getConnection() {
    	if (this.conn != null)
    		return this.conn;
        Connection conn = null;
        try {
            Class.forName(driverName);
            conn = DriverManager.getConnection(url, uid, pwd);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.conn = conn;
    }

    public static void closeAll(Connection conn, PreparedStatement ps,
            ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeAll(Connection conn, PreparedStatement ps) {
        try {

            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void close(PreparedStatement ps) {
    	closeAll(conn, ps);
    }
    
    public void close(PreparedStatement ps, ResultSet rs) {
    	closeAll(conn, ps, rs);
    }
}
