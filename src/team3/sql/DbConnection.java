
package team3.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private String username = "root";
    private String password = "";
    private String address  = "jdbc:mysql://localhost:3306/db_laundry";
    
    public Connection createConnection() throws SQLException{
        Connection conn = DriverManager.getConnection(
                    address
                    , username
                    , password);
        return conn;
    }
    // kung dito kasi tayo mag try catch wala ma re return na connection
    
    public boolean isConnectedToDB() {
        boolean isConnect;
        try {
            Connection conn = createConnection();
            isConnect = true;
            conn.close();
        } catch (Exception e) {
            isConnect = false;
        }
        return isConnect;
    }
    
    public static void main(String[] args) {
        DbConnection callConn = new DbConnection();
        String connectionTest;
        try {
            Connection conn = DriverManager.getConnection(
                    callConn.address
                    , callConn.username
                    , callConn.password);
            connectionTest = "DbConnection Successful";
            conn.close();
        } catch (Exception e) {
            connectionTest = "DbConnection Fail:\n";
            connectionTest += e.toString();
        }
        
        System.out.println(connectionTest);
    }
}