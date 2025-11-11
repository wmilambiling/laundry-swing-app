package team3.sql;
import java.sql.*;
import javax.swing.*;
import javax.swing.JComboBox;
import team3.sql.DbConnection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Group 3
 */
public class OrderCrud extends DbConnection {
    String messageResult = "";
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;
    
    public String fillFnameData(String txt1) {
        String sqlQuery = "SELECT * FROM customer where = ?";
        try(Connection conn = createConnection();){
            pstmt = conn.prepareStatement(sqlQuery);
            pstmt.setString(1, txt1);
            rs = pstmt.executeQuery();
            
//            while (rs.next()) {
//                JComboBox<String> jComboBox1 = new JComboBox<>();
//                String item = rs.getString("first_name");
//                jComboBox1.addItem(item);
//            }
        } catch (Exception e){
            messageResult = "Error: " + e.toString();
        }
        return null;
    }
}
