package com.fireflyest.aktivni.util;

import java.sql.*;

public class JdbcUtils {

    public static JdbcUtils jdbcUtils = new JdbcUtils();

    public static JdbcUtils getInstance(){ return jdbcUtils; }

    private JdbcUtils() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static Connection getConnection(String url, String user, String password) throws SQLException {
        return DriverManager.getConnection(url , user, password);
    }

    public static void close(ResultSet resultSet, Statement statement, Connection connection){
        try {
            if(resultSet != null)resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if(statement != null)statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if(connection != null)connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close(Statement statement, Connection connection){
        close(null, statement, connection);
    }

}
