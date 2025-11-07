
package team3.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class ServiceCrud extends DbConnection{
    
    public String addService(String txt1, String txt2) {
        String messageResult = "";
        String sqlQuery = "INSERT INTO service(service_name, service_price)"
                + " VALUES ( ? , ? )";
        try(Connection conn = createConnection();) {
            PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
            pstmt.setString(1, txt1);
            pstmt.setString(2, txt2);
            
            int rowsAffected = pstmt.executeUpdate();
            
            messageResult = (rowsAffected == 1) ? "Success" 
                    : "Insert Query problem(" + rowsAffected + ")";
            
        } catch (Exception e) {
            messageResult = "Error: " + e.toString();
        }
        return messageResult;
    }
    
    public String updateService(String txt1, String txt2, int service_id) {
        String messageResult = "";
        String sqlQuery = "UPDATE service SET service_name = ? "
                + ", service_price = ?"
                + " WHERE service_id = ? ";
        try(Connection conn = createConnection();) {
            PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
            pstmt.setString(1, txt1);
            pstmt.setDouble(2, Double.parseDouble(txt2));
            pstmt.setInt(3, service_id);
            
            int rowsAffected = pstmt.executeUpdate();
            
            messageResult = (rowsAffected == 1) ? "Success" 
                    : "Update Query problem(" + rowsAffected + ")";
            
        } catch (Exception e) {
            messageResult = "Error: " + e.toString();
        }
        return messageResult;
    }
    
    public String deleteService(int service_id) {
        String messageResult = "";
        String sqlQuery = "DELETE FROM service"
                + " WHERE service_id = ? ";
        try(Connection conn = createConnection();) {
            PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
            pstmt.setInt(1, service_id);
            
            int rowsAffected = pstmt.executeUpdate();
            
            messageResult = (rowsAffected == 1) ? "Deleted" 
                    : "Delete Query problem(" + rowsAffected + ")";
            
        } catch (Exception e) {
            messageResult = "Error: " + e.toString();
        }
        return messageResult;
    }
    
    public void getAllServices(JTable tblResult) {
        FilterUtil callFilter = new FilterUtil();
        //callFilter.setlDatePattern("MMM/dd/yyyy");
        DefaultTableModel dtm = (DefaultTableModel) tblResult.getModel();
        dtm.setRowCount(0);
        String sqlQuery = "SELECT service_id, service_name, service_price"
            + " FROM service";
        try(Connection conn = createConnection();) {
            PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
            
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {                
                Object[] newRow = {
                    rs.getInt(1)
                    , rs.getString(2)
                    , rs.getDouble(3)                    
                };
                dtm.addRow(newRow);
            }
            
        } catch (Exception e) {
            String[] errRow = {"Error: ", e.toString()};
            dtm.addRow(errRow);
        }
    }
}

