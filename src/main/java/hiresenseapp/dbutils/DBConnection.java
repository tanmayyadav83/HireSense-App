package hiresenseapp.dbutils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static String dbUrl;
    private static String username;
    private static String password;

    // Called once from AppInitializer
    public static void openConnection(String url, String user, String pass) {
        dbUrl = url;
        username = user;
        password = pass;
        System.out.println("DB config loaded");
    }

    // Creates a NEW connection every time
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dbUrl, username, password);
    }

    // DO NOT close shared config
    public static void closeConnection() {
        // Left empty intentionally
        // Each DAO closes its own connection
        System.out.println("closeConnection() called — no global connection to close");
    }
}
