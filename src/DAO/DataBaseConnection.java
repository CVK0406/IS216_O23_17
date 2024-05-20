package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataBaseConnection {

    static {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DataBaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Connection getConnection() {
        Connection con = null;
        final String url = "jdbc:oracle:thin:@localhost:1521:xe";
        final String user = "testdb25";
        final String password = "123456";
        
        try {
            con = DriverManager.getConnection(url, user, password);
            return con;
        } catch (SQLException e) {
            return null;
        }
    }
}
