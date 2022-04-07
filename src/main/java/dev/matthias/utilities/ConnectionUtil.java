package dev.matthias.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

    public static Connection createConnection() {
        try {
            return DriverManager.getConnection(System.getenv("schooldb"));
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
