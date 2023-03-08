package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionManager {
    private static final String url = "jdbc:mysql://127.0.0.1:3307/cinema";
    private static final String username = "root";
    private static final String password = "";

    static {
        loadDriver();
    }

    public static Connection open(){
        try {
            return DriverManager.getConnection(url,username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void loadDriver() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Error loading driver");
        }
    }

    private ConnectionManager(){};
}
