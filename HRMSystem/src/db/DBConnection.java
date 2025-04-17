package db;

import java.sql.*;

public class DBConnection {
    public static Connection getConnection() throws Exception {
        String url = "jdbc:mysql://localhost:3306/hrms";
        String user = "root";
        String password = "Abhinay@30"; // change this
        return DriverManager.getConnection(url, user, password);
    }
}
