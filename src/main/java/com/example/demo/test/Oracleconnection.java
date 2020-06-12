package com.example.demo.test;

import oracle.jdbc.OracleConnection;

import java.sql.*;

public class Oracleconnection {
    public static Connection getConnection() {
        String url = "jdbc:oracle:thin:@//10.0.11.83:1545/srm.weichai.com";
        String username = "lisongyue";
        String password = "lisongyue";
        String driver = "oracle.jdbc.driver.OracleDriver";
        try {
            Class.forName(driver);
            Connection con = DriverManager.getConnection(url, username, password);
            Statement state = con.createStatement();
            String sql = "select * from emp";
            ResultSet resultSet = state.executeQuery(sql);
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + "--" + resultSet.getString(2));
            }
            con.close();
            return con;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void main(String[] args) {
        Connection conn = Oracleconnection.getConnection();
    }
}
