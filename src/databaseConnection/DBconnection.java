package databaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection {

    private static final String url = "jdbc:mysql://localhost:3306/zboruri?serverTimezone=UTC";
    private static final String user = "root";
    private static final String password = "";

    private static Connection connection;

    public static Connection getConnection() {
        if(connection == null) {
            try {
                connection = DriverManager.getConnection(url, user, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static boolean close() throws SQLException {
        if(connection != null) {
            connection.close();
            return true;
        }
        return false;
    }

}
