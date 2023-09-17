package org.example.utils;


import java.sql.*;

public class MySQLDriver
{
    final private Connection _mysqlConn;

    public MySQLDriver() {
        String url = String.format(
                "jdbc:mysql://%s:%s/%s",
                "localhost",
                "3306",
                "opencart-db"
        );
        String username = "opencart";
        String password = "opencart";
        try {
            this._mysqlConn = DriverManager.getConnection(url, username, password);
            Runtime.getRuntime().addShutdownHook(new Thread(MySQLDriver.this::close));
        } catch (SQLException e) {
            String msg = "Could not connect to database";
            throw new RuntimeException(msg, e);
        }
    }

    public ResultSet executeQuery(String query) {
        try {
            return this._mysqlConn.createStatement().executeQuery(query);
        } catch (SQLException e) {
            String msg = "Could not execute query: \"" + query + "\"";
            throw new RuntimeException(msg, e);
        }
    }

    public boolean execute(String query) {
        try {
            return this._mysqlConn.createStatement().execute(query);
        } catch (SQLException e) {
            String msg = "Could not execute query: \"" + query + "\"";
            throw new RuntimeException(msg, e);
        }
    }

    public PreparedStatement preparedStatement(String query) {
        try {
            return this._mysqlConn.prepareStatement(query);
        } catch (SQLException e) {
            String msg = "Could not create prepared statement with query: \"" + query + "\"";
            throw new RuntimeException(msg, e);
        }
    }

    final public void close() {
        try {
            this._mysqlConn.close();
        } catch (SQLException e) { /* Ignore */ }
    }
}

