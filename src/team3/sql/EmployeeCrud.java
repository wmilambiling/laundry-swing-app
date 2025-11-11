
package team3.sql;

import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;


public class EmployeeCrud extends DbConnection{
    
    private final Connection conn;

    // ✅ Constructor - handles connection safely
    public EmployeeCrud() {
        Connection tempConn = null;
        try {
            tempConn = new DbConnection().createConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        conn = tempConn;
    }

    // ✅ Get last inserted ID
    public int getLastInsertedId() {
        int id = -1;
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT LAST_INSERT_ID()")) {
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }
    
    // ✅ Return role_id given a role name
    public int getRoleId(String txtRole) {
        String query = "SELECT role_id FROM role WHERE role_name LIKE ?";
        int role_id = -1;
        
        try (PreparedStatement pstmt = conn.prepareStatement(query)){
            pstmt.setString(1, txtRole);
        
            try (ResultSet rs = pstmt.executeQuery()){                
                while (rs.next()) role_id = rs.getInt("role_id");                
            } catch (SQLException e) {
                e.printStackTrace();
            }
        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());    
        }
        return role_id;
    }
    
    // ✅ Return role_id given a role name
    public String getRoleName(int roleId) {
        String query = "SELECT role_name FROM role WHERE role_id = ?";
        String roleName = "";
        
        try (PreparedStatement pstmt = conn.prepareStatement(query)){
            pstmt.setInt(1, roleId);
        
            try (ResultSet rs = pstmt.executeQuery()){                
                while (rs.next()) roleName = rs.getString("role_name");                
            } catch (SQLException e) {
                e.printStackTrace();
            }
        
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());    
        }
        return roleName;
    }
    
    public ArrayList<String> getAllRoles(){
        ArrayList<String> roleList = new ArrayList();
        String sqlQuery = "SELECT role_name FROM role";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery);
            ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) 
                    roleList.add(rs.getString("role_name"));                
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roleList;
    }
    
    public DefaultComboBoxModel<String> getEmployeesName() {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        String sql = "SELECT CONCAT(first_name, ' ', last_name) AS FullName FROM employee ORDER BY first_name, last_name";

        try {
            
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                model.addElement(rs.getString("FullName"));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return model;
    }

    // ✅ Load employee data (all if limit == 0)
    public DefaultTableModel getEmployeeModel(int limit) {
        DefaultTableModel model = new DefaultTableModel(
            new String[]{"ID", "Role", "First Name", "Last Name", "Username", "Password", "Date Hired"}, 0
        );

        try {
            String sql = "SELECT * FROM employee";
            if (limit > 0) {
                sql += " LIMIT ?";
            }

            PreparedStatement ps = conn.prepareStatement(sql);
            if (limit > 0) {
                ps.setInt(1, limit);
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("employee_id"),
                    rs.getInt("role_id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("date_hired")
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return model;
    }
    
    public void getAllEmployees(JTable tblEmployees) {
        //FilterUtil callFilter = new FilterUtil();
        //callFilter.setlDatePattern("MMM/dd/yyyy");
        DefaultTableModel dtm = (DefaultTableModel) tblEmployees.getModel();
        dtm.setRowCount(0);
        
        String sql = "SELECT employee_id, first_name, last_name, role_id,";
            sql += "user_name, password, date_hired FROM employee";
        
        try(Connection conn = createConnection();) {
            
            PreparedStatement pstmt = conn.prepareStatement(sql);
            
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {                
                Object[] newRow = {
                    rs.getInt(1)
                    , rs.getString(2)
                    , rs.getString(3)
                    , getRoleName(rs.getInt(4))
                    , rs.getString(5)
                    , rs.getString(6)
                    , rs.getDate(7)                                        
                };
                dtm.addRow(newRow);
            }
            
        } catch (Exception e) {
            String[] errRow = {"Error: ", e.toString()};
            dtm.addRow(errRow);
        }
    }

    // ✅ Add new employee
    public void addEmployee(String roleName, String firstName, String lastName,
            String userName, String passWord, String dateHired) {
        
        int roleId = getRoleId(roleName); // get role id of role name
        
        String sql = "INSERT INTO employee (role_id, first_name, last_name, ";
                sql += "user_name, password, date_hired) VALUES (?,?,?,?,?,?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, roleId);
            ps.setString(2, firstName);
            ps.setString(3, lastName);
            ps.setString(4, userName);
            ps.setString(5, passWord);
            ps.setString(6, dateHired);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ✅ Update existing employee
    public void updateEmployee(int id, String roleName, String firstName, 
        String lastName,String userName, String passWord, String dateHired) {
        
        int roleId = getRoleId(roleName);

        String sql = "UPDATE employee SET role_id=?, first_name=?, last_name=?, ";
            sql += "user_name=?, password=?, date_hired=? WHERE employee_id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, roleId);
            ps.setString(2, firstName);
            ps.setString(3, lastName);
            ps.setString(4, userName);
            ps.setString(5, passWord);
            ps.setString(6, dateHired);
            ps.setInt(7, id);
            
            //System.out.println("Update sql: " + ps.toString());            
            int rowsAffected = ps.executeUpdate();
            System.out.println(rowsAffected + " row(s) updated.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ✅ Delete employee by ID
    public void deleteEmployee(int roleId) {
        
        String sql = "DELETE FROM employee WHERE employee_id=?";
        
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, roleId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

