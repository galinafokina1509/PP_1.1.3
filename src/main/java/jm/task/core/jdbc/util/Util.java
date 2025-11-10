package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private final String URL = "jdbc:mysql://localhost:3306/pp_jdbc?useSSL=false&serverTimezone=UTC";
    private final String USERNAME = "root";
    private final String PASSWORD = "root";

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL,USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}