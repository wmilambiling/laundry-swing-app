//Shayne Fabelina


package team3.sql;

import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class EmployeeCrud {
    private final Connection conn;

    public EmployeeCrud() {
        Connection tmp = null;
        try {
            tmp = new DbConnection().createConnection();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        this.conn = tmp;
    }

    /**
     * Get last inserted id (useful after insert with auto_increment)
     */
    public int getLastInsertedId() {
        int id = -1;
        if (conn == null) return id;
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT LAST_INSERT_ID()")) {
            if (rs.next()) id = rs.getInt(1);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return id;
    }

    /**
     * Return a DefaultTableModel with employees.
     * Columns: ID, Role ID, Role Name, First Name, Last Name, Username, Password, Date Hired
     * If limit == 0, returns all rows.
     */
    public DefaultTableModel getEmployeeData(int limit) {
        DefaultTableModel model = new DefaultTableModel(
            new String[]{"ID","Role ID","Role Name","First Name","Last Name","Username","Password","Date Hired"}, 0
        );
        if (conn == null) return model;

        String baseSql = "SELECT e.employee_id, e.role_id, r.role_name, e.first_name, e.last_name, e.user_name, e.password, e.date_hired "
                       + "FROM employee e LEFT JOIN role r ON e.role_id = r.role_id "
                       + "ORDER BY e.employee_id DESC";
        String sql = (limit > 0) ? baseSql + " LIMIT ?" : baseSql;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            if (limit > 0) ps.setInt(1, limit);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    model.addRow(new Object[]{
                        rs.getInt("employee_id"),
                        rs.getInt("role_id"),
                        rs.getString("role_name"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("user_name"),
                        rs.getString("password"),
                        rs.getTimestamp("date_hired")
                    });
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return model;
    }

    /**
     * Search employees by keyword (first, last, username, role_name).
     * Returns DefaultTableModel with same columns as getEmployeeData.
     */
    public DefaultTableModel searchEmployees(String keyword) {
        DefaultTableModel model = new DefaultTableModel(
            new String[]{"ID","Role ID","Role Name","First Name","Last Name","Username","Password","Date Hired"}, 0
        );
        if (conn == null) return model;

        String sql = "SELECT e.employee_id, e.role_id, r.role_name, e.first_name, e.last_name, e.user_name, e.password, e.date_hired "
                   + "FROM employee e LEFT JOIN role r ON e.role_id = r.role_id "
                   + "WHERE e.first_name LIKE ? OR e.last_name LIKE ? OR e.user_name LIKE ? OR r.role_name LIKE ? "
                   + "ORDER BY e.employee_id DESC";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            String q = "%" + keyword + "%";
            ps.setString(1, q);
            ps.setString(2, q);
            ps.setString(3, q);
            ps.setString(4, q);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    model.addRow(new Object[]{
                        rs.getInt("employee_id"),
                        rs.getInt("role_id"),
                        rs.getString("role_name"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("user_name"),
                        rs.getString("password"),
                        rs.getTimestamp("date_hired")
                    });
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return model;
    }

    /**
     * Add an employee.
     * If roleId <= 0, NULL will be set for role_id.
     * If dateHired is null, current timestamp will be used.
     */
    public boolean addEmployee(int roleId, String firstName, String lastName, String username, String password, Timestamp dateHired) {
        if (conn == null) return false;
        String sql = "INSERT INTO employee (role_id, first_name, last_name, user_name, password, date_hired) VALUES (?,?,?,?,?,?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            if (roleId <= 0) ps.setNull(1, Types.INTEGER);
            else ps.setInt(1, roleId);
            ps.setString(2, firstName);
            ps.setString(3, lastName);
            ps.setString(4, username);
            ps.setString(5, password);
            if (dateHired != null) ps.setTimestamp(6, dateHired);
            else ps.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * Update an employee by id.
     */
    public boolean updateEmployee(int id, int roleId, String firstName, String lastName, String username, String password, Timestamp dateHired) {
        if (conn == null) return false;
        String sql = "UPDATE employee SET role_id=?, first_name=?, last_name=?, user_name=?, password=?, date_hired=? WHERE employee_id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            if (roleId <= 0) ps.setNull(1, Types.INTEGER);
            else ps.setInt(1, roleId);
            ps.setString(2, firstName);
            ps.setString(3, lastName);
            ps.setString(4, username);
            ps.setString(5, password);
            if (dateHired != null) ps.setTimestamp(6, dateHired);
            else ps.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
            ps.setInt(7, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * Delete employee by id.
     */
    public boolean deleteEmployee(int id) {
        if (conn == null) return false;
        String sql = "DELETE FROM employee WHERE employee_id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * Get single employee by id. Returns EmployeeRecord DTO or null if not found.
     */
    public EmployeeRecord getEmployeeById(int id) {
        if (conn == null) return null;
        String sql = "SELECT e.employee_id, e.role_id, r.role_name, e.first_name, e.last_name, e.user_name, e.password, e.date_hired "
                   + "FROM employee e LEFT JOIN role r ON e.role_id = r.role_id WHERE e.employee_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new EmployeeRecord(
                        rs.getInt("employee_id"),
                        rs.getInt("role_id"),
                        rs.getString("role_name"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("user_name"),
                        rs.getString("password"),
                        rs.getTimestamp("date_hired")
                    );
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * Convenience: get roles as DefaultTableModel for JComboBox loading in NetBeans forms.
     * Columns: Role ID, Role Name
     */
    public DefaultTableModel getRoles() {
        DefaultTableModel model = new DefaultTableModel(new String[]{"Role ID","Role Name"}, 0);
        if (conn == null) return model;
        String sql = "SELECT role_id, role_name FROM role ORDER BY role_id";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                model.addRow(new Object[]{rs.getInt("role_id"), rs.getString("role_name")});
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return model;
    }

    // Simple immutable DTO returned by getEmployeeById
    public static class EmployeeRecord {
        public final int employeeId;
        public final int roleId;
        public final String roleName;
        public final String firstName;
        public final String lastName;
        public final String userName;
        public final String password;
        public final Timestamp dateHired;

        public EmployeeRecord(int employeeId, int roleId, String roleName, String firstName, String lastName, String userName, String password, Timestamp dateHired) {
            this.employeeId = employeeId;
            this.roleId = roleId;
            this.roleName = roleName;
            this.firstName = firstName;
            this.lastName = lastName;
            this.userName = userName;
            this.password = password;
            this.dateHired = dateHired;
        }
    }
}
