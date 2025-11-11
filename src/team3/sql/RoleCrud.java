
 //Shayne
package team3.sql;

import java.sql.*;
import java.util.*;

public class RoleCrud {
    private final Connection conn;

    public RoleCrud() {
        Connection tempConn = null;
        try {
            tempConn = new DbConnection().createConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        conn = tempConn;
    }


    public Map<Integer, String> getAllRoles() {
        Map<Integer, String> roles = new LinkedHashMap<>();
        String sql = "SELECT role_id, role_name FROM role";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                roles.put(rs.getInt("role_id"), rs.getString("role_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roles;
    }

 
    public boolean addRole(String roleName) {
        String sql = "INSERT INTO role (role_name) VALUES (?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, roleName);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean updateRole(int id, String roleName) {
        String sql = "UPDATE role SET role_name=? WHERE role_id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, roleName);
            ps.setInt(2, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteRole(int id) {
        String sql = "DELETE FROM role WHERE role_id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
