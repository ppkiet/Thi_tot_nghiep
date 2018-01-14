package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {

    Connection conn;

    //netstat -an -P TCP
    public Connection getSQLServerConnection()
            throws SQLException, ClassNotFoundException {
        String hostName = "localhost";
        String database = "Test";
        String userName = "sa";
        String password = "ppkiet123";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            String connectionURL = "jdbc:sqlserver://" + hostName + ":1433" + ";databaseName=" + database;

            conn = DriverManager.getConnection(connectionURL, userName, password);
//            System.out.println("thanh cong");
        } catch (Exception e) {
            System.out.println("that bai " + e);
        }
        System.out.println("connect successful");
        return conn;
    }
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Connect cn = new Connect();
        cn.getSQLServerConnection();
    }
    
    

}
