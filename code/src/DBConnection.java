import java.sql.*;

public class DBConnection {
    public static Connection getConnection() {
        try {
            String url = "jdbc:mysql://localhost:3306/stadium";
            String user = "root";
            String pass = "poojamuna"; // ✅ replace with your actual password
            return DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            e.printStackTrace();
            return null; // <-- ⚠️ This is causing the error if not handled properly
        }
    }
}
